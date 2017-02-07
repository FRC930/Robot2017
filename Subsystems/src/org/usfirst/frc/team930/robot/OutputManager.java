package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import java.lang.Object;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;

public class OutputManager {
	
	//Enum for Light Patterns on Arduino
	 enum LightPatterns {
		 LIGHTS_AUTO,
		 LIGHTS_TELE,
		 LIGHTS_DISABLE,
		 LIGHTS_SHOOT,
		 LIGHTS_DRIVE,
		 LIGHTS_CLIMB,
		 LIGHTS_INTAKE,
		 LIGHTS_FUUN
	 }
	 
	 // Declaring Pins on RoboRio
	private static	DigitalOutput lightPin0 = new DigitalOutput(0);
	private static	DigitalOutput lightPin1 = new DigitalOutput(1);
	private static	DigitalOutput lightPin2 = new DigitalOutput(2);
	
	//PDP Class used to monitor current
	private static	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	// Declaring robot motors
	// 6 Drivetrain motor declarations
	private static CANTalon frontLeftMotor; 	
	private static CANTalon rearLeftMotor;
	private static CANTalon frontRightMotor;
	private static CANTalon rearRightMotor; 
	private static CANTalon leftSlave;
	private static CANTalon rightSlave;
	
	// 1 Shooter Talon declaration
	private static CANTalon shooterMotor;
	
	// 1 Intake  Spark Motor controller declaration
	private static Spark intakeSpark;
	
	// 1 Climber  Spark Motor controller declaration
	private static Spark climberSpark;
	
	// Feeder 1 Spark, either combine with shooter or make own subsystem
	private static Spark feederSpark;
	
	public static void init(){
		
		// Initializing motors
		// Initializes Motors for drivetrain
		frontLeftMotor = new CANTalon(Constants.L1_MOTOR_CHANNEL);
		rearLeftMotor = new CANTalon(Constants.L3_MOTOR_CHANNEL);
		leftSlave = new CANTalon(Constants.L2_MOTOR_CHANNEL);
		
		frontLeftMotor.setInverted(true);
		rearLeftMotor.setInverted(true);
		leftSlave.setInverted(true);
		
		frontLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeftMotor.reverseSensor(false);
		frontLeftMotor.configEncoderCodesPerRev(250);
		frontLeftMotor.setVoltageRampRate(6400);
		
		frontRightMotor = new CANTalon(Constants.R1_MOTOR_CHANNEL);
		rearRightMotor = new CANTalon(Constants.R3_MOTOR_CHANNEL);
		rightSlave = new CANTalon(Constants.R2_MOTOR_CHANNEL);
		
		frontRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRightMotor.configEncoderCodesPerRev(250);
		frontRightMotor.setVoltageRampRate(6400);
		
		shooterMotor = new CANTalon (Constants.SHOOTER_MOTOR_CHANNEL);
		
		// Spark Range is 2.003 ms Full Forward - .999 ms Full Reverse
		intakeSpark = new Spark (Constants.INTAKE_MOTOR_CHANNEL);
		
		climberSpark = new Spark (Constants.CLIMBER_MOTOR_CHANNEL);
		
		feederSpark = new Spark (Constants.FEEDER_MOTOR_CHANNEL);
		
	}
	
	// Sets the speed for the motors on the right side of the robot drivetrain.
	public static void setSpeedR(double speed){
		
		frontRightMotor.set(speed);

		
	}
	
	// Sets the speed for the motors on the left side of the robot drivetrain.
	public static void setSpeedL(double speed){
		
		frontLeftMotor.set(speed);

		
	}
	
	// Sets the Drivetrain motors to Speed Mode.
	public static void setDrivetrainSpeedMode(){
		
		frontRightMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rearRightMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		rearRightMotor.set(frontRightMotor.getDeviceID());
		rightSlave.set(frontRightMotor.getDeviceID());
		
		frontLeftMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rearLeftMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		rearLeftMotor.set(frontLeftMotor.getDeviceID());
		leftSlave.set(frontLeftMotor.getDeviceID());
	}
	
	// Sets the Drivetrain motors to Disabled Mode.
	public static void setDrivetrainDisabledMode(){
		
		frontRightMotor.changeControlMode(CANTalon.TalonControlMode.Disabled);
		rearRightMotor.changeControlMode(CANTalon.TalonControlMode.Disabled);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
		frontLeftMotor.changeControlMode(CANTalon.TalonControlMode.Disabled);
		rearLeftMotor.changeControlMode(CANTalon.TalonControlMode.Disabled);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
	}
	
	// Sets the Drivetrain motors to PercentVbus Mode.
	public static void setDrivetrainPercentVbusMode(){
		
		frontRightMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		rearRightMotor.changeControlMode(CANTalon.TalonControlMode.Follower );
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower );
		
		rearRightMotor.set(frontRightMotor.getDeviceID());
		rightSlave.set(frontRightMotor.getDeviceID());
		
		frontLeftMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		rearLeftMotor.changeControlMode(CANTalon.TalonControlMode.Follower );
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower );
		
		rearLeftMotor.set(frontLeftMotor.getDeviceID());
		leftSlave.set(frontLeftMotor.getDeviceID());
		
	}
	
	// Mutator method to set the speed of the shooter [-1,1]
	public static void setShooterSpeed( double speed ){
		
		shooterMotor.set(speed);
		
	}
	
	// Changes the mode of the shooter to speed
	public static void setShooterSpeedMode(){
		
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		
	}
	
	// Changes the mode of the shooter to disabled
	public static void setShooterDisabledMode(){
		
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
	}
	
	// Changes the mode of the shooter to PercentVbus
	public static void setShooterPercentVbusMode(){
		
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
	}

	// Mutator method to set the speed of the intake motor [-1,1]
	public static void setIntakeSpeed( double speed ){
		
		intakeSpark.set(speed);
		
	}
	// Mutator method to set the speed of the climber motor [-1,1]
	public static void setClimberSpeed( double speed ){
		
		climberSpark.set(speed);
		
	}
	
	// Series of Talon accessor classes, mainly used in loggable.
	// Return a string value of the speed units will be in the sensor's native ticks per 100ms.
	public static double getTalonSpeedFrontRightMotor(){
		
		return frontRightMotor.getSpeed();
				
	}
	
	public static double getTalonShooterMotor(){
		
		return shooterMotor.getSpeed();
				
	}
	
	public static double getTalonSpeedFrontLeftMotor(){
		
		return frontLeftMotor.getSpeed();
				
	}
	
	public static double getTalonSpeedRearRightMotor(){
		
		return rearRightMotor.getSpeed();
				
	}
	
	public static double getTalonSpeedRearLeftMotor(){
		
		return rearLeftMotor.getSpeed();
				
	}
	
	public static double getTalonSpeedLeftSlave(){
		
		return leftSlave.getSpeed();
				
	}
	
	public static double getTalonSpeedRightSlave(){
		
		return rightSlave.getSpeed();
				
	}
	
	// Series of Spark accessors to return speed used in loggable
	// Return a string value of the set speed value on [-1,1]
	public static double getSparkSpeedIntake(){
		
		return intakeSpark.getSpeed();
		
	}
	
	public static double getSparkSpeedClimber(){
		
		return climberSpark.getSpeed();
		
	}
	
	public static double getSparkSpeedFeeder(){
		
		return feederSpark.getSpeed();
		
	}
	public static double getPDPChannelCurrent(int Channel){
		
		return PDP.getCurrent(Channel);
		
	}
	public static void setLights(LightPatterns L){
		switch(L){
		
		case LIGHTS_AUTO:	
			lightPin0.set(true);
			lightPin1.set(true);
			lightPin2.set(false);
			break;
		
		
		case LIGHTS_TELE:	
			lightPin0.set(false);
			lightPin1.set(false);
			lightPin2.set(false);
			break;
		
		
		case LIGHTS_DISABLE:	
			lightPin0.set(true);
			lightPin1.set(false);
			lightPin2.set(false);
			break;
		
		
		case LIGHTS_SHOOT:	
			lightPin0.set(true);
			lightPin1.set(true);
			lightPin2.set(true);
			break;
			
			
		case LIGHTS_CLIMB:	
			lightPin0.set(true);
			lightPin1.set(false);
			lightPin2.set(true);
			break;
			
			
		case LIGHTS_DRIVE:	
			lightPin0.set(false);
			lightPin1.set(true);
			lightPin2.set(true);
			break;
	
			
		case LIGHTS_INTAKE:	
			lightPin0.set(false);
			lightPin1.set(true);
			lightPin2.set(false);
			break;
			
			
		case LIGHTS_FUUN:	
			lightPin0.set(false);
			lightPin1.set(false);
			lightPin2.set(true);
			break;
		}
		// Why is there no false true true?
	}
}