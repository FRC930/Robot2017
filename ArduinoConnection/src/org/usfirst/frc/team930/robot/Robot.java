package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SerialPort;



public class Robot extends IterativeRobot {
	Joystick stick = new Joystick(0);
	DigitalOutput lightPin0 = new DigitalOutput(0);
	DigitalOutput lightPin1 = new DigitalOutput(1);
	DigitalOutput lightPin2 = new DigitalOutput(2);
	
	@Override
	public void robotInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		
		if( stick.getRawButton(1)){
		lightPin0.set(true);
			lightPin1.set(false);
			lightPin2.set(false);
		System.out.println("Button 1");
	}
	if( stick.getRawButton(2)){
		lightPin0.set(true);
			lightPin1.set(true);
			lightPin2.set(true);
		System.out.println("Button 2");
		
	}
	if( stick.getRawButton(3)){
		lightPin0.set(false);
			lightPin1.set(false);
			lightPin2.set(false);
		
	}
	if( stick.getRawButton(4)){
		lightPin0.set(true);
			lightPin1.set(false);
			lightPin2.set(true);
		
		
	}
	if( stick.getRawButton(5)){
		lightPin0.set(false);
			lightPin1.set(false);
			lightPin2.set(true);
		
	}
	if( stick.getRawButton(6)){
		lightPin0.set(true);
			lightPin1.set(true);
			lightPin2.set(false);
		
		
	}
	if( stick.getRawButton(7)){
		lightPin0.set(false);
			lightPin1.set(true);
			lightPin2.set(false);
		
		
	}
	if( stick.getRawButton(8)){
		lightPin0.set(false);
			lightPin1.set(true);
			lightPin2.set(true);
		
		
	}
}
}
