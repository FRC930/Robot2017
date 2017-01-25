package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;



public class Robot extends IterativeRobot {
	
	Joystick stick = new Joystick(0);
	Spark climb1 = new Spark(0);
	
	public void teleopPeriodic() {
		climb1.set(stick.getRawAxis(5));
	}
}
