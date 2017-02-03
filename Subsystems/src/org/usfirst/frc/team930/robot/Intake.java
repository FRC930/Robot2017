package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Intake implements Runnable {
   
	public void run(){
		
		System.out.println("Intake " + Timer.getFPGATimestamp());
		//Constructors
		
		Joystick stick = new Joystick(1);	//Controller
		
		CANTalon intakeSpark = new CANTalon(8);		//Talon
		
		boolean toggleOn = false;
		intakeSpark.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			
			if (stick.getRawButton(1) && !toggleOn) {
					toggleOn = true;
					intakeSpark.set(-1);
				}
			else if(!stick.getRawButton(1) && toggleOn) {
				toggleOn = false;
				intakeSpark.set(0);	
			}	
		}
	}
	

