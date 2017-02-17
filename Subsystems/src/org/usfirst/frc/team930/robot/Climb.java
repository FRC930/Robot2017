package org.usfirst.frc.team930.robot;
import org.usfirst.frc.team930.robot.OutputManager.LightPatterns;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climb implements Runnable {
	
	int counter;
	
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
		
			double yValue = Math.pow(DSManager.getCoDriveYAxis(), Constants.JOYSTICK_NONLINEARITY);
			
			//System.out.println("Climb " + Timer.getFPGATimestamp());
			
			//Setting speed to controller
			if(DSManager.getCoDriveYAxis() < 0.1){
				counter = 0;
			}
			if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) < Constants.CLIMBER_CUT_OUT_CURRENT){
				
				OutputManager.setClimberSpeed(yValue);
				
			}
			if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) < Constants.CLIMBER_FIRST_INTERVAL_CURRENT && OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) > Constants.CLIMBER_GOING_CURRENT){
					
				counter = 20;
				
			}
				else if (OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) > Constants.CLIMBER_FIRST_INTERVAL_CURRENT && OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) < Constants.CLIMBER_CUT_OUT_CURRENT){
					counter+=2;
			}
				else if (OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) > Constants.CLIMBER_CUT_OUT_CURRENT){
					counter = 100;
			}
		

			SmartDashboard.putNumber("Climb Progress", counter );
			
			SmartDashboard.putNumber("Climb Motor Current", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL5));
			
			//System.out.println("Climb Motor Current" + OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL5));
			
			if (Math.abs(yValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE){
	
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
		
			}
		
		}
		
		else if (OutputManager.isRobotAuton()){
			
			}
		
		}
	
	}

