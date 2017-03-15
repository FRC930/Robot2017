package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;


public class Drive implements Runnable {
	
	static Trajectory trajectory2;
	private static Timer time = new Timer();
	double xValue, yValue;
	public void run(){
		if(OutputManager.isRobotTeleop()){
			
			if (DSManager.checkToggleRelay()){
				if (OutputManager.getRelayState() == Relay.Value.kOn){
					OutputManager.turnRelaysOff();
				}
				else if (OutputManager.getRelayState() == Relay.Value.kOff){
					OutputManager.turnRelaysOn();
				}
			}
			

			// Adjusting joystick sensitivity
			double xValue = Math.pow((DSManager.getDriveXAxis()-.04)/.96, Constants.JOYSTICK_NONLINEARITY);
			double yValue = Math.pow((DSManager.getDriveYAxis() * -1.0 -.04)/.96, Constants.JOYSTICK_NONLINEARITY);
			
			if (xValue > 0){

				xValue = Math.ceil(xValue * 1000000) / 1000000;
				
		    }
		    
			if (yValue > 0){

				yValue = Math.ceil(yValue * 1000000) / 1000000;
				
			}
			
			if (yValue < 0){
				
			    yValue = Math.floor(yValue * 1000000) / 1000000;
			    
			}
			
			if (xValue < 0){
				
			    xValue = Math.floor(xValue * 1000000) / 1000000;
			    
			}
			

			// Deadband
			if (Math.abs(DSManager.getDriveXAxis()) < Constants.JOYSTICK_ERROR_ALLOWANCE) {
				xValue = Constants.X_DEFAULT_VALUE;
			}
			if (Math.abs(DSManager.getDriveYAxis()) < Constants.JOYSTICK_ERROR_ALLOWANCE) {
				yValue = Constants.Y_DEFAULT_VALUE;
			}
			if ((Math.abs(xValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE)&& (Math.abs(yValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE)) {
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_DRIVE);
			}
			
			
			if (xValue > 1)
				xValue = 1;
			if (xValue < -1)
				xValue = -1;
			if (yValue >1)
				yValue = 1;
			if(yValue < -1)
				yValue = -1;

			
			// Setting talons
			OutputManager.setSpeedL(yValue + xValue);
			OutputManager.setSpeedR(yValue - xValue);
			
			SmartDashboard.putNumber("Left Encoder Velocity", OutputManager.L1Master.getEncVelocity());
			SmartDashboard.putNumber("Right Encoder Velocity", OutputManager.R1Master.getEncVelocity());

		}
		else if (OutputManager.isRobotAuton()){
				
			//AutonManager.driveCode();
			
			if (time.get() > 1) {
				
				OutputManager.turnRelaysOff();
				
			}
			else {
				
				OutputManager.turnRelaysOn();
			
			}
			
			OutputManager.profilerRun(true);
			
		}
			
	}
	
	public static void init(){
		
		OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.PercentVbus);	
		
	}
	
	public static void autonInit(){
		
		time.reset();	
		
		time.start();
		
	}
	public static Trajectory generatePoints(int side){
		if(side == 0){
		Waypoint[] points = new Waypoint[] {
			    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(5.5, -6.3, Pathfinder.d2r(-89))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			};
		
		
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, 4.0, 4.0, 50.0);

		Trajectory trajectory = Pathfinder.generate(points, config);
		trajectory2 = trajectory;
		return null;
		}
		else if(side == 1){
		double wheelbase_width = 29.25/12;

		// Create the Modifier Object
		TankModifier modifier = new TankModifier(trajectory2);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier.modify(wheelbase_width);

		Trajectory left  = modifier.getLeftTrajectory(); 
		 return left;// Get the Left Side
		}
		else if(side == 2){
		double wheelbase_width2 = 29.25/12;
		

		// Create the Modifier Object
		TankModifier modifier2 = new TankModifier(trajectory2);

		// Generate the Left and Right trajectories using the original trajectory
		// as the centre
		modifier2.modify(wheelbase_width2);

		Trajectory right  = modifier2.getRightTrajectory(); 
		return right;
		// Get the Right Side
		}
		else
		return null;
		
	}
	
	
	
}
