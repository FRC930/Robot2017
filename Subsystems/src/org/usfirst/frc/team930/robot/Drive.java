package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class Drive implements Runnable {
	
    double targetX;
    double targetY;
    boolean useJoysticks;
	
	//AHRS gyro = new AHRS(SerialPort.Port.kUSB);
    
	public void run(){
		
		System.out.println("DRIVE"+ Timer.getFPGATimestamp());
		
		OutputManager.setDrivetrainSpeedMode();
		
		// Intake
		if (DSManager.getRawButtonFive() || DSManager.getRawButtonSix() ) {
			OutputManager.setIntakeSpeed(1);;
		}
		else {
			OutputManager.setIntakeSpeed(0);;
		}
		
		// Adjusting joystick sensitivity
		double xValue = Math.pow(DSManager.getRawAxisZero(), 3);
		double yValue = Math.pow(DSManager.getRawAxisOne(), 3);
				
		// Deadband
		if (Math.abs(xValue) < 0.1 && Math.abs(yValue) < 0.1) {
			xValue = 0;
			yValue = 0;
		}
				
		// Setting talons
		OutputManager.setSpeedL(yValue + xValue);
		OutputManager.setSpeedR(yValue - xValue);
		
		/*
		// Controlling different speeds with buttons
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
		*/
		
		double leftSpeed = OutputManager.getTalonSpeedFrontLeftMotor();
		double rightSpeed = OutputManager.getTalonSpeedFrontRightMotor();
		System.out.println("Left Speed: " + leftSpeed);
		System.out.println("Right Speed: " + rightSpeed);
		
		//double leftEncoder = L1.getEncVelocity();
		//double rightEncoder = R1.getEncVelocity();
		//System.out.println("Left Encoder Value: " + leftEncoder);
		//System.out.println("Right Encoder Value: " + rightEncoder);
		
	}
	
	public void setTarget(double x, double y){
		
	}
	
	public void setUseJoysticks( boolean b){
		
	}
	
	public void drive(){
		
	}
	
}


