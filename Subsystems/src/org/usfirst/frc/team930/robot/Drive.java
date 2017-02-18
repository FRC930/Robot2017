package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;

public class Drive implements Runnable {

	public void run(){
		if(OutputManager.isRobotTeleop()){
		//	OutputManager.setDrivetrainPercentVbusMode();
			
			// Adjusting joystick sensitivity
			double xValue = Math.pow(DSManager.getDriveXAxis(), Constants.JOYSTICK_NONLINEARITY);
			double yValue = Math.pow(DSManager.getDriveYAxis() * -1.0, Constants.JOYSTICK_NONLINEARITY);
					
			// Deadband
			if (Math.abs(xValue) < Constants.JOYSTICK_ERROR_ALLOWANCE && Math.abs(yValue) < Constants.JOYSTICK_ERROR_ALLOWANCE) {
				xValue = Constants.X_DEFAULT_VALUE;
				yValue = Constants.Y_DEFAULT_VALUE;
			}
			else{
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_DRIVE);
			}
			
			// Setting talons
			OutputManager.setSpeedL(yValue + xValue);
			OutputManager.setSpeedR(yValue - xValue);
			System.out.println("r: " + OutputManager.getTalonSpeedFrontRightMotor());
			System.out.println("l: "+OutputManager.getTalonSpeedFrontLeftMotor());
		}
		else if (OutputManager.isRobotAuton()){
			
	    	//CANTalon.SetValueMotionProfile setOutputLeft = OutputManager.motionProfilerLeft.getSetValue();
			//OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
			
			//CANTalon.SetValueMotionProfile setOutputRight = OutputManager.motionProfilerRight.getSetValue();
			//OutputManager.setRightDrivetrainCustomMode(setOutputRight);
			
			//OutputManager.motionProfilerLeft.control();
			//OutputManager.motionProfilerRight.control();
			
			OutputManager.profilerRun(true);
			
		}
			
		}
	public static void init(){
		OutputManager.setDrivetrainPercentVbusMode();		
	}
}
