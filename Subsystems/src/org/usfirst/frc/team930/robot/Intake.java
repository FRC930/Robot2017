package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake implements Runnable {
   
	public void run(){
		
		System.out.println("Intake " + Timer.getFPGATimestamp());
		
		//Setting lights for the Intake
	
		if(DSManager.getCoDriveRawButtonOne()){
		
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_INTAKE);
	
		}
		
	}
	
}
	

