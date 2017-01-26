package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;

public class Drive implements Runnable {
	
    double targetX;
    double targetY;
    boolean useJoysticks;
    
    RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick stick = new Joystick(1);
	
	AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
	CANTalon L1 = new CANTalon(0);
	CANTalon L2 = new CANTalon(1);
	CANTalon L3 = new CANTalon(2);
	CANTalon R1 = new CANTalon(3);
	CANTalon R2 = new CANTalon(4);
	CANTalon R3 = new CANTalon(5);
	
	// Reverses talons on right side
	public void Drivetrain() {
		
		R1.setInverted(true);
		R2.setInverted(true);
		R3.setInverted(true);
		
	}
    
	public void run(){
		
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
		
	}
	
	public void setTarget(double x, double y){
		
	}
	
	public void setUseJoysticks( boolean b){
		
	}
	
	public void drive(){
		
	}
	
}
