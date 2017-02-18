package org.usfirst.frc.team930.robot;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;


public class DriveMotionProfiler implements Runnable {
		
	private int loopTimeout = -1;
	
	private static final int kNumLoopsTimeout = 10;
	
	private static final int kMinPointsInTalon = 5;
		
	private static OutputManager.MotionProfileDrivetrainSide drivetrainSide;
	
	public static boolean isRunning = false;
	
	private static int state = 0;
	
	
	public void run (){
		
		/* Get the motion profile status every loop */
		
		

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
		// && isRunning
		if (OutputManager.isRobotAuton()){
			
			switch (state) {
			
			case 0: 
				
				if(isRunning){
					
					OutputManager.endMotionProfiler();
					
					OutputManager.bufferTalons();
					
					startFilling();
					
					state = 1;
					loopTimeout = kNumLoopsTimeout;
					
				}
				
				break;
				
			case 1:
			
<<<<<<< HEAD
				System.out.println("Right BufferCnt: " + OutputManager.GetBtmBufferCntRight());
				System.out.println("Left BufferCnt: " + OutputManager.GetBtmBufferCntLeft());

				if ((OutputManager.GetBtmBufferCntRight() > kMinPointsInTalon) && (OutputManager.GetBtmBufferCntLeft() > kMinPointsInTalon)) {
=======
				OutputManager.getMotionProfileStatus(statusL, statusR);
				
				System.out.println("Left BufferCnt: " + statusL.btmBufferCnt);
				System.out.println("Right BufferCnt: " + statusR.btmBufferCnt);
				
				if ((statusL.btmBufferCnt > kMinPointsInTalon) && (statusR.btmBufferCnt > kMinPointsInTalon)) {
>>>>>>> 154ed37b2593218d8c532f71c3e6f81f7ba6d8f2
					/* start (once) the motion profile */
					
					OutputManager.startMotionProfiler();
	 
					/* MP will start once the control frame gets scheduled */
			
					loopTimeout = kNumLoopsTimeout;
					state = 2;
					
					//System.out.println((OutputManager.motionProfilerLeft.getSetValue()) + "          " + (OutputManager.motionProfilerRight.getSetValue()) + "          " + (Timer.getFPGATimestamp()));
				}
				
				break;
				
			case 2:
				
				if (!OutputManager.isLeftStatusUnderrun() || !OutputManager.isRightStatusUnderrun()) {
					loopTimeout = kNumLoopsTimeout;
				}
				
				if ((OutputManager.isLeftActivePointValid()) && (OutputManager.isRightActivePointValid())) {
					/*
					 * because we set the last point's isLast to true, we will
					 * get here when the MP is done
					 */
					OutputManager.endMotionProfiler();
					//state = 0;
					loopTimeout = -1;
					System.out.println("MP Done " + Timer.getFPGATimestamp());
				}
				
				break;
				
			}
			
		}
		
	}
	private void startFilling() {
		/* since this example only has one talon, just update that one */
		
		drivetrainSide = OutputManager.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
			startFilling(GeneratedMotionProfileRight.Points, GeneratedMotionProfileRight.kNumPoints);
			//System.out.println("Right Side");
	
		drivetrainSide = OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
			startFilling(GeneratedMotionProfileLeft.Points, GeneratedMotionProfileLeft.kNumPoints);
			//System.out.println("Left Side");
		//}
		
		
	}

	private void startFilling(double[][] profile, int totalCnt) {
			
		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		/* did we get an underrun condition since last time we checked ? */
	
		if ((OutputManager.isLeftStatusUnderrun()) && (OutputManager.isRightStatusUnderrun())) {
		
			/* better log it so we know about it */
			OutputManager.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			OutputManager.clearMotionProfileHasUnderrun();
			
		}
		
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		
		OutputManager.clearMotionProfileTrajectories();

		/* This is fast since it's just into our TOP buffer */
	
		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			
			if(drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
			
				point.position = profile[i][0] * -1.0;
				point.velocity = profile[i][1] * -1.0;
			
			}
			
			else if(drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE) {
			
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
			
			if(drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
			
				OutputManager.pushMotionProfileTrajectoryLeft(point);
			
			}
			
			else if (drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
				
				OutputManager.pushMotionProfileTrajectoryLeft(point);
			
			}
			
		}
	
	}

	public static void init(){
		
		OutputManager.changeMotionControlFramePeriod();
		
		OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.MotionProfile);
		
		OutputManager.endMotionProfiler();
		OutputManager.profilerRun(false);
		
		state = 0;
		
	}
	public static void OnNoProgress() {
		//System.out.format("%s\n", "NOPROGRESS");
	}
}
