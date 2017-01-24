package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;

public class Intake implements Runnable {
   
	public void run(){
		
		System.out.println("Intake " + Timer.getFPGATimestamp());
		
	}
	
}
