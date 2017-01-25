package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a sample program demonstrating how to use an ultrasonic sensor and
 * proportional control to maintain a set distance from an object.
 */

public class Robot extends IterativeRobot {
	// distance in inches the robot wants to stay from an object
	

	
	/**
	 * Tells the robot to drive to a set distance (in inches) from an object
	 * using proportional control.
	 */
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Hi", 6);
	}
}
