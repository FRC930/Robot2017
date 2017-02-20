package org.usfirst.frc.team930.robot;
import org.usfirst.frc.team930.robot.OutputManager.LightPatterns;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climb implements Runnable {
  
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
		
			double yValue = Math.pow(DSManager.getDriveYAxis(), Constants.JOYSTICK_NONLINEARITY);
			
			//System.out.println("Climb " + Timer.getFPGATimestamp());
			
			//Setting speed to controller
			
			/*if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) < 35){
				
				OutputManager.setClimberSpeed(DSManager.getCoDriveYAxis());
		
			}
			*/
			SmartDashboard.putNumber("Climb Motor Current", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL5));
			
			//System.out.println("Climb Motor Current" + OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL5));
			
			OutputManager.setClimberSpeed(DSManager.getCoDriveYAxis());
			
			if (Math.abs(yValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE){
	
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
		
			}
		
		}
		
		else if (OutputManager.isRobotAuton()){
			
		}
		
	}
	
}
