package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.CANTalon.TalonControlMode;

public class MotionProfilingHandler {
	
	private static int kNumPoints;
	public static double[][] Points;
	
	public enum MotionProfileDrivetrainSide {
		DRIVE_LEFT_SIDE,
		DRIVE_RIGHT_SIDE
	}
	
	private MotionProfileDrivetrainSide drivetrainSide;
	
	static double timeout = 0;
	static int count = 0;

	private static final String []_table = {" Dis "," En  ","Hold "};
	
	private CANTalon.MotionProfileStatus status = new CANTalon.MotionProfileStatus();

	/**
	 * reference to the talon we plan on manipulating. We will not changeMode()
	 * or call set(), just get motion profile status and make decisions based on
	 * motion profile.
	 */
	
	private CANTalon talon;

	/**
	 * State machine to make sure we let enough of the motion profile stream to
	 * talon before we fire it.
	 */
	private int state = 0;
	/**
	 * Any time you have a state machine that waits for external events, its a
	 * good idea to add a timeout. Set to -1 to disable. Set to nonzero to count
	 * down to '0' which will print an error message. Counting loops is not a
	 * very accurate method of tracking timeout, but this is just conservative
	 * timeout. Getting time-stamps would certainly work too, this is just
	 * simple (no need to worry about timer overflows).
	 */
	private int loopTimeout = -1;
	/**
	 * If start() gets called, this flag is set and in the control() we will
	 * service it.
	 */
	private boolean bStart = false;
	
	private boolean sideCheck = false;

	/**
	 * Since the CANTalon.set() routine is mode specific, deduce what we want
	 * the set value to be and let the calling module apply it whenever we
	 * decide to switch to MP mode.
	 */
	private CANTalon.SetValueMotionProfile setValue = CANTalon.SetValueMotionProfile.Disable;
	/**
	 * How many trajectory points do we wait for before firing the motion
	 * profile.
	 */
	private static final int kMinPointsInTalon = 5;
	/**
	 * Just a state timeout to make sure we don't get stuck anywhere. Each loop
	 * is about 20ms.
	 */
	private static final int kNumLoopsTimeout = 10;
	
	/**
	 * Lets create a periodic task to funnel our trajectory points into our talon.
	 * It doesn't need to be very accurate, just needs to keep pace with the motion
	 * profiler executer.  Now if you're trajectory points are slow, there is no need
	 * to do this, just call _talon.processMotionProfileBuffer() in your teleop loop.
	 * Generally speaking you want to call it at least twice as fast as the duration
	 * of your trajectory points.  So if they are firing every 20ms, you should call 
	 * every 10ms.
	 */
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {  
	    	talon.processMotionProfileBuffer();
	    	//System.out.println("Running");
	    }
	}
	
	Notifier _notifer = new Notifier(new PeriodicRunnable());
	

	/**
	 * C'tor
	 * 
	 * @param talon
	 *            reference to Talon object to fetch motion profile status from.
	 */
	public MotionProfilingHandler(CANTalon _talon, MotionProfileDrivetrainSide _drivetrainSide) {
		talon = _talon;
		
		drivetrainSide = _drivetrainSide;
		
		/*
		 * since our MP is 10ms per point, set the control frame rate and the
		 * notifer to half that
		 */
		talon.changeMotionControlFramePeriod(5);
		_notifer.startPeriodic(0.005);
	}

	/**
	 * Called to clear Motion profile buffer and reset state info during
	 * disabled and when Talon is not in MP control mode.
	 */
	public void reset() {
		/*
		 * Let's clear the buffer just in case user decided to disable in the
		 * middle of an MP, and now we have the second half of a profile just
		 * sitting in memory.
		 */
		talon.clearMotionProfileTrajectories();
		/* When we do re-enter motionProfile control mode, stay disabled. */
		setValue = CANTalon.SetValueMotionProfile.Disable;
		/* When we do start running our state machine start at the beginning. */
		state = 0;
		loopTimeout = -1;
		/*
		 * If application wanted to start an MP before, ignore and wait for next
		 * button press
		 */
		bStart = false;
	}

	/**
	 * Called every loop.
	 */
	public void control() {
		/* Get the motion profile status every loop */
		talon.getMotionProfileStatus(status);

		/*
		 * track time, this is rudimentary but that's okay, we just want to make
		 * sure things never get stuck.
		 */
		if (loopTimeout < 0) {
			/* do nothing, timeout is disabled */
		} else {
			/* our timeout is nonzero */
			if (loopTimeout == 0) {
				/*
				 * something is wrong. Talon is not present, unplugged, breaker
				 * tripped
				 */
				OnNoProgress();
			} else {
				--loopTimeout;
			}
		}

		/* first check if we are in MP mode */
		if (talon.getControlMode() != TalonControlMode.MotionProfile) {
			/*
			 * we are not in MP mode. We are probably driving the robot around
			 * using gamepads or some other mode.
			 */
			state = 0;
			loopTimeout = -1;
		} else {
			/*
			 * we are in MP control mode. That means: starting Mps, checking Mp
			 * progress, and possibly interrupting MPs if thats what you want to
			 * do.
			 */
			switch (state) {
				case 0: /* wait for application to tell us to start an MP */
					if (bStart) {
						bStart = false;
	
						setValue = CANTalon.SetValueMotionProfile.Disable;
						startFilling();
						/*
						 * MP is being sent to CAN bus, wait a small amount of time
						 */
						state = 1;
						loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1: /*
						 * wait for MP to stream to Talon, really just the first few
						 * points
						 */
					/* do we have a minimum numberof points in Talon */
					if (status.btmBufferCnt > kMinPointsInTalon) {
						/* start (once) the motion profile */
						setValue = CANTalon.SetValueMotionProfile.Enable;
						
						/* MP will start once the control frame gets scheduled */
						state = 2;
						loopTimeout = kNumLoopsTimeout;
						
						//System.out.println((OutputManager.motionProfilerLeft.getSetValue()) + "          " + (OutputManager.motionProfilerRight.getSetValue()) + "          " + (Timer.getFPGATimestamp()));
					}
					break;
				case 2: /* check the status of the MP */
					/*
					 * if talon is reporting things are good, keep adding to our
					 * timeout. Really this is so that you can unplug your talon in
					 * the middle of an MP and react to it.
					 */
					if (status.isUnderrun == false) {
						loopTimeout = kNumLoopsTimeout;
					}
					/*
					 * If we are executing an MP and the MP finished, start loading
					 * another. We will go into hold state so robot servo's
					 * position.
					 */
					if (status.activePointValid && status.activePoint.isLastPoint) {
						/*
						 * because we set the last point's isLast to true, we will
						 * get here when the MP is done
						 */
						setValue = CANTalon.SetValueMotionProfile.Disable;
						//state = 0;
						loopTimeout = -1;
						System.out.println("MP Done " + Timer.getFPGATimestamp());
					}
					break;
			}
		}
		/* printfs and/or logging */
		//process(status);
		
		
	}

	/** Start filling the MPs to all of the involved Talons. */
	private void startFilling() {
		/* since this example only has one talon, just update that one */
		if(drivetrainSide == MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE) {
			startFilling(GeneratedMotionProfileRight.Points, GeneratedMotionProfileRight.kNumPoints);
			//System.out.println("Right Side");
		}
		else if(drivetrainSide == MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
			startFilling(GeneratedMotionProfileLeft.Points, GeneratedMotionProfileLeft.kNumPoints);
			//System.out.println("Left Side");
		}
		
	}

	private void startFilling(double[][] profile, int totalCnt) {
		
		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		/* did we get an underrun condition since last time we checked ? */
		if (status.hasUnderrun) {
			/* better log it so we know about it */
			OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			talon.clearMotionProfileHasUnderrun();
		}
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		talon.clearMotionProfileTrajectories();

		/* This is fast since it's just into our TOP buffer */
		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			
			if(drivetrainSide == MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
				point.position = profile[i][0] * -1.0;
				point.velocity = profile[i][1] * -1.0;
			}
			else if(drivetrainSide == MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE) {
				point.position = profile[i][0];
				point.velocity = profile[i][1];
			}
			
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			System.out.println("i Value: " + i);
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */
			
			if (i >= (totalCnt - 25))point.velocityOnly = true;
			
			if (i == (totalCnt - 8)) {
				point.isLastPoint = true;
				point.zeroPos = true;
			}
			
			point.isLastPoint = false;
			if ((i + 1) >= totalCnt) {
				point.velocityOnly = true;
				point.isLastPoint = true; /* set this to true on the last point  */
				System.out.println("Last Point");
			}

			talon.pushMotionProfileTrajectory(point);
		}
	}

	/**
	 * Called by application to signal Talon to start the buffered MP (when it's
	 * able to).
	 */
	void startMotionProfile() {
		bStart = true;
	}
	
	/**
	 * 
	 * @return the output value to pass to Talon's set() routine. 0 for disable
	 *         motion-profile output, 1 for enable motion-profile, 2 for hold
	 *         current motion profile trajectory point.
	 */
	CANTalon.SetValueMotionProfile getSetValue() {
		return setValue;
	}
	
	public int getkNumPoints() {
		return kNumPoints;
	}
	
	public double[][] getPoints() {
		return Points;
	}
	
	public static void OnUnderrun() {
		System.out.format("%s\n", "UNDERRUN");
	}
	public static void OnNoProgress() {
		System.out.format("%s\n", "NOPROGRESS");
	}
	static private String StrOutputEnable(CANTalon.SetValueMotionProfile sv)
	{
		if(sv == null)
			return "null";
		if(sv.value > 3)
			return "Inval";
		return _table[sv.value];
	}
	/** round to six decimal places */
	static private double round(double toround)
	{
		long whole = (long)(toround * 1000000.0 + 0.5);
		return ((double)whole) * 0.000001;
	}
	public static void process(CANTalon.MotionProfileStatus status1) {
		double now = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();

		if((now-timeout) > 0.2){
			timeout = now;
			/* fire a loop every 200ms */

			if(--count <= 0){
				count = 8;
				/* every 8 loops, print our columns */
				
				System.out.format("%-9s\t", "topCnt");
				System.out.format("%-9s\t", "btmCnt");
				System.out.format("%-9s\t", "set val");
				System.out.format("%-9s\t", "HasUnder");
				System.out.format("%-9s\t", "IsUnder");
				System.out.format("%-9s\t", "IsValid");
				System.out.format("%-9s\t", "IsLast");
				System.out.format("%-9s\t", "VelOnly");
				System.out.format("%-9s\t", "Pos");
				System.out.format("%-9s\t", "Vel");

				System.out.format("\n");
			}
			/* every loop, print our values */
			System.out.format("%-9s\t", status1.topBufferCnt);
			System.out.format("%-9s\t", status1.btmBufferCnt);
			System.out.format("%-9s\t", StrOutputEnable(status1.outputEnable));
			System.out.format("%-9s\t", (status1.hasUnderrun ? "1" : ""));
			System.out.format("%-9s\t", (status1.isUnderrun ? "1" : ""));
			System.out.format("%-9s\t", (status1.activePointValid ? "1" : ""));
			System.out.format("%-9s\t", (status1.activePoint.isLastPoint ? "1" : ""));
			System.out.format("%-9s\t", (status1.activePoint.velocityOnly ? "1" : ""));
			System.out.format("%-9s\t", round(status1.activePoint.position));
			System.out.format("%-9s\t", round(status1.activePoint.velocity));
			System.out.format("%-9s\t", round(OutputManager.getL1Voltage()));

			System.out.format("\n");
		}
	}
}
