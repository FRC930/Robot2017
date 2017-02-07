package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Robot extends IterativeRobot {
	
	Joystick stick = new Joystick(0);
	Spark climb1 = new Spark(0);
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	public void teleopPeriodic() {
		if(PDP.getCurrent(6)>0){
		climb1.set(stick.getRawAxis(5));
		SmartDashboard.putNumber("Rope Climb Motor Power %", climb1.get()*100);
		}
	}
}
