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
	private static Joystick stick2 = new Joystick(2);
	
	//METHODS FOR CONTROLLER ONE
	
	public static double getDriveXAxis(){
		return stick.getRawAxis(4);
	}
	public static double getDriveYAxis(){
		return stick.getRawAxis(1);
	}
	public static boolean getRawButtonOne(){
		return stick.getRawButton(1);
	}
	public static boolean getRawButtonFive(){
		return stick.getRawButton(5);
	}
	public static boolean getRawButtonSix(){
		return stick.getRawButton(6);
	}
	
	//METHODS FOR CONTROLLER TWO
	
	public static double getCoDriveXAxis(){
		return stick2.getRawAxis(4);
	}
	public static double getCoDriveYAxis(){
		return stick2.getRawAxis(1);
	}
	public static boolean getCoDriveRawButtonOne(){
		return stick2.getRawButton(1);
	}
	public static boolean getCoDriveRawButtonFive(){
		return stick2.getRawButton(5);
	}
	public static boolean getCoDriveRawButtonSix(){
		return stick2.getRawButton(6);
	}
}
