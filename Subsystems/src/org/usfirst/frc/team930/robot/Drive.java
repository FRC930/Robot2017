package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;

public class Drive implements Runnable {
	
    double targetX;
    double targetY;
    boolean useJoysticks;
    
	public void run(){
		
		System.out.println("Drive " + Timer.getFPGATimestamp());
		
	}
	
	public void setTarget(double x, double y){
		
	}
	
	public void setUseJoysticks( boolean b){
		
	}
	
	public void drive(){
		
	}
	
}
