//Kyle Senebouttarath and Andrew Ocampo
package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;


public class Robot extends IterativeRobot {
	
	//Variables
	
	Joystick stick = new Joystick(1);	//Controller
	
	CANTalon IntakeMotor = new CANTalon(8);		//Talon
	
	//Button MotorControlButton = new JoystickButton(stick, 1);		//Button, A button
	
	boolean MotorOn = false;	//On-Off Checker
	
	//Initalization
	@Override
	public void robotInit() {
		
		IntakeMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
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
		
		if (stick.getRawButton(1)) {
			
			if (!MotorOn) {
				
				MotorOn = true;
				
				IntakeMotor.set(-1);
				
			} else {
				
				MotorOn = false;
				
				IntakeMotor.set(0);
				
			}
			
		}
		
		
		Timer.delay(0.005);
	}

	//Testing Period
	@Override
	public void testPeriodic() {
		
	}
}

