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
    
    RobotDrive myRobot = new RobotDrive(0, 1);
	
	//AHRS gyro = new AHRS(SerialPort.Port.kUSB);
    
	public void run(){
		
		System.out.println("DRIVE"+ Timer.getFPGATimestamp());
		
		OutputManager.setDrivetrainPercentVbusMode();
		
		myRobot.arcadeDrive(DSManager.stick);
		
		// Adjusting joystick sensitivity
		double xValue = Math.pow(DSManager.stick.getRawAxis(0), 3);
		double yValue = Math.pow(DSManager.stick.getRawAxis(1), 3);
				
		// Deadband
		if (Math.abs(xValue) < 0.1 && Math.abs(yValue) < 0.1) {
			xValue = 0;
			yValue = 0;
		}
				
		// Setting talons
		OutputManager.setSpeedL(yValue + xValue);
		OutputManager.setSpeedR(yValue - xValue);
		
	}
	
	public void setTarget(double x, double y){
		
	}
	
	public void setUseJoysticks( boolean b){
		
	}
	
	public void drive(){
		
	}
	
}
