package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
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
	/*final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();*/
	
	//RobotDrive myRobot = new RobotDrive(0, 1);
	
	Joystick stick = new Joystick(1);
	//Button driver4 = new JoystickButton(stick, 4);
	//Button driver1 = new JoystickButton(stick, 1);
	
	//AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
	CANTalon L1 = new CANTalon(1);
	CANTalon L2 = new CANTalon(2);
	CANTalon L3 = new CANTalon(3);
	CANTalon R1 = new CANTalon(4);
	CANTalon R2 = new CANTalon(5);
	CANTalon R3 = new CANTalon(6);

	// MotionProfileExample example = new MotionProfileExample(_talon);

	//boolean[] btnsLast = {false,false,false,false,false,false,false,false,false,false};
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		/*chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);*/
		
		//R1.setInverted(true);
		//R2.setInverted(true);
		//R3.setInverted(true);
		
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
		
		// At 200 slammed forward and backward no drop outs and driving responsive
		L1.setVoltageRampRate(1600);
		R1.setVoltageRampRate(1600);

		/*L1.changeControlMode(CANTalon.TalonControlMode.Speed);
		L2.changeControlMode(CANTalon.TalonControlMode.Follower);
		L3.changeControlMode(CANTalon.TalonControlMode.Follower);
		R1.changeControlMode(CANTalon.TalonControlMode.Speed);
		R2.changeControlMode(CANTalon.TalonControlMode.Follower);
		R3.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		L2.set(L1.getDeviceID());
		L3.set(L1.getDeviceID());
		R2.set(R1.getDeviceID());
		R3.set(R1.getDeviceID());
		
		// Motion profiling
		L1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		L1.reverseSensor(false);
		R1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		R1.reverseSensor(false);*/
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
		/*autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);*/
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		/*switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}*/
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//myRobot.arcadeDrive(stick);
		
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
		L1.set(yValue + xValue);
		//L2.set(yValue + xValue);
		//L3.set(yValue + xValue);
		R1.set(yValue - xValue);
		//R2.set(yValue - xValue);
		//R3.set(yValue - xValue);
		
		/* Joystick values to gyro values
		double angleGyro = gyro.getAngle()%360;
		System.out.println(angleGyro);
		
		double angleStick = Math.toDegrees(Math.atan2((stick.getRawAxis(1)), (stick.getRawAxis(0))));
		System.out.println(angleStick);*/
		
		/*
		double wheel = stick.getRawAxis(0);
		double throttle = stick.getRawAxis(1);

		double wheelNonLinearity;
		double oldWheel; 
		double quickStopAccumulator = 0.0;

		wheel = handleDeadband(wheel, 0.085);
		throttle = handleDeadband(throttle, 0.1);

		oldWheel = wheel;
		double negInertia = wheel - oldWheel;
		wheelNonLinearity = 0.5;
		
		// Apply a sin function that's scaled to make it feel better, smoother driving
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);

		double leftPwm;
		double rightPwm;
		double overPower;
		
		double angularPower;
		double linearPower;

		// Negative inertia, reducing overturn after joystick values stopped
		double negInertiaAccumulator = 0.0;
		double negInertiaScalar;
		if (wheel * negInertia > 0) {
			negInertiaScalar = 2.5;
		} else {
			if (Math.abs(wheel) > 0.65) {
				negInertiaScalar = 5.0;
			} else {
				negInertiaScalar = 3.0;
			}
		}
		double negInertiaPower = negInertia * negInertiaScalar;
		negInertiaAccumulator += negInertiaPower;

		wheel = wheel + negInertiaAccumulator;
		if (negInertiaAccumulator > 1) {
			negInertiaAccumulator -= 1;
		} else if (negInertiaAccumulator < -1) {
			negInertiaAccumulator += 1;
		} else {
			negInertiaAccumulator = 0;
		}
		linearPower = throttle;

		overPower = 0.0;
		
		double alpha = 0.1;
        quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha;
        
		angularPower = Math.abs(throttle) * wheel * - quickStopAccumulator;
		if (quickStopAccumulator > 1) {
			quickStopAccumulator -= 1;
		} else if (quickStopAccumulator < -1) {
			quickStopAccumulator += 1;
		} else {
			quickStopAccumulator = 0.0;
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}

		L1.set(throttle + wheel);
		L2.set(throttle + wheel);
		L3.set(throttle + wheel);
		R1.set(throttle - wheel);
		R2.set(throttle - wheel);
		R3.set(throttle - wheel);
		*/

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

