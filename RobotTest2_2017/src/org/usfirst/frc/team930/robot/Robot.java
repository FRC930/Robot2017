// F = .0045
// P = .000328
package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;

import com.ctre.CANTalon.FeedbackDevice;


public class Robot extends IterativeRobot {
	
	final Joystick joy1 = new Joystick(0);
	
	final CANTalon myTal = new CANTalon(5);
	
	double speed = 0.50;
	double rpmActual = 0;
	double fGain = .0045;
	double kF = 0.0;
	
	boolean Apressed = false; 
	boolean Bpressed = false;
	boolean Ypressed = false;
	
	boolean onBool = false;

	@Override
	public void robotInit() {
		myTal.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		//myTal.changeControlMode(CANTalon.TalonControlMode.Speed);
		myTal.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		//myTal.setF(fGain);
		myTal.configNominalOutputVoltage(+0.0f, -0.0f);
		myTal.configPeakOutputVoltage(+12.0, 0.0f);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		
			// Checks for certain button presses
			// Y increases speed by 2.5%, A decreases speed by 2.5%, and B turns the motor "on" or "off" depending on if current motor is on or off
			if (joy1.getRawButton(4) && (!Ypressed)) { // Y
				speed += 0.025;                      
				Ypressed = true;
			} else if((!joy1.getRawButton(4)) && Ypressed) {
				Ypressed = false;
			}
	
			if(joy1.getRawButton(1) && (!Apressed)) { // A
			    speed -= 0.025;  
			    Apressed = true;
			} else if((!joy1.getRawButton(1)) && Apressed) {
			    Apressed = false;
			}
	
			if(joy1.getRawButton(2) && (!Bpressed)) { // B
			    onBool = !onBool;
			    Bpressed = true; 
			} else if((!joy1.getRawButton(2)) && Bpressed) {
				Bpressed = false;
			}
			
			rpmActual = myTal.getEncVelocity() / 4096.0 * 60.0 * 10.0;
			
			// BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
			if (onBool)
				/*if (rpmActual < (speed * 4550)) {
					myTal.set(1.0);
				}
				else {
					myTal.set(0.0);
				}*/
				myTal.set(speed*4500);
			else
				myTal.set(0.0);
			
			System.out.print("TS: " +  (speed * 4500) + "   Volt: " + myTal.getOutputVoltage());
			System.out.println("   S: " + (myTal.getSpeed()) + "E: " + myTal.getClosedLoopError());
			//if (onBool) {
				//myTal.set((speed * 4550 * 4096.0 / 60.0 / 10.0));
				
			//}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

