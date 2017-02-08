package org.usfirst.frc.team930.robot;

public class Lights implements Runnable {

	public void run() {
		//Makes a button to make the fun mode work in lights
		if(DSManager.getCoDriveRawButtonFive()){
			
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_FUUN);
			
		}
		
		//Sets the teleop lights when no other lights are running
		if(Math.abs(DSManager.getDriveYAxis()) <= Constants.JOYSTICK_ERROR_ALLOWANCE && DSManager.getCoDriveRawButtonOne() == false && OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL7)<5 && Math.abs(Math.pow(DSManager.getDriveXAxis(), Constants.JOYSTICK_NONLINEARITY)) > Constants.JOYSTICK_ERROR_ALLOWANCE && Math.abs(Math.pow(DSManager.getDriveYAxis() * -1.0, Constants.JOYSTICK_NONLINEARITY)) > Constants.JOYSTICK_ERROR_ALLOWANCE) {
			
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_TELE);
			
		}
	}

}
