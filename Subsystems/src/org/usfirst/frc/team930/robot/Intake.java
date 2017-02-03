package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake implements Runnable {
   
	public void run(){
		
		System.out.println("Intake " + Timer.getFPGATimestamp());
		//Constructors
		
		Spark intakeSpark = new Spark(0);		//Talon
		
		boolean toggleOn = false;
		
			
			if (DSManager.getRawButtonOne() && !toggleOn) {
					toggleOn = true;
					intakeSpark.set(-1);
					System.out.println("On");
				}
			else if(!DSManager.getRawButtonOne() && toggleOn) {
				toggleOn = false;
				intakeSpark.set(0);	
				System.out.println("Off");
			}	
		}
	}
	

