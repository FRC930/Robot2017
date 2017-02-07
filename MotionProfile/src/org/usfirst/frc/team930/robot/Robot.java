package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.MotionProfileStatus;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
	
	CANTalon L1 = new CANTalon(1);
	CANTalon L2 = new CANTalon(2);
	CANTalon L3 = new CANTalon(3);
	CANTalon R1 = new CANTalon(4);
	CANTalon R2 = new CANTalon(5);
	CANTalon R3 = new CANTalon(6);	
	
	//MotionProfileExample example = new MotionProfileExample(L1, L2, R1, R2);

	boolean[] btnsLast = {false,false,false,false,false,false,false,false,false,false};

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
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
		
		L1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		L1.reverseSensor(false);
		R1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		R1.reverseSensor(false);
		
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
		boolean [] btns= new boolean [btnsLast.length];
		for(int i=1; i<btnsLast.length; ++i)
			btns[i] = stick.getRawButton(i);

		// get the left joystick axis on Logitech Gampead
		double leftYjoystick = -1 * stick.getY(); // multiple by -1 so joystick forward is positive

		// call this periodically, and catch the output.  Only apply it if user wants to run MP.
		// example.control();
		
		if (btns[5] == false) {
			
			// button5 is off so straight drive
			L1.changeControlMode(TalonControlMode.Voltage);
			L1.set(12.0 * leftYjoystick);
			R1.changeControlMode(TalonControlMode.Voltage);
			R1.set(12.0 * leftYjoystick);

			//example.reset();
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
				// user just tapped button 6
				// example.startMotionProfile();
			}
		}

		// save buttons states for on-press detection
		for(int i=1;i<10;++i) {
			
			btnsLast[i] = btns[i];
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

