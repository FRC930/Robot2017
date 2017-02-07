package org.usfirst.frc.team930.robot;
import org.usfirst.frc.team930.robot.OutputManager.LightPatterns;

import edu.wpi.first.wpilibj.Timer;

public class Climb implements Runnable {
  
	public void run(){
		
		System.out.println("Climb " + Timer.getFPGATimestamp());
	
		if(OutputManager.getPDPChannelCurrent(5) < 35){
		
		OutputManager.setClimberSpeed(DSManager.getDriveYAxis());
	
		}
		OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
	}
	
}
