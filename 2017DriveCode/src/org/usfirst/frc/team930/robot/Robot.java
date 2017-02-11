package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

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
	
	// Real FPID values
	/*
	 * Left:
	 * F = 0.945
	 * P = 0.4
	 * I = 0.00035
	 * 
	 * Right:
	 * F = 0.945
	 * P = 0.4
	 * I = 0.00035
	 */
	
	CANTalon L1 = new CANTalon(1);		// Right F: 0.945
	CANTalon L2 = new CANTalon(2);		// Right P: 0.4
	CANTalon L3 = new CANTalon(3);		// Right I: 0.00035
	CANTalon R1 = new CANTalon(4);
	CANTalon R2 = new CANTalon(5);		// Left F: 1
	CANTalon R3 = new CANTalon(6);		// Left P: 0.1
										// Left I: 0.0012
	Spark intakeMotor = new Spark(1);
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		L1.setInverted(true);
		L2.setInverted(true);
		L3.setInverted(true);
		
		L1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		L2.changeControlMode(CANTalon.TalonControlMode.Follower);
		L3.changeControlMode(CANTalon.TalonControlMode.Follower);
		R1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		R2.changeControlMode(CANTalon.TalonControlMode.Follower);
		R3.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		L2.set(L1.getDeviceID());
		L3.set(L1.getDeviceID());
		R2.set(R1.getDeviceID());
		R3.set(R1.getDeviceID());
		
		L1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		R1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		L1.setAllowableClosedLoopErr(0);		// F: 0.97
		R1.setAllowableClosedLoopErr(0);		// 30, 35, 35, 40
												// F: 0.975
		L1.reverseSensor(false);				// 30, 30, 35, 40
												// F: 0.98
		L1.configEncoderCodesPerRev(250);		// 30, 30, 30, 40
		R1.configEncoderCodesPerRev(250);
		
		// At 200 slammed forward and backward no drop outs and driving responsive
		//L1.setVoltageRampRate(6400);
		//R1.setVoltageRampRate(6400);

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
		if (stick.getRawButton(5) || stick.getRawButton(6)) {
			intakeMotor.set(1);
		}
		else if (stick.getRawButton(1)) {
			intakeMotor.set(-1);
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
		
		// Left drive & right drive Speed mode
		double leftDrive = (yValue + xValue) * 570;
		double rightDrive = (yValue - xValue) * 570;
		
		if (Math.abs(leftDrive) > 570) {
			leftDrive = 600 * Math.signum(leftDrive);
		}
		
		if (Math.abs(rightDrive) > 570) {
			rightDrive = 600 * Math.signum(rightDrive);
		}
		
		// Setting talons
		
		// Speed mode
		//L1.set(leftDrive);
		//R1.set(rightDrive);
		
		// PercenVbus mode
		L1.set(yValue + xValue);
		R1.set(yValue - xValue);
		
		// Left Side & Right Side Speed mode
		//System.out.println(Math.round(leftDrive) + " " + Math.round(L1.getSpeed() * -1.0) + " " + Math.round(leftDrive - (L1.getSpeed() * -1.0)) + "          " + Math.round(rightDrive) + " " + Math.round(R1.getSpeed()) + " " + Math.round(rightDrive - R1.getSpeed()));
		
		// Left Side & Right Side PercentVbus mode
		//System.out.println(Math.round(yValue + xValue) + " " + Math.round(L1.getSpeed() * -1.0) + " " + Math.round((yValue + xValue) - (L1.getSpeed() * -1.0)) + "          " + Math.round(yValue - xValue) + " " + Math.round(R1.getSpeed()) + " " + Math.round((yValue - xValue) - R1.getSpeed()) + "          " + Timer.getFPGATimestamp());
		
		// Right Side PercentVbus mode
		//System.out.println(Math.round(yValue - xValue) + "," + (R1.getSpeed()) + "," + ((yValue - xValue) - R1.getSpeed()) + "," + R1.getOutputVoltage() + "," + pdp.getCurrent(0) + "," + pdp.getVoltage() + "," + Timer.getFPGATimestamp());
		
		// Left Side & Right Side PercentVbus mode
		System.out.println(Math.round(yValue + xValue) + "," + (L1.getSpeed()) + "," + ((yValue + xValue) - L1.getSpeed()) + "," + L1.getOutputVoltage() + "," + pdp.getCurrent(15) + "," + pdp.getVoltage() + "," + L1.getBusVoltage() + "," + Math.round(yValue - xValue) + "," + (R1.getSpeed()) + "," + ((yValue - xValue) - R1.getSpeed()) + "," + R1.getOutputVoltage() + "," + pdp.getCurrent(0) + "," + pdp.getVoltage() + "," + R1.getBusVoltage() + "," + Timer.getFPGATimestamp());

		/*double percent25 = 0.25 * 570;
		double percent50 = 0.50 * 570;
		double percent75 = 0.75 * 570;
		double percent100 = 1.0 * 570;
		
		double leftSent;
		double rightSent;
		
		if(stick.getRawButton(1)) {
			L1.set(percent25);
			R1.set(percent25);
			leftSent = percent25;
			rightSent = percent25;
		}
		else if(stick.getRawButton(2)) {
			L1.set(percent50);
			R1.set(percent50);
			leftSent = percent50;
			rightSent = percent50;
		}
		else if(stick.getRawButton(3)) {
			L1.set(percent75);
			R1.set(percent75);
			leftSent = percent75;
			rightSent = percent75;
		}
		else if(stick.getRawButton(4)) {
			L1.set(percent100);
			R1.set(percent100);
			leftSent = percent100;
			rightSent = percent100;
		}
		else {
			L1.set(0);
			R1.set(0);
			leftSent = 0;
			rightSent = 0;
		}*/
		
		// Left Side & Right Side Speed mode Buttons
		//System.out.println(Math.round(leftSent) + " " + Math.round(L1.getSpeed() * -1.0) + " " + Math.round(leftSent - (L1.getSpeed() * -1.0)) + "          " + Math.round(rightSent) + " " + Math.round(R1.getSpeed()) + " " + Math.round(rightSent - R1.getSpeed()));
		
		// Left & Right Motor Output
		/*double leftSpeed = L1.getSpeed();
		double rightSpeed = R1.getSpeed();
		System.out.println("Left Speed: " + leftSpeed);
		System.out.println("Right Speed: " + rightSpeed);*/
		
		// Left & Right Encoder Output
		/*double leftEncoder = L1.getEncVelocity();
		double rightEncoder = R1.getEncVelocity();
		System.out.println("Left Encoder Value: " + leftEncoder);
		System.out.println("Right Encoder Value: " + rightEncoder);*/
		
		/* Joystick values to gyro values
		double angleGyro = gyro.getAngle()%360;
		System.out.println(angleGyro);
		
		double angleStick = Math.toDegrees(Math.atan2((stick.getRawAxis(1)), (stick.getRawAxis(0))));
		System.out.println(angleStick);*/

		Timer.delay(0.005);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

