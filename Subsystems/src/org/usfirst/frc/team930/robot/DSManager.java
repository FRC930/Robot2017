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
	
	private static Joystick stick = new Joystick(Constants.JOYSTICK_ONE_PORT);
	private static Joystick stick2 = new Joystick(Constants.JOYSTICK_TWO_PORT);
	
	private static boolean increaseShooterSpeedBool = false;
	private static boolean decreaseShooterSpeedBool = false;
	//METHODS FOR CONTROLLER ONE
	
	public static double getDriveXAxis(){
		ds.getAlliance();
		return stick.getRawAxis(Constants.DRIVE_X_AXIS_PORT);
	}
	public static double getDriveYAxis(){
		return stick.getRawAxis(Constants.DRIVE_Y_AXIS_PORT);
	}
	public static boolean getDriveRawButtonOne(){
		return stick.getRawButton(Constants.RAW_BUTTON_ONE_PORT);
	}
	public static boolean getDriveRawButtonTwo(){
		return stick.getRawButton(Constants.RAW_BUTTON_TWO_PORT);
	}
	public static boolean getDriveRawButtonThree(){
		return stick.getRawButton(Constants.RAW_BUTTON_THREE_PORT);
	}
	public static boolean getDriveRawButtonFour(){
		return stick.getRawButton(Constants.RAW_BUTTON_FOUR_PORT);
	}
	public static boolean getDriveRawButtonFive(){
		return stick.getRawButton(Constants.RAW_BUTTON_FIVE_PORT);
	}
	public static boolean getDriveRawButtonSix(){
		return stick.getRawButton(Constants.RAW_BUTTON_SIX_PORT);
	}
	public static boolean getDriveShootTrigger(){
		if(stick.getRawAxis(Constants.RAW_AXIS_TRIGGER_RIGHT) > 0.3){
			return true;
		}
		else {
			return false;
		}
	}
	public static double getDriveElevatorTrigger(){
		if(stick.getRawAxis(Constants.RAW_AXIS_TRIGGER_LEFT) > 0.1){
			return stick.getRawAxis(Constants.RAW_AXIS_TRIGGER_LEFT);
		}
		else {
			return 0;
		}
	}
	
	//METHODS FOR CONTROLLER TWO
	
	public static double getCoDriveXAxis(){
		return stick2.getRawAxis(Constants.DRIVE_X_AXIS_PORT);
	}
	public static double getCoDriveYAxis(){
		return stick2.getRawAxis(Constants.DRIVE_Y_AXIS_PORT);
	}
	public static boolean getCoDriveRawButtonOne(){
		return stick2.getRawButton(Constants.RAW_BUTTON_ONE_PORT);
	}
	public static boolean getCoDriveRawButtonTwo(){
		return stick2.getRawButton(Constants.RAW_BUTTON_TWO_PORT);
	}
	public static boolean getCoDriveRawButtonThree(){
		return stick2.getRawButton(Constants.RAW_BUTTON_THREE_PORT);
	}
	public static boolean getCoDriveRawButtonFive(){
		return stick2.getRawButton(Constants.RAW_BUTTON_FIVE_PORT);
	}
	public static boolean getCoDriveRawButtonSix(){
		return stick2.getRawButton(Constants.RAW_BUTTON_SIX_PORT);
	}
	public static boolean getCoDriveRawButtonSeven(){
		return stick2.getRawButton(Constants.RAW_BACK_BUTTON_PORT);
	}
	public static boolean getCoDriveRawButtonEight(){
		return stick2.getRawButton(Constants.RAW_START_BUTTON_PORT);
	}
	public static boolean getCoDriveShootTrigger(){
		if(stick2.getRawAxis(Constants.RAW_AXIS_TRIGGER_RIGHT) > 0.3){
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean increaseShooterSpeed(){
		if( getCoDriveRawButtonTwo() && !increaseShooterSpeedBool){
			increaseShooterSpeedBool = true;
			return !increaseShooterSpeedBool;
		}
		else if (!getCoDriveRawButtonTwo() && increaseShooterSpeedBool){
			increaseShooterSpeedBool = false;
			return !increaseShooterSpeedBool;
		}
		return false;
	}
	public static boolean decreaseShooterSpeed(){
		if( getCoDriveRawButtonThree() && !decreaseShooterSpeedBool){
			decreaseShooterSpeedBool = true;
			return !decreaseShooterSpeedBool;
		}
		else if (!getCoDriveRawButtonThree() && decreaseShooterSpeedBool){
			decreaseShooterSpeedBool = false;
			return !decreaseShooterSpeedBool;
		}
		return false;
	}
}
