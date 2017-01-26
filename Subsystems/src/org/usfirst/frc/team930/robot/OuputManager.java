package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import java.lang.Object;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;

public class OuputManager {
	
	// Declaring robot motors
	// 6 Drivetrain motor declarations
	public static CANTalon frontLeftMotor; 	
	public static CANTalon rearLeftMotor;
	public static CANTalon frontRightMotor;
	public static CANTalon rearRightMotor; 
	public static CANTalon leftSlave;
	public static CANTalon rightSlave;
	
	// 1 Shooter Talon declaration
	public static CANTalon shooterMotor;
	
	// 1 Intake  Spark Motor controller declaration
	public static Spark intakeSpark;
	
	// 1 Climber  Spark Motor controller declaration
	public static Spark climberSpark;
	
	// Feeder 1 Spark, either combine with shooter or make own subsystem
	public static Spark feederSpark;
	
	public void init(){
		
		// Initializing motors
		// Initializes Motors for drivetrain
		frontLeftMotor = new CANTalon(1);
		rearLeftMotor = new CANTalon(2);
		frontRightMotor = new CANTalon(3);
		rearRightMotor = new CANTalon(4);
		
		leftSlave = new CANTalon(5);
		rightSlave = new CANTalon(6);
		
		shooterMotor = new CANTalon (7);
		
		// Spark Range is 2.003 ms Full Forward - .999 ms Full Reverse
		intakeSpark = new Spark (8);
		
		climberSpark = new Spark (9);
		
		feederSpark = new Spark (10);
		
	}
	
	// Sets the speed for the motors on the right side of the robot drivetrain.
	public static void setSpeedR(double speed){
		
		frontRightMotor.set(speed);
		rearRightMotor.set(speed);
		rightSlave.set(speed);
		
	}
	
	// Sets the speed for the motors on the left side of the robot drivetrain.
	public static void setSpeedL(double speed){
		
		frontLeftMotor.set(speed);
		rearLeftMotor.set(speed);
		leftSlave.set(speed);
		
	}
	
	// Sets the Drivetrain motors to Speed Mode.
	public static void setDrivetrainSpeedMode(){
		
		frontRightMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rearRightMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		frontLeftMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		rearLeftMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
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
		rearRightMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		rightSlave.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		
		frontLeftMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		rearLeftMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		leftSlave.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
		
	}
	
	public static void setShooterSpeed( double speed ){
		
		shooterMotor.set(speed);
		
	}
	
	public static void setShooterSpeedMode(){
		
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		
	}
	
	public static void setShooterDisabledMode(){
		
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
	}
	
	public static void setShooterPercentVbusMode(){
		
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
	}

	public static void setIntakeSpeed( double speed ){
		
		intakeSpark.set(speed);
		
	}
	
	public static void setClimberSpeed( double speed ){
		
		climberSpark.set(speed);
		
	}
	
}
