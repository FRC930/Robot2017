package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Joystick stick = new Joystick(1);
	
	//AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
	CANTalon L1 = new CANTalon(1);		// Right F: 0.945
	CANTalon L2 = new CANTalon(2);		// Right P: 0.4
	CANTalon L3 = new CANTalon(3);		// Right I: 0.00025
	CANTalon R1 = new CANTalon(4);
	CANTalon R2 = new CANTalon(5);
	CANTalon R3 = new CANTalon(6);
	
	Spark intakeMotor = new Spark(1);
	boolean motorOn = false;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		L1.setInverted(true);
		L2.setInverted(true);
		L3.setInverted(true);
		
		L1.changeControlMode(CANTalon.TalonControlMode.Speed);
		L2.changeControlMode(CANTalon.TalonControlMode.Follower);
		L3.changeControlMode(CANTalon.TalonControlMode.Follower);
		R1.changeControlMode(CANTalon.TalonControlMode.Speed);
		R2.changeControlMode(CANTalon.TalonControlMode.Follower);
		R3.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		L2.set(L1.getDeviceID());
		L3.set(L1.getDeviceID());
		R2.set(R1.getDeviceID());
		R3.set(R1.getDeviceID());
		
		L1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		R1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		L1.reverseSensor(true);
		
		L1.configEncoderCodesPerRev(250);
		R1.configEncoderCodesPerRev(250);
		
		// At 200 slammed forward and backward no drop outs and driving responsive
		//L1.setVoltageRampRate(1600);
		//R1.setVoltageRampRate(1600);

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		// Intake
		if (stick.getRawButton(5) || stick.getRawButton(6) ) {
			intakeMotor.set(1);
		}
		else {
			intakeMotor.set(0);
		}
		
		// Adjusting joystick sensitivity
		double xValue = Math.pow(stick.getRawAxis(4), 3);
		double yValue = Math.pow(stick.getRawAxis(1) * -1.0, 3);
		
		// Deadband
		if (Math.abs(xValue) < 0.1) {
			xValue = 0;
		}
		
		if (Math.abs(yValue) < 0.1) {
			yValue = 0;
		}
		
		// Setting talons
		//L1.set(yValue + xValue);
		//L2.set(yValue + xValue);
		//L3.set(yValue + xValue);
		//R1.set(yValue - xValue);
		//R2.set(yValue - xValue);
		//R3.set(yValue - xValue);
		
		if(stick.getRawButton(1) == true) {
			//L1.set(0.25 * 600);
			R1.set(0.25 * 600);
		}
		else if(stick.getRawButton(2) == true) {
			//L1.set(0.5 * 600);
			R1.set(0.5 * 600);
		}
		else if(stick.getRawButton(3) == true) {
			//L1.set(0.75 * 600);
			R1.set(0.75 * 600);
		}
		else if(stick.getRawButton(4) == true) {
			//L1.set(1.0 * 600);
			R1.set(1.0 * 600);
		}
		else {
			//L1.set(0);
			R1.set(0);
		}
		
		double leftSpeed = L1.getSpeed();
		double rightSpeed = R1.getSpeed();
		System.out.println("Left Speed: " + leftSpeed);
		System.out.println("Right Speed: " + rightSpeed);
		
		double leftEncoder = L1.getEncVelocity();
		double rightEncoder = R1.getEncVelocity();
		//System.out.println("Left Encoder Value: " + leftEncoder);
		//System.out.println("Right Encoder Value: " + rightEncoder);
		
		/* Joystick values to gyro values
		double angleGyro = gyro.getAngle()%360;
		System.out.println(angleGyro);
		
		double angleStick = Math.toDegrees(Math.atan2((stick.getRawAxis(1)), (stick.getRawAxis(0))));
		System.out.println(angleStick);*/

		Timer.delay(0.005);
	}

	public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

