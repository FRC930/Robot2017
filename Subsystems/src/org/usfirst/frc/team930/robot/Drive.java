package org.usfirst.frc.team930.robot;

public class Drive implements Runnable {

	public void run(){
		
	//	OutputManager.setDrivetrainPercentVbusMode();
		
		// Adjusting joystick sensitivity
		double xValue = Math.pow(DSManager.getDriveXAxis(), 3);
		double yValue = Math.pow(DSManager.getDriveYAxis() * -1.0, 3);
				
		// Deadband
		if (Math.abs(xValue) < 0.1 && Math.abs(yValue) < 0.1) {
			xValue = 0;
			yValue = 0;
		}
				
		// Setting talons
		OutputManager.setSpeedL(yValue + xValue);
		OutputManager.setSpeedR(yValue - xValue);
		
	}
	public static void init(){
		OutputManager.setDrivetrainPercentVbusMode();		
	}
}
