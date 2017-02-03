package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.JoystickBase;
import edu.wpi.first.wpilibj.Joystick;
// This class instatiates Objects that will be used to get data for the robot to use. 
public class DSManager {

	// Instantiates the Driver Station as ds
	private static DriverStation ds = DriverStation.getInstance();
	
	// Instantiates the Joystick as stick
	private static Joystick stick = new Joystick(1);
	
	public static double getRawAxisZero(){
		return stick.getRawAxis(0);
	}
	
	public static double getRawAxisOne(){
		return stick.getRawAxis(1);
	}
	public static boolean getRawButtonOne(){
		return stick.getRawButton(1);
	}
}
