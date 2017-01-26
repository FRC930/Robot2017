package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.JoystickBase;
import edu.wpi.first.wpilibj.Joystick;
// This class instatiates Objects that will be used to get data for the robot to use. 
public class DSManager {

	// Instantiates the Driver Station as ds
	DriverStation ds = DriverStation.getInstance();
	
	// Instantiates the Joystick as stick
	Joystick stick = new Joystick(1);
	
}
