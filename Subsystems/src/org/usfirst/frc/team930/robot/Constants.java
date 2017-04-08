package org.usfirst.frc.team930.robot;

public class Constants {
	
	// ----------PDP CHANNELS----------
	
	/*
	 * Shooter: 12
	 * Climb: 5
	 */
	public static final int PDP_CHANNEL0 = 0;
	public static final int PDP_CHANNEL1 = 1;
	public static final int PDP_CHANNEL2 = 2;
	public static final int PDP_CHANNEL3 = 3;
	public static final int PDP_CHANNEL4 = 4;
	public static final int PDP_CHANNEL5 = 5;
	public static final int PDP_CHANNEL6 = 6;
	public static final int PDP_CHANNEL7 = 7;
	public static final int PDP_CHANNEL8 = 8;
	public static final int PDP_CHANNEL9 = 9;
	public static final int PDP_CHANNEL10 = 10;
	public static final int PDP_CHANNEL11 = 11;
	public static final int PDP_CHANNEL12 = 12;
	public static final int PDP_CHANNEL13 = 13;
	public static final int PDP_CHANNEL14 = 14;
	public static final int PDP_CHANNEL15 = 15;
	
	// ----------Talon Motor Channels----------
	
	public static final int L1_MOTOR_CHANNEL = 1;
	public static final int L2_MOTOR_CHANNEL = 2;
	public static final int L3_MOTOR_CHANNEL = 3;
	public static final int R1_MOTOR_CHANNEL = 4;
	public static final int R2_MOTOR_CHANNEL = 5;
	public static final int R3_MOTOR_CHANNEL = 6;
	public static final int SHOOTER_MOTOR_CHANNEL = 7;
	public static final int SHOOTER_MOTOR_CHANNEL2 = 8;
	public static final int GEAR_ARM_CHANNEL = 9;
	
	//-----------Shooter Constants--------------
	
	public static final double BANG_BANG_VARIABLES = 5;
	public static final double FULL_SHOOT_SPEED =3200.0;
	
	// ----------Spark Motor Channels-----------
	
	public static final int INTAKE_MOTOR_CHANNEL = 0;
	public static final int CLIMBER_MOTOR_CHANNEL = 3;
	public static final int CLIMBER2_MOTOR_CHANNEL = 1;
	public static final int ELEVATOR_MOTOR_CHANNEL = 2;
	public static final int ELEVATOR_MOTOR_CHANNEL2 = 5;
	public static final int GEAR_WHEELS_CHANNEL = 6;
	
	// -----------DRIVE CODE CONSTANTS-----------
	
	public static final int JOYSTICK_NONLINEARITY = 3;
	public static final int X_DEFAULT_VALUE = 0;
	public static final int Y_DEFAULT_VALUE = 0;
	public static final double JOYSTICK_ERROR_ALLOWANCE = 0.1;
	
	// ----------JOYSTICK CONSTANTS----------
	
	public static final int JOYSTICK_ONE_PORT = 1;
	public static final int JOYSTICK_TWO_PORT = 2;
	public static final int DRIVE_X_AXIS_PORT = 4;
	public static final int DRIVE_Y_AXIS_PORT = 1;
	public static final int RAW_BUTTON_ONE_PORT = 1;
	public static final int RAW_BUTTON_TWO_PORT = 2;
	public static final int RAW_BUTTON_THREE_PORT = 3;
	public static final int RAW_BUTTON_FOUR_PORT = 4;
	public static final int RAW_BUTTON_FIVE_PORT = 5;
	public static final int RAW_BUTTON_SIX_PORT = 6;
	public static final int RAW_BACK_BUTTON_PORT = 7;
	public static final int RAW_START_BUTTON_PORT = 8;
	public static final int RAW_AXIS_TRIGGER_RIGHT = 3;
	public static final int RAW_AXIS_TRIGGER_LEFT = 2;
	
	// ----------CLIMBER CURRENT CONSTANTS----------
	
	public static final int	CLIMBER_GOING_CURRENT = 2;
	public static final int CLIMBER_FIRST_INTERVAL_CURRENT = 10;
	public static final int CLIMBER_CUT_OUT_CURRENT = 35;
	
	
	// -----------ELEVATOR CONSTANTS------------------
	public static final double ELEVATOR_SPEED_FORWARDS = -0.6;
	public static final double ELEVATOR_SPEED_BACKWARDS = 0.8;
	
	// -----------GEAR ARM/WHEELS------------------
	public static final double GEAR_ARM_UP = 0.575;
	public static final double GEAR_ARM_DOWN = 0.872;
	public static final double GEAR_ARM_MIDDLE = 25;
	public static final double GEAR_WHEELS_SPEED_IN = 0.5;
	public static final double GEAR_WHEELS_SPEED_OUT = -0.5;
}