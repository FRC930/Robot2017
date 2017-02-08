package org.usfirst.frc.team930.robot;
import org.usfirst.frc.team930.robot.OutputManager.LightPatterns;

import edu.wpi.first.wpilibj.Timer;

public class Climb implements Runnable {
  
	public void run(){
		
		System.out.println("Climb " + Timer.getFPGATimestamp());
		
		//Setting speed to controller
		OutputManager.setClimberSpeed(DSManager.getCoDriveYAxis());
		
		//Setting lights for the climb
		if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL8) < 35){
		
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
	
		}
		else {
			
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_DRIVE);
		
		}
		
	}
	
}
