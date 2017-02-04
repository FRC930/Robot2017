//Kyle Senebouttarath and Andrew Ocampo
package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;


public class Robot extends IterativeRobot {
	
	//Variables
	
	Joystick stick = new Joystick(1);	//Controller
	
	Spark intakeMotor = new Spark(8);		//Spark
	
	//Button MotorControlButton = new JoystickButton(stick, 1);		//Button, A button
	
	boolean motorOn = false;	//On-Off Checker
	
	//Initalization
	@Override
	public void robotInit() {
		
	}

	//Auto Initalization
	@Override
	public void autonomousInit() {
		
	}
	
	//Auto Period
	@Override
	public void autonomousPeriodic() {
	
	}

	//Teleop Period
	@Override
	public void teleopPeriodic() {
		
		if (stick.getRawButton(5) || stick.getRawButton(6) ) {
			intakeMotor.set(-1);
		}
		else {
			intakeMotor.set(0);
		}
		
		
		Timer.delay(0.005);
	}

	//Testing Period
	@Override
	public void testPeriodic() {
		
	}
}

