package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;

public class Shoot implements Runnable {
	
	double speed;
	double targetX;
	double targetY;
	
	public void run(){
		
		System.out.println("Shoot " + Timer.getFPGATimestamp());
		
	}
	
	public void setSpeed(double x, double y){
		
	}
	
	public double getSpeed(){
		
		return speed;
		
	}
	
	public void visionTrack (){
		
	}
	
}
