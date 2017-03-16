package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;


public class DriveMotionProfiler implements Runnable {
		
	private int loopTimeout = -1;
	
	private static final int kNumLoopsTimeout = 10;
	
	private static final int kMinPointsInTalon = 5;
	
	private static CANTalon.MotionProfileStatus statusL = new CANTalon.MotionProfileStatus();
	
	private static CANTalon.MotionProfileStatus statusR = new CANTalon.MotionProfileStatus();
	public static boolean isRunning = false;
	
	private static int state = 0;
	
	private static int waitTimePoints = 200;
	
	
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
	public static void startFilling() {
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
		
		//double[][] profile = GeneratedMotionProfileRight.Points;
		Waypoint[] points1 = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(5.5, -6.3, Pathfinder.d2r(-89))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, 4.0, 4.0, 50.0);

		// Generate the trajectory
		Trajectory trajectory1 = Pathfinder.generate(points1, config);
		
		double wheelbase_width = 29.25/12;

		// Create the Modifier Object
		TankModifier modifier1 = new TankModifier(trajectory1);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier1.modify(wheelbase_width);

		Trajectory left1  = modifier1.getLeftTrajectory();
		Trajectory right1 = modifier1.getRightTrajectory();
		/////////////////////////////////////////////////////////////////////////////////
		Waypoint[] points2 = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(3.8, 1.0, Pathfinder.d2r(80))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		// Generate the trajectory
		Trajectory trajectory2 = Pathfinder.generate(points2, config);

		// Create the Modifier Object
		TankModifier modifier2 = new TankModifier(trajectory2);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier2.modify(wheelbase_width);

		Trajectory left2  = modifier2.getLeftTrajectory();
		Trajectory right2 = modifier2.getRightTrajectory();
		/////////////////////////////////////////////////////////////////////////////////
		Waypoint[] points3 = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(5.0, 0, Pathfinder.d2r(0))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		// Generate the trajectory
		Trajectory trajectory3 = Pathfinder.generate(points3, config);

		// Create the Modifier Object
		TankModifier modifier3 = new TankModifier(trajectory3);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier3.modify(wheelbase_width);

		Trajectory left3  = modifier3.getLeftTrajectory();
		Trajectory right3 = modifier3.getRightTrajectory();
		
		int totalPoints = left1.length() + left2.length() + left3.length() + waitTimePoints;
		
		double [][] pointsRight = new double[totalPoints + 8 + 8 + 8][3];
		double [][] pointsLeft = new double[totalPoints + 8 + 8 + 8][3];
		/*
		Segment segLeft1 = left1.get(i);
		Segment segRight2 = right2.get(i);
		Segment segLeft3 = left3.get(i);
		Segment segRight1 = right1.get(i);
		Segment segLeft2 = left2.get(i);
		Segment segRight3 = right3.get(i);
		*/	
		for(int r = 0; r < left1.length(); r++){
			
			Segment segLeft1 = left1.get(r);
			Segment segRight1 = right1.get(r);
			
			pointsLeft[r][0] = segLeft1.position;
			pointsRight[r][0] = segRight1.position;
			pointsLeft[r][1] = segLeft1.velocity;
			pointsRight[r][1] = segRight1.velocity;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left1.length(); r < left1.length() + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left1.length() + 8; r < waitTimePoints + left1.length() + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left1.length() + waitTimePoints + 8; r < left2.length() + left1.length() + waitTimePoints + 8; r++){
			
			Segment segLeft2 = left2.get(r - (left1.length() + waitTimePoints + 8));
			Segment segRight2 = right2.get(r - (left1.length() + waitTimePoints + 8));
			
			pointsLeft[r][0] = segLeft2.position;
			pointsRight[r][0] = segRight2.position;
			pointsLeft[r][1] = segLeft2.velocity;
			pointsRight[r][1] = segRight2.velocity;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left2.length() + left1.length() + waitTimePoints + 8; r < left2.length() + left1.length() + waitTimePoints + 8 + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left2.length() + left1.length() + waitTimePoints + 8 + 8; r < totalPoints + 8 + 8; r++){
			
			Segment segLeft3 = left3.get(r - (left2.length() + left1.length() + waitTimePoints + 8 + 8));
			Segment segRight3 = right3.get(r - (left2.length() + left1.length() + waitTimePoints + 8 + 8));
			
			pointsLeft[r][0] = segLeft3.position;
			pointsRight[r][0] = segRight3.position;
			pointsLeft[r][1] = segLeft3.velocity;
			pointsRight[r][1] = segRight3.velocity;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = totalPoints + 8 + 8; r < totalPoints + 8 + 8 + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		
		/*int totalCnt = GeneratedMotionProfileRight.kNumPoints;
		int totalCntToHopper = GeneratedMotionProfileRight.kNumPointsToHopper;
		int totalCntWait = GeneratedMotionProfileRight.kNumPointsWait;
		int totalCntBackwards = GeneratedMotionProfileRight.kNumPointsBackwards;
		int totalCntToBoiler = GeneratedMotionProfileRight.kNumPointsToBoiler;*/
		int totalCnt = totalPoints + 8 + 8 + 8;
		int totalCntToHopper = left1.length() + 8;
		int totalCntWait = waitTimePoints;
		int totalCntBackwards = left2.length() + 8;
		int totalCntToBoiler = left3.length() + 8;
		
		for (int i = 0; i < totalCnt; ++i) {
			OutputManager.L1Master.processMotionProfileBuffer();
			OutputManager.R1Master.processMotionProfileBuffer();
			
			/* for each point, fill our structure and pass it to API */
			
			point.position = pointsRight[i][0];
			point.velocity = pointsRight[i][1];
			//point.position = profile[i][0];
			//point.velocity = profile[i][1];
			
			//point.timeDurMs = (int) profile[i][2];
			point.timeDurMs = (int) pointsRight[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			/*System.out.println("i Value: " + i);*/
			point.zeroPos = false;
			
			if (i == 0)
				
				point.zeroPos = true; /* set this to true on the first point */
			
			if (i >= (totalCnt - 25)) point.velocityOnly = true;
			if ((i >= (totalCntToHopper - 25)) && (i <= (totalCntToHopper))) point.velocityOnly = true;
			if ((i >= (totalCntBackwards - 25)) && (i <= (totalCntBackwards))) point.velocityOnly = true;
			if ((i >= (totalCntToBoiler - 25)) && (i <= (totalCntToBoiler))) point.velocityOnly = true;
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			if (i == (totalCntToHopper - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntWait - waitTimePoints)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntBackwards - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntToBoiler - 8)) {
				
				point.zeroPos = true;
			
			}
			
			point.isLastPoint = false;
			
			if ((i + 1) >= totalCnt) {
			
				point.velocityOnly = true;
				point.isLastPoint = true; /* set this to true on the last point  */
				System.out.println("Last Point: " + totalCnt);
			
			}
			
			OutputManager.pushMotionProfileTrajectoryRight(point);
			
		}
		
		
		//profile = GeneratedMotionProfileLeft.Points;
		
		/*totalCnt = GeneratedMotionProfileLeft.kNumPoints;
		totalCntToHopper = GeneratedMotionProfileLeft.kNumPointsToHopper;
		totalCntWait = GeneratedMotionProfileLeft.kNumPointsWait;
		totalCntBackwards = GeneratedMotionProfileLeft.kNumPointsBackwards;
		totalCntToBoiler = GeneratedMotionProfileLeft.kNumPointsToBoiler;*/
		totalCnt = totalPoints;
		totalCntToHopper = right1.length() + 8;
		totalCntWait = waitTimePoints;
		totalCntBackwards = right2.length() + 8;
		totalCntToBoiler = right3.length() + 8;
		
		for (int i = 0; i < totalCnt; ++i) {
			
			OutputManager.L1Master.processMotionProfileBuffer();
			OutputManager.R1Master.processMotionProfileBuffer();
			
			/* for each point, fill our structure and pass it to API */
			
			point.position = pointsLeft[i][0]* -1.0;
			point.velocity = pointsLeft[i][1]* -1.0;
			//point.position = profile[i][0] * -1.0;
			//point.velocity = profile[i][1] * -1.0;
			
			//point.timeDurMs = (int) profile[i][2];
			point.timeDurMs = (int) pointsLeft[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			System.out.println("i Value: " + i);
			point.zeroPos = false;
			
			if (i == 0)
				
				point.zeroPos = true; /* set this to true on the first point */
			
			if (i >= (totalCnt - 25)) point.velocityOnly = true;
			if ((i >= (totalCntToHopper - 25)) && (i <= (totalCntToHopper))) point.velocityOnly = true;
			if ((i >= (totalCntBackwards - 25)) && (i <= (totalCntBackwards))) point.velocityOnly = true;
			if ((i >= (totalCntToBoiler - 25)) && (i <= (totalCntToBoiler))) point.velocityOnly = true;
			
			if (i == (totalCnt - 8)) {
			
				point.isLastPoint = true;
				point.zeroPos = true;
			
			}
			if (i == (totalCntToHopper - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntWait - waitTimePoints)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntBackwards - 8)) {
				
				point.zeroPos = true;
			
			}
			if (i == (totalCntToBoiler - 8)) {
				
				point.zeroPos = true;
			
			}
			
			point.isLastPoint = false;
			
			if ((i + 1) >= totalCnt) {
			
				point.velocityOnly = true;
				point.isLastPoint = true; /* set this to true on the last point  */
				System.out.println("Last Point: " + totalCnt);
			
			}
			
			OutputManager.pushMotionProfileTrajectoryLeft(point);
			
		}
		System.out.println("Left: " +left1.length() + "Left 2: " + left2.length() + "Left 3: " + left3.length() +"   "+ waitTimePoints);
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
	/*public static void setWaypoints(){
		
		Waypoint[] points1 = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(5.5, -6.3, Pathfinder.d2r(-89))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, 4.0, 4.0, 50.0);

		// Generate the trajectory
		Trajectory trajectory1 = Pathfinder.generate(points1, config);
		
		double wheelbase_width = 29.25/12;

		// Create the Modifier Object
		TankModifier modifier1 = new TankModifier(trajectory1);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier1.modify(wheelbase_width);

		Trajectory left1  = modifier1.getLeftTrajectory();
		Trajectory right1 = modifier1.getRightTrajectory();
		/////////////////////////////////////////////////////////////////////////////////
		Waypoint[] points2 = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(5.5, -6.3, Pathfinder.d2r(-89))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		// Generate the trajectory
		Trajectory trajectory2 = Pathfinder.generate(points2, config);

		// Create the Modifier Object
		TankModifier modifier2 = new TankModifier(trajectory2);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier2.modify(wheelbase_width);

		Trajectory left2  = modifier1.getLeftTrajectory();
		Trajectory right2 = modifier1.getRightTrajectory();
		/////////////////////////////////////////////////////////////////////////////////
		Waypoint[] points3 = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(5.5, -6.3, Pathfinder.d2r(-89))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		// Generate the trajectory
		Trajectory trajectory3 = Pathfinder.generate(points3, config);

		// Create the Modifier Object
		TankModifier modifier3 = new TankModifier(trajectory3);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier3.modify(wheelbase_width);

		Trajectory left3  = modifier1.getLeftTrajectory();
		Trajectory right3 = modifier1.getRightTrajectory();
		
		int totalPoints = left1.length() + left2.length() + left3.length() + waitTimePoints;
		
		double [][] pointsRight = new double[totalPoints][3];
		double [][] pointsLeft = new double[totalPoints][3];
		
		Segment segLeft1 = left1.get(i);
		Segment segRight2 = right2.get(i);
		Segment segLeft3 = left3.get(i);
		Segment segRight1 = right1.get(i);
		Segment segLeft2 = left2.get(i);
		Segment segRight3 = right3.get(i);
			
		for(int r = 0; r <= left1.length(); r++){
			
			Segment segLeft1 = left1.get(r);
			Segment segRight1 = right1.get(r);
			
			pointsLeft[r][0] = segLeft1.position;
			pointsRight[r][0] = segRight1.position;
			pointsLeft[r][1] = segLeft1.velocity;
			pointsRight[r][1] = segRight1.velocity;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left1.length(); r <= left1.length() + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left1.length() + 8; r <= waitTimePoints + left1.length() + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left1.length() + waitTimePoints + 8; r <= left2.length() + left1.length() + waitTimePoints + 8; r++){
			
			Segment segLeft2 = left2.get(r);
			Segment segRight2 = right2.get(r);
			
			pointsLeft[r][0] = segLeft2.position;
			pointsRight[r][0] = segRight2.position;
			pointsLeft[r][1] = segLeft2.velocity;
			pointsRight[r][1] = segRight2.velocity;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left2.length() + left1.length() + waitTimePoints + 8; r <= left2.length() + left1.length() + waitTimePoints + 8 + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = left2.length() + left1.length() + waitTimePoints + 8 + 8; r <= totalPoints + 8 + 8; r++){
			
			Segment segLeft3 = left3.get(r);
			Segment segRight3 = right3.get(r);
			
			pointsLeft[r][0] = segLeft3.position;
			pointsRight[r][0] = segRight3.position;
			pointsLeft[r][1] = segLeft3.velocity;
			pointsRight[r][1] = segRight3.velocity;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
		for(int r = totalPoints + 8 + 8; r <= totalPoints + 8 + 8 + 8; r++){
			
			pointsLeft[r][0] = 0;
			pointsRight[r][0] = 0;
			pointsLeft[r][1] = 0;
			pointsRight[r][1] = 0;
			pointsLeft[r][2] = 10;
			pointsRight[r][2] = 10;
			
		}
	}*/
}
