package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Timer;

public class DrivetrainTeleop {
		myRobot.arcadeDrive(stick);
		
		double wheel = stick.getRawAxis(0);
		double throttle = stick.getRawAxis(1);
		
		double wheelNonLinearity = 0.5;

		wheel = handleDeadband(wheel, 0.1);
		throttle = handleDeadband(throttle, 0.085);
		
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		
		L1.set(throttle + wheel);
		L2.set(throttle + wheel);
		L3.set(throttle + wheel);
		R1.set(throttle - wheel);
		R2.set(throttle - wheel);
		R3.set(throttle - wheel);
		
		/* Joystick values to gyro values
		double angleGyro = gyro.getAngle()%360;
		System.out.println(angleGyro);
		
		double angleStick = Math.toDegrees(Math.atan2((stick.getRawAxis(1)), (stick.getRawAxis(0))));
		System.out.println(angleStick);*/
		
		Timer.delay(0.005);
	

	private double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;		// boolean statement ? true result : false result
	}
}
