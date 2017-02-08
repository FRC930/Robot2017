package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;

public class Shoot implements Runnable {
	
	double speed;
	double targetX;
	double targetY;
	
	public void run(){
		
		System.out.println("Shoot " + Timer.getFPGATimestamp());
		
		//Setting Lights for Shooting
		if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL7)>5){
		
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_SHOOT);
		
		}
			
	}
	
	public void visionTrack (){
		
	}
	
}
