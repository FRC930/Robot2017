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
public class Drivetrain extends IterativeRobot {
	/*final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();*/
	
	RobotDrive myRobot = new RobotDrive(0, 1);
	
	Joystick stick = new Joystick(1);
	Button driver4 = new JoystickButton(stick, 4);
	Button driver1 = new JoystickButton(stick, 1);
	
	AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
	CANTalon L1 = new CANTalon(0);
	CANTalon L2 = new CANTalon(1);
	CANTalon L3 = new CANTalon(2);
	CANTalon R1 = new CANTalon(3);
	CANTalon R2 = new CANTalon(4);
	CANTalon R3 = new CANTalon(5);

	// MotionProfileExample example = new MotionProfileExample(_talon);

	boolean[] btnsLast = {false,false,false,false,false,false,false,false,false,false};

	public Drivetrain() {
		super();
		R1.setInverted(true);
		R2.setInverted(true);
		R3.setInverted(true);
		
		gyro.reset();
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		/*chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);*/

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
		
		// Motion profiling
		L1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		L1.reverseSensor(false);
		R1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		R1.reverseSensor(false);
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
		myRobot.arcadeDrive(stick);
		
		// Adjusting joystick sensitivity
		double xValue = Math.pow(stick.getRawAxis(0), 3);
		double yValue = Math.pow(stick.getRawAxis(1), 3);
		
		// Deadband
		if (Math.abs(xValue) < 0.1 && Math.abs(yValue) < 0.1) {
			xValue = 0;
			yValue = 0;
		}
		
		// Setting talons
		L1.set(yValue + xValue);
		L2.set(yValue + xValue);
		L3.set(yValue + xValue);
		R1.set(yValue - xValue);
		R2.set(yValue - xValue);
		R3.set(yValue - xValue);
		
		/* Joystick values to gyro values
		double angleGyro = gyro.getAngle()%360;
		System.out.println(angleGyro);
		
		double angleStick = Math.toDegrees(Math.atan2((stick.getRawAxis(1)), (stick.getRawAxis(0))));
		System.out.println(angleStick);*/
		
		// Motion profiling
		boolean [] btns= new boolean [btnsLast.length];
		for(int i=1; i<btnsLast.length; ++i)
			btns[i] = stick.getRawButton(i);

		// get the left joystick axis on Logitech Gampead
		double leftYjoystick = -1 * stick.getY(); /* multiple by -1 so joystick forward is positive */

		// call this periodically, and catch the output.  Only apply it if user wants to run MP.
		// example.control();
		
		if (btns[5] == false) {
			
			// button5 is off so straight drive
			L1.changeControlMode(TalonControlMode.Voltage);
			L1.set(12.0 * leftYjoystick);
			R1.changeControlMode(TalonControlMode.Voltage);
			R1.set(12.0 * leftYjoystick);

			// example.reset();
		} 
		else {
			
			// Button5 is held down so switch to motion profile control mode
			L1.changeControlMode(TalonControlMode.MotionProfile);
			R1.changeControlMode(TalonControlMode.MotionProfile);
			
			// CANTalon.SetValueMotionProfile setOutput = example.getSetValue();
					
			// L1.set(setOutput.value);
			// R1.set(setOutput.value);

			// if btn is pressed and was not pressed last time
			if( (btns[6] == true) && (btnsLast[6] == false) ) {
				/* user just tapped button 6 */
				// example.startMotionProfile();
			}
		}

		// save buttons states for on-press detection
		for(int i=1;i<10;++i) {
			
			btnsLast[i] = btns[i];
		}
		
		
		Timer.delay(0.005);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

