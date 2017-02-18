package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.MotionProfilingHandler.MotionProfileDrivetrainSide;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.CANTalon.TalonControlMode;


public class MotionProfilerSubsystem implements Runnable {
	
	public enum MotionProfileDrivetrainSide {
		
		DRIVE_LEFT_SIDE,
		DRIVE_RIGHT_SIDE
		
	}
	
	private CANTalon talon;
	
	private int loopTimeout = -1;
	
	private static final int kNumLoopsTimeout = 10;
	
	private static final int kMinPointsInTalon = 5;
		
	private CANTalon.MotionProfileStatus statusL = new CANTalon.MotionProfileStatus();
	
	private CANTalon.MotionProfileStatus statusR = new CANTalon.MotionProfileStatus();

	private static MotionProfileDrivetrainSide drivetrainSide;
	
	public static boolean isRunning = false;
	
	private static int state = 0;
	
	public void run (){
		
		/* Get the motion profile status every loop */
		drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
		OutputManager.getTalon(drivetrainSide).getMotionProfileStatus(statusL);
		
		
		drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
		OutputManager.getTalon(drivetrainSide).getMotionProfileStatus(statusR);
		
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
					
					bufferTalons();
					
					drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
					OutputManager.getTalon(drivetrainSide).getMotionProfileStatus(statusL);
					startFilling();
					
					
					
					drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
					OutputManager.getTalon(drivetrainSide).getMotionProfileStatus(statusR);
					startFilling();
					
					state = 1;
					loopTimeout = kNumLoopsTimeout;
				}
				break;
			case 1:
				
				bufferTalons();
				drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
				OutputManager.getTalon(drivetrainSide).getMotionProfileStatus(statusL);
				
				drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
				OutputManager.getTalon(drivetrainSide).getMotionProfileStatus(statusR);
				
				
				if ((statusL.btmBufferCnt > kMinPointsInTalon) && (statusR.btmBufferCnt > kMinPointsInTalon)) {
					/* start (once) the motion profile */
					
					OutputManager.startMotionProfiler();
	 
					/* MP will start once the control frame gets scheduled */
			
					loopTimeout = kNumLoopsTimeout;
					state = 2;
					
					System.out.println((OutputManager.motionProfilerLeft.getSetValue()) + "          " + (OutputManager.motionProfilerRight.getSetValue()) + "          " + (Timer.getFPGATimestamp()));
				}
				
				break;
			case 2:
				if ((statusL.isUnderrun == false) || (statusR.isUnderrun == false)) {
					loopTimeout = kNumLoopsTimeout;
				}
				
				if ((statusL.activePointValid && statusL.activePoint.isLastPoint) && (statusR.activePointValid && statusR.activePoint.isLastPoint)) {
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
		bufferTalons();
		if(drivetrainSide == MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE) {
			startFilling(GeneratedMotionProfileRight.Points, GeneratedMotionProfileRight.kNumPoints);
			//System.out.println("Right Side");
		}
		else if(drivetrainSide == MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
			startFilling(GeneratedMotionProfileLeft.Points, GeneratedMotionProfileLeft.kNumPoints);
			//System.out.println("Left Side");
		}
		bufferTalons();
		
	}

	private void startFilling(double[][] profile, int totalCnt) {
		
		
		
		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		bufferTalons();
		/* did we get an underrun condition since last time we checked ? */
		bufferTalons();
		if ((statusL.hasUnderrun) && (statusR.hasUnderrun)) {
			/* better log it so we know about it */
			OutputManager.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
			OutputManager.getTalon(drivetrainSide).clearMotionProfileHasUnderrun();
			
			drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
			OutputManager.getTalon(drivetrainSide).clearMotionProfileHasUnderrun();
		}
		bufferTalons();
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		bufferTalons();
		talon.clearMotionProfileTrajectories();

		/* This is fast since it's just into our TOP buffer */
		bufferTalons();
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

			bufferTalons();
			drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
			OutputManager.getTalon(drivetrainSide).pushMotionProfileTrajectory(point);
		
			
			drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
			OutputManager.getTalon(drivetrainSide).pushMotionProfileTrajectory(point);
		
		}
	
	}
	private static void bufferTalons(){
		drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;

		OutputManager.getTalon(drivetrainSide).processMotionProfileBuffer();;
		
		drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;

		OutputManager.getTalon(drivetrainSide).processMotionProfileBuffer();;
	
	}
	public static void reset(){
		drivetrainSide = MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
		OutputManager.getTalon(drivetrainSide).clearMotionProfileTrajectories();
	
		
		drivetrainSide = MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
		OutputManager.getTalon(drivetrainSide).clearMotionProfileTrajectories();
	
		
		OutputManager.endMotionProfiler();
		OutputManager.profilerRun(false);
		
		state = 0;
		
	}
	public static void OnNoProgress() {
		System.out.format("%s\n", "NOPROGRESS");
	}
}
