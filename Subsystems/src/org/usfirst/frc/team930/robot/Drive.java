package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive implements Runnable {

	private static Timer time = new Timer();
	
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
			double xValue = Math.pow(DSManager.getDriveXAxis(), Constants.JOYSTICK_NONLINEARITY);
			double yValue = Math.pow(DSManager.getDriveYAxis() * -1.0, Constants.JOYSTICK_NONLINEARITY);
					
			// Deadband
			/*if (Math.abs(DSManager.getDriveXAxis()) < Constants.JOYSTICK_ERROR_ALLOWANCE) {
				xValue = Constants.X_DEFAULT_VALUE;
			}
			if (Math.abs(DSManager.getDriveYAxis()) < Constants.JOYSTICK_ERROR_ALLOWANCE) {
				yValue = Constants.Y_DEFAULT_VALUE;
			}*/
			if (!(Math.abs(xValue) < Constants.JOYSTICK_ERROR_ALLOWANCE)&& !(Math.abs(yValue) < Constants.JOYSTICK_ERROR_ALLOWANCE)) {
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_DRIVE);
			}
			
			SmartDashboard.putNumber("Left stick", DSManager.getDriveYAxis());
			SmartDashboard.putNumber("Right stick", DSManager.getDriveXAxis());
			SmartDashboard.putNumber("Left commanded", OutputManager.getCommandedSpeed(OutputManager.Motors.L1MASTER));
			SmartDashboard.putNumber("Right commanded", OutputManager.getCommandedSpeed(OutputManager.Motors.R1MASTER));
			
			// Setting talons
			OutputManager.setSpeedL(yValue + xValue);
			OutputManager.setSpeedR(yValue - xValue);
			//System.out.println("r: " + OutputManager.getCommandedSpeed(OutputManager.Motors.R1MASTER));
			//System.out.println("l: "+OutputManager.getCommandedSpeed(OutputManager.Motors.L1MASTER));
		}
		else if (OutputManager.isRobotAuton()){
			
	    	//CANTalon.SetValueMotionProfile setOutputLeft = OutputManager.motionProfilerLeft.getSetValue();
			//OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
			
			//CANTalon.SetValueMotionProfile setOutputRight = OutputManager.motionProfilerRight.getSetValue();
			//OutputManager.setRightDrivetrainCustomMode(setOutputRight);
			
			//OutputManager.motionProfilerLeft.control();
			//OutputManager.motionProfilerRight.control();
				
			if (time.get() > 3) {
						
					OutputManager.setSpeedL(0);
					OutputManager.setSpeedR(0);
			}
				
			else {
					
					OutputManager.setSpeedL(0.25);
					OutputManager.setSpeedR(0.25);
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
