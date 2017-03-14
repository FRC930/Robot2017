package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive implements Runnable {

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
			
<<<<<<< HEAD
=======
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
			
>>>>>>> 9aaf717224454dbd68cea8e48d7e01d15a7bee55
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
<<<<<<< HEAD
			// Adjusting joystick sensitivity
			xValue = Math.pow(DSManager.getDriveXAxis(), Constants.JOYSTICK_NONLINEARITY);
			yValue = Math.pow(DSManager.getDriveYAxis() * -1.0, Constants.JOYSTICK_NONLINEARITY);
								
			SmartDashboard.putNumber("Left stick", DSManager.getDriveYAxis());
			SmartDashboard.putNumber("Right stick", DSManager.getDriveXAxis());
			SmartDashboard.putNumber("Left commanded", OutputManager.getCommandedSpeed(OutputManager.Motors.L1MASTER));
			SmartDashboard.putNumber("Right commanded", OutputManager.getCommandedSpeed(OutputManager.Motors.R1MASTER));
=======
			
			if (xValue > 1)
				xValue = 1;
			if (xValue < -1)
				xValue = -1;
			if (yValue >1)
				yValue = 1;
			if(yValue < -1)
				yValue = -1;
>>>>>>> 9aaf717224454dbd68cea8e48d7e01d15a7bee55
			
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
	
}
