package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climb implements Runnable {
	
	int counter;
	
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
			
			double yValue = Math.pow(DSManager.getCoDriveYAxis(), Constants.JOYSTICK_NONLINEARITY);
			
			//Setting speed to controller

<<<<<<< HEAD
			SmartDashboard.putNumber("Climb Progress", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL12));
			SmartDashboard.putNumber("Climb Motor Current", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL12));

			//System.out.println("Climb Motor Current " + OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL0));
=======
			SmartDashboard.putNumber("Climb Progress", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL0));
>>>>>>> 9aaf717224454dbd68cea8e48d7e01d15a7bee55
			
			if (Math.abs(yValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE){
	
				OutputManager.setClimberSpeed(-yValue);
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
		
			}
			
			else {
				
				OutputManager.setClimberSpeed(0);
				
			}
		
		}
		
		else if (OutputManager.isRobotAuton()){
			
			}
		
		}
	
	}

