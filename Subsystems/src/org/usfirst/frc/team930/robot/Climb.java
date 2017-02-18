package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climb implements Runnable {
	
	int counter;
	
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
			
			double yValue = Math.pow(DSManager.getCoDriveYAxis(), Constants.JOYSTICK_NONLINEARITY);
			
			//System.out.println("Climb " + Timer.getFPGATimestamp());
			
			//Setting speed to controller

			SmartDashboard.putNumber("Climb Progress", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL0));
			
			SmartDashboard.putNumber("Climb Motor Current", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL0));

			System.out.println("Climb Motor Current " + OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL0));
			
			if (Math.abs(yValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE){
	
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
		
			}
		
		}
		
		else if (OutputManager.isRobotAuton()){
			
			}
		
		}
	
	}

