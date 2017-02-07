package org.usfirst.frc.team930.robot;

public class Drive implements Runnable {

	public void run(){
		
	//	OutputManager.setDrivetrainPercentVbusMode();
		
		// Adjusting joystick sensitivity
		double xValue = Math.pow(DSManager.getDriveXAxis(), Constants.JOYSTICK_NONLINEARITY);
		double yValue = Math.pow(DSManager.getDriveYAxis() * -1.0, Constants.JOYSTICK_NONLINEARITY);
				
		// Deadband
		if (Math.abs(xValue) < Constants.JOYSTICK_ERROR_ALLOWANCE && Math.abs(yValue) < Constants.JOYSTICK_ERROR_ALLOWANCE) {
			xValue = Constants.X_DEFAULT_VALUE;
			yValue = Constants.Y_DEFAULT_VALUE;
		}
				
		// Setting talons
		OutputManager.setSpeedL(yValue + xValue);
		OutputManager.setSpeedR(yValue - xValue);
		
	}
	public static void init(){
		OutputManager.setDrivetrainPercentVbusMode();		
	}
}
