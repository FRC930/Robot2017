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
		int totalCntSeg1 = 0;
		int totalCntSeg2 = 0;
		int totalCntSeg3 = 0;
		int totalCntSeg4 = 0;
		double [] [] profile  = GeneratedMotionProfileRightRED.Points;
		
		if (mode == 1){
			
			 totalCnt = GeneratedMotionProfileRightRED.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileRightRED.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileRightRED.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileRightRED.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileRightRED.kNumPointsToBoiler;
			
		}
		
		else if (mode == 2){
			
			 profile = GeneratedMotionProfileRightBLUE.Points;
			
			 totalCnt = GeneratedMotionProfileRightBLUE.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileRightBLUE.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileRightBLUE.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileRightBLUE.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileRightBLUE.kNumPointsToBoiler;
			
		}
		
		else if (mode == 3){
			
			 profile = GeneratedMotionProfileBlueRGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileBlueRGearRight.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileBlueRGearRight.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileBlueRGearRight.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileBlueRGearRight.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileBlueRGearRight.kNumPointsToBoiler;
			
		}

		else if (mode == 4){
			
			 profile = GeneratedMotionProfileRedLGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileRedLGearRight.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileRedLGearRight.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileRedLGearRight.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileRedLGearRight.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileRedLGearRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 5){
			
			 profile = GeneratedMotionProfileRedRGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileRedRGearRight.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileRedRGearRight.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileRedRGearRight.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileRedRGearRight.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileRedRGearRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 6){
			
			 profile = GeneratedMotionProfileBlueLGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileBlueLGearRight.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileBlueLGearRight.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileBlueLGearRight.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileBlueLGearRight.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileBlueLGearRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 7){
			
			 profile = GeneratedMotionProfileMiddleGearRight.Points;
			
			 totalCnt = GeneratedMotionProfileMiddleGearRight.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileMiddleGearRight.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileMiddleGearRight.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileMiddleGearRight.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileMiddleGearRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 8){
			
			 profile = GeneratedMotionProfileRedGearShootRight.Points;
			
			 totalCnt = GeneratedMotionProfileRedGearShootRight.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileRedGearShootRight.kNumPointsToAirship;
			 totalCntSeg2 = GeneratedMotionProfileRedGearShootRight.kNumPointsBackup;
			 totalCntSeg3 = GeneratedMotionProfileRedGearShootRight.kNumPointsPivot;
			 totalCntSeg4 = GeneratedMotionProfileRedGearShootRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 9){
			
			 profile = GeneratedMotionProfileBlueGearShootRight.Points;
			
			 totalCnt = GeneratedMotionProfileBlueGearShootRight.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileBlueGearShootRight.kNumPointsToAirship;
			 totalCntSeg2 = GeneratedMotionProfileBlueGearShootRight.kNumPointsBackup;
			 totalCntSeg3 = GeneratedMotionProfileBlueGearShootRight.kNumPointsPivot;
			 totalCntSeg4 = GeneratedMotionProfileBlueGearShootRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 10){
			
			 profile = GeneratedMotionProfileRedGearHopperRight.Points;
			
			 totalCnt = GeneratedMotionProfileRedGearHopperRight.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileRedGearHopperRight.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileRedGearHopperRight.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileRedGearHopperRight.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileRedGearHopperRight.kNumPointsToBoiler;
			
		}
		
		else if (mode == 11){
			
			 profile = GeneratedMotionProfileBlueGearHopperRight.Points;
			
			 totalCnt = GeneratedMotionProfileBlueGearHopperRight.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileBlueGearHopperRight.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileBlueGearHopperRight.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileBlueGearHopperRight.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileBlueGearHopperRight.kNumPointsToBoiler;
			
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
			// More points at end of first segment
			if ((i >= (totalCntSeg1 - 25)) && (i <= (totalCntSeg1))) point.velocityOnly = true;
			if ((i >= (totalCntSeg2 - 25)) && (i <= (totalCntSeg2))) point.velocityOnly = true;
			if ((i >= (totalCntSeg3 - 25)) && (i <= (totalCntSeg3))) point.velocityOnly = true;
			if ((i >= (totalCntSeg4 - 25)) && (i <= (totalCntSeg4))) point.velocityOnly = true;
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg1 - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg2 - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg3 - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg4 - 8)) {
				
				point.zeroPos = true;
			
			}
			
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
			 totalCntSeg1 = GeneratedMotionProfileLeftRED.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileLeftRED.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileLeftRED.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileLeftRED.kNumPointsToBoiler;
			
		}
		
		else if (mode == 2){
			
			 profile = GeneratedMotionProfileLeftBLUE.Points;
			
			 totalCnt = GeneratedMotionProfileLeftBLUE.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileLeftBLUE.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileLeftBLUE.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileLeftBLUE.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileLeftBLUE.kNumPointsToBoiler;
			
		}
		
		else if (mode == 3){
			
			 profile = GeneratedMotionProfileBlueRGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileBlueRGearLeft.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileBlueRGearLeft.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileBlueRGearLeft.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileBlueRGearLeft.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileBlueRGearLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 4){
			
			 profile = GeneratedMotionProfileRedLGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileRedLGearLeft.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileRedLGearLeft.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileRedLGearLeft.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileRedLGearLeft.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileRedLGearLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 5){
			
			 profile = GeneratedMotionProfileRedRGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileRedRGearLeft.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileRedRGearLeft.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileRedRGearLeft.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileRedRGearLeft.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileRedRGearLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 6){
			
			 profile = GeneratedMotionProfileBlueLGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileBlueLGearLeft.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileBlueLGearLeft.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileBlueLGearLeft.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileBlueLGearLeft.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileBlueLGearLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 7){
			
			 profile = GeneratedMotionProfileMiddleGearLeft.Points;
			
			 totalCnt = GeneratedMotionProfileMiddleGearLeft.kNumPoints;
			 totalCntSeg1 = 999999;//GeneratedMotionProfileMiddleGearLeft.kNumPointsToHopper;
			 totalCntSeg2 = 999999;//GeneratedMotionProfileMiddleGearLeft.kNumPointsWait;
			 totalCntSeg3 = 999999;//GeneratedMotionProfileMiddleGearLeft.kNumPointsBackwards;
			 totalCntSeg4 = 999999;//GeneratedMotionProfileMiddleGearLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 8){
			
			 profile = GeneratedMotionProfileRedGearShootLeft.Points;
			
			 totalCnt = GeneratedMotionProfileRedGearShootLeft.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileRedGearShootLeft.kNumPointsToAirship;
			 totalCntSeg2 = GeneratedMotionProfileRedGearShootLeft.kNumPointsBackup;
			 totalCntSeg3 = GeneratedMotionProfileRedGearShootLeft.kNumPointsPivot;
			 totalCntSeg4 = GeneratedMotionProfileRedGearShootLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 9){
			
			 profile = GeneratedMotionProfileBlueGearShootLeft.Points;
			
			 totalCnt = GeneratedMotionProfileBlueGearShootLeft.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileBlueGearShootLeft.kNumPointsToAirship;
			 totalCntSeg2 = GeneratedMotionProfileBlueGearShootLeft.kNumPointsBackup;
			 totalCntSeg3 = GeneratedMotionProfileBlueGearShootLeft.kNumPointsPivot;
			 totalCntSeg4 = GeneratedMotionProfileBlueGearShootLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 10){
			
			 profile = GeneratedMotionProfileRedGearHopperLeft.Points;
			
			 totalCnt = GeneratedMotionProfileRedGearHopperLeft.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileRedGearHopperLeft.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileRedGearHopperLeft.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileRedGearHopperLeft.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileRedGearHopperLeft.kNumPointsToBoiler;
			
		}
		
		else if (mode == 11){
			
			 profile = GeneratedMotionProfileBlueGearHopperLeft.Points;
			
			 totalCnt = GeneratedMotionProfileBlueGearHopperLeft.kNumPoints;
			 totalCntSeg1 = GeneratedMotionProfileBlueGearHopperLeft.kNumPointsToHopper;
			 totalCntSeg2 = GeneratedMotionProfileBlueGearHopperLeft.kNumPointsWait;
			 totalCntSeg3 = GeneratedMotionProfileBlueGearHopperLeft.kNumPointsBackwards;
			 totalCntSeg4 = GeneratedMotionProfileBlueGearHopperLeft.kNumPointsToBoiler;
			
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
			if ((i >= (totalCntSeg1 - 25)) && (i <= (totalCntSeg1))) point.velocityOnly = true;
			if ((i >= (totalCntSeg2 - 25)) && (i <= (totalCntSeg2))) point.velocityOnly = true;
			if ((i >= (totalCntSeg3 - 25)) && (i <= (totalCntSeg3))) point.velocityOnly = true;
			if ((i >= (totalCntSeg4 - 25)) && (i <= (totalCntSeg4))) point.velocityOnly = true;
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg1 - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg2 - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg3 - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntSeg4 - 8)) {
				
				point.zeroPos = true;
			
			}
			
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
