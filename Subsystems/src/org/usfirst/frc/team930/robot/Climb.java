package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;

public class Climb implements Runnable {
  
	public void run(){
		
	System.out.println("Climb " + Timer.getFPGATimestamp());
	
	OutputManager.setClimberSpeed(DSManager.getRawAxisOne());
	
	}
	
}
