package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SerialPort;



public class Robot extends IterativeRobot {
	Joystick stick = new Joystick(0);
	DigitalOutput pin0 = new DigitalOutput(0);
	DigitalOutput pin1 = new DigitalOutput(1);
	DigitalOutput pin2 = new DigitalOutput(2);
	
	@Override
	public void robotInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		
		if( stick.getRawButton(1)){
		pin0.set(false);
		pin1.set(false);
		pin2.set(false);
		
	}
	if( stick.getRawButton(2)){
		pin0.set(true);
		pin1.set(false);
		pin2.set(false);
		
	}
	if( stick.getRawButton(3)){
		pin0.set(true);
		pin1.set(false);
		pin2.set(true);
		
	}
	if( stick.getRawButton(4)){
		pin0.set(true);
		pin1.set(true);
		pin2.set(true);
		
		
	}
}
}
