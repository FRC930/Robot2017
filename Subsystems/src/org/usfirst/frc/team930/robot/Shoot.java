package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;

public class Shoot implements Runnable {
	
	double speed;
	double targetX;
	double targetY;
	
	public void run(){
		
		System.out.println("Shoot " + Timer.getFPGATimestamp());
		
		//Setting Lights for Shooting
		if(OutputManager.getPDPChannelCurrent(7)>5){
		
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_SHOOT);
		
		}
		else {
			
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_DRIVE);
		
		}
		
	}
	
	public void visionTrack (){
		
	}
	
}
