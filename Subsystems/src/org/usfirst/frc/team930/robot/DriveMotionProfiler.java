package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveMotionProfiler implements Runnable {
		
	private int loopTimeout = -1;
	
	private static final int kNumLoopsTimeout = 10;
	
	private static final int kMinPointsInTalon = 5;
	
	private static CANTalon.MotionProfileStatus statusL = new CANTalon.MotionProfileStatus();
	
	private static CANTalon.MotionProfileStatus statusR = new CANTalon.MotionProfileStatus();
	public static boolean isRunning = false;
	
	private static int state = 0;
	
	
	public void run (){
		
		/* Get the motion profile status every loop */
		
		if (OutputManager.isRobotAuton()) {
			
			OutputManager.L1Master.getMotionProfileStatus(statusL);
			OutputManager.R1Master.getMotionProfileStatus(statusR);

		}

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
			
			SmartDashboard.putNumber("Left Encoder Velocity", OutputManager.L1Master.getEncVelocity());
			SmartDashboard.putNumber("Right Encoder Velocity", OutputManager.R1Master.getEncVelocity());
			
			OutputManager.L1Master.processMotionProfileBuffer();
			OutputManager.R1Master.processMotionProfileBuffer();
			
			switch (state) {
			
			case 0: 
				
				if(isRunning){
					
					OutputManager.endMotionProfiler();
					
					OutputManager.L1Master.processMotionProfileBuffer();
					OutputManager.R1Master.processMotionProfileBuffer();					
					
					//startFilling();
					
					state = 1;
					loopTimeout = kNumLoopsTimeout;
					
				}
				
				break;
				
			case 1:

				OutputManager.L1Master.getMotionProfileStatus(statusL);
				OutputManager.R1Master.getMotionProfileStatus(statusR);
				
				System.out.println("Right BufferCnt: " + statusR.btmBufferCnt);
				System.out.println("Left BufferCnt: " + statusL.btmBufferCnt);
				
				if ((statusL.btmBufferCnt > kMinPointsInTalon) && (statusR.btmBufferCnt > kMinPointsInTalon)) {

					/* start (once) the motion profile */
					
					OutputManager.startMotionProfiler();
	 
					/* MP will start once the control frame gets scheduled */
			
					loopTimeout = kNumLoopsTimeout;
					state = 2;
					
					//System.out.println((OutputManager.motionProfilerLeft.getSetValue()) + "          " + (OutputManager.motionProfilerRight.getSetValue()) + "          " + (Timer.getFPGATimestamp()));
				}
				
				break;
				
			case 2:
				
				OutputManager.L1Master.getMotionProfileStatus(statusL);
				OutputManager.R1Master.getMotionProfileStatus(statusR);
				
				if (!statusL.isUnderrun || !statusR.isUnderrun) {
					loopTimeout = kNumLoopsTimeout;
				}
				
				OutputManager.L1Master.getMotionProfileStatus(statusL);
				OutputManager.R1Master.getMotionProfileStatus(statusR);
				
				if ((statusL.activePointValid && statusL.activePoint.isLastPoint)) {
					/*
					 * because we set the last point's isLast to true, we will
					 * get here when the MP is done
					 */
					//OutputManager.endMotionProfiler();
					//state = 0;
					//loopTimeout = -1;
					System.out.println("Left Done " + Timer.getFPGATimestamp());
				}
				
				if ((statusR.activePointValid && statusR.activePoint.isLastPoint)) {
					/*
					 * because we set the last point's isLast to true, we will
					 * get here when the MP is done
					 */
					//OutputManager.endMotionProfiler();
					//state = 0;
					//loopTimeout = -1;
					System.out.println("Right Done " + Timer.getFPGATimestamp());
				}
				
				break;
				
			}
			
		}
		
	}
	public static void startFilling(int mode) {
		/* since this example only has one talon, just update that one */
		/*
		drivetrainSide = OutputManager.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE;
			startFilling(GeneratedMotionProfileRight.Points, GeneratedMotionProfileRight.kNumPoints);
			//System.out.println("Right Side");
	
		drivetrainSide = OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE;
			startFilling(GeneratedMotionProfileLeft.Points, GeneratedMotionProfileLeft.kNumPoints);
			//System.out.println("Left Side");
		//}*/
		
		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		/* did we get an underrun condition since last time we checked ? */
	
		OutputManager.L1Master.getMotionProfileStatus(statusL);
		OutputManager.R1Master.getMotionProfileStatus(statusR);
		
		if ((statusL.hasUnderrun) || (statusR.hasUnderrun)) {
		
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
		
		int totalCnt = 0;
		int totalCntToHopper = 0;
		int totalCntWait = 0;
		int totalCntBackwards = 0;
		int totalCntToBoiler = 0;
		double [] [] profile  = GeneratedMotionProfileRightRED.Points;
		
		if (mode == 1){
			
			 totalCnt = GeneratedMotionProfileRightRED.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileRightRED.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileRightRED.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileRightRED.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileRightRED.kNumPointsToBoiler;
			
		}
		
		else if (mode == 2){
			
			 profile = GeneratedMotionProfileRightBLUE.Points;
			
			 totalCnt = GeneratedMotionProfileRightBLUE.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileRightBLUE.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileRightBLUE.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileRightBLUE.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileRightBLUE.kNumPointsToBoiler;
			
		}
		
		else if (mode == 3){
			
			 profile = GeneratedMotionProfileBlueRGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileBlueRGearRight.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileBlueRGearRight.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileBlueRGearRight.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileBlueRGearRight.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileBlueRGearRight.kNumPointsToBoiler;
			
		}

		else if (mode == 4){
			
			 profile = GeneratedMotionProfileRedLGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileRedLGearRight.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileRedLGearRight.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileRedLGearRight.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileRedLGearRight.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileRedLGearRight.kNumPointsToBoiler;
			
		}
		
		for (int i = 0; i < totalCnt; ++i) {
			OutputManager.L1Master.processMotionProfileBuffer();
			OutputManager.R1Master.processMotionProfileBuffer();
			
			/* for each point, fill our structure and pass it to API */
			
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			System.out.println("i Value: " + i);
			point.zeroPos = false;
			
			if (i == 0)
				
				point.zeroPos = true; /* set this to true on the first point */
			
			if (i >= (totalCnt - 25)) point.velocityOnly = true;
			/*if ((i >= (totalCntToHopper - 25)) && (i <= (totalCntToHopper))) point.velocityOnly = true;
			if ((i >= (totalCntBackwards - 25)) && (i <= (totalCntBackwards))) point.velocityOnly = true;
			if ((i >= (totalCntToBoiler - 25)) && (i <= (totalCntToBoiler))) point.velocityOnly = true;*/
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			/*if (i == (totalCntToHopper - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntWait - 200)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntBackwards - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntToBoiler - 8)) {
				
				point.zeroPos = true;
			
			}*/
			
			point.isLastPoint = false;
			
			if ((i + 1) >= totalCnt) {
			
				point.velocityOnly = true;
				point.isLastPoint = true; /* set this to true on the last point  */
				System.out.println("Last Point");
			
			}
			
			OutputManager.pushMotionProfileTrajectoryRight(point);
			
		}
		
		if (mode == 1){
			
			 profile  = GeneratedMotionProfileLeftRED.Points;
			 
			 totalCnt = GeneratedMotionProfileLeftRED.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileLeftRED.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileLeftRED.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileLeftRED.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileLeftRED.kNumPointsToBoiler;
			
		}
		
		else if (mode == 2){
			
			 profile = GeneratedMotionProfileLeftBLUE.Points;
			
			 totalCnt = GeneratedMotionProfileLeftBLUE.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileLeftBLUE.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileLeftBLUE.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileLeftBLUE.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileLeftBLUE.kNumPointsToBoiler;
			
		}
		
		else if (mode == 3){
			
			 profile = GeneratedMotionProfileBlueRGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileBlueRGearLeft.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileBlueRGearLeft.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileBlueRGearLeft.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileBlueRGearLeft.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileBlueRGearLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 4){
			
			 profile = GeneratedMotionProfileRedLGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileRedLGearLeft.kNumPoints;
			 totalCntToHopper = GeneratedMotionProfileRedLGearLeft.kNumPointsToHopper;
			 totalCntWait = GeneratedMotionProfileRedLGearLeft.kNumPointsWait;
			 totalCntBackwards = GeneratedMotionProfileRedLGearLeft.kNumPointsBackwards;
			 totalCntToBoiler = GeneratedMotionProfileRedLGearLeft.kNumPointsToBoiler;
			
		}
		
		for (int i = 0; i < totalCnt; ++i) {
			
			OutputManager.L1Master.processMotionProfileBuffer();
			OutputManager.R1Master.processMotionProfileBuffer();
			
			/* for each point, fill our structure and pass it to API */
			
			point.position = profile[i][0] * -1.0;
			point.velocity = profile[i][1] * -1.0;
			
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			System.out.println("i Value: " + i);
			point.zeroPos = false;
			
			if (i == 0)
				
				point.zeroPos = true; /* set this to true on the first point */
			
			if (i >= (totalCnt - 25)) point.velocityOnly = true;
			/*if ((i >= (totalCntToHopper - 25)) && (i <= (totalCntToHopper))) point.velocityOnly = true;
			if ((i >= (totalCntBackwards - 25)) && (i <= (totalCntBackwards))) point.velocityOnly = true;
			if ((i >= (totalCntToBoiler - 25)) && (i <= (totalCntToBoiler))) point.velocityOnly = true;*/
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			/*if (i == (totalCntToHopper - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntWait - 200)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntBackwards - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntToBoiler - 8)) {
				
				point.zeroPos = true;
			
			}*/
			
			point.isLastPoint = false;
			
			if ((i + 1) >= totalCnt) {
			
				point.velocityOnly = true;
				point.isLastPoint = true; /* set this to true on the last point  */
				System.out.println("Last Point");
			
			}
			
			OutputManager.pushMotionProfileTrajectoryLeft(point);
			
		}
		
	}

	/*private void startFilling(double[][] profile, int totalCnt) {
			
		/* create an empty point 
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		 did we get an underrun condition since last time we checked ? 
	
		if ((OutputManager.isLeftStatusUnderrun()) && (OutputManager.isRightStatusUnderrun())) {
		
			 better log it so we know about it 
			OutputManager.OnUnderrun();
			
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 
			OutputManager.clearMotionProfileHasUnderrun();
			
		}
		
		
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 
		
		OutputManager.clearMotionProfileTrajectories();

		 This is fast since it's just into our TOP buffer 
	
		for (int i = 0; i < totalCnt; ++i) {
			 for each point, fill our structure and pass it to API 
			
			if(drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
			
				point.position = profile[i][0] * -1.0;
				point.velocity = profile[i][1] * -1.0;
			
			}
			
			else if(drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE) {
			
				point.position = profile[i][0];
				point.velocity = profile[i][1];
			
			}
			
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0;  which set of gains would you like to use? 
			point.velocityOnly = false;  set true to not do any position
										 * servo, just velocity feedforward
										 
			System.out.println("i Value: " + i);
			point.zeroPos = false;
			
			if (i == 0)
				
				point.zeroPos = true;  set this to true on the first point 
			
			if (i >= (totalCnt - 25))point.velocityOnly = true;
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			
			point.isLastPoint = false;
			
			if ((i + 1) >= totalCnt) {
			
				point.velocityOnly = true;
				point.isLastPoint = true;  set this to true on the last point  
				System.out.println("Last Point");
			
			}
			
			if(drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
			
				OutputManager.pushMotionProfileTrajectoryLeft(point);
			
			}
			
			else if (drivetrainSide == OutputManager.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE) {
				
				OutputManager.pushMotionProfileTrajectoryLeft(point);
			
			}
			
		}*/
	
	//}

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
