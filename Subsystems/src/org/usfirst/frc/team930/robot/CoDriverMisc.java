package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CoDriverMisc implements Runnable{

	@Override
	public void run() {
		
		if (OutputManager.isRobotTeleop()){

			// ELEVATOR
			if(DSManager.getDriveElevatorTrigger()>0.02){
				
			//OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
				OutputManager.setSpeedElevator(-1);
			//SmartDashboard.putNumber("Elevator Speed", OutputManager.getCommandedSpeed(OutputManager.Motors.ELEVATOR));

			}
			
			else if(DSManager.getCoDriveRawButtonEight()){
				
				OutputManager.setSpeedElevator(.8); 	
				
			}
			
			else {
				
				OutputManager.setSpeedElevator(0.0);

			}
			
			// CLIMBER
			double yValue = Math.pow(DSManager.getCoDriveYAxis(), Constants.JOYSTICK_NONLINEARITY);
			
			//Setting speed to controller

			SmartDashboard.putNumber("Climb Progress", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL0));
			
			if (Math.abs(yValue) >= Constants.JOYSTICK_ERROR_ALLOWANCE){
	
				OutputManager.setClimberSpeed(-yValue);
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_CLIMB);
		
			}
			
			else {
				
				OutputManager.setClimberSpeed(0);
				
			}
			
			// INTAKE
			if(DSManager.getCoDriveRawButtonOne()){
				OutputManager.setIntakeSpeed(-1.0);
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_INTAKE);
		
			}
			else if(DSManager.getCoDriveRawButtonSeven()){
				OutputManager.setIntakeSpeed(1.0);
				
			}
			else{
				OutputManager.setIntakeSpeed(0);
				
			}
			
			// LIGHTS
			if(DSManager.getCoDriveRawButtonFive()){
				
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_FUUN);
				
			}
			
			//Sets the teleop lights when no other lights are running
			if(Math.abs(DSManager.getDriveYAxis()) <= Constants.JOYSTICK_ERROR_ALLOWANCE && DSManager.getCoDriveRawButtonOne() == false && OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL7)<5 && Math.abs(Math.pow(DSManager.getDriveXAxis(), Constants.JOYSTICK_NONLINEARITY)) > Constants.JOYSTICK_ERROR_ALLOWANCE && Math.abs(Math.pow(DSManager.getDriveYAxis() * -1.0, Constants.JOYSTICK_NONLINEARITY)) > Constants.JOYSTICK_ERROR_ALLOWANCE) {
				
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_TELE);
				
			}
			
		}
		
		else if (OutputManager.isRobotAuton()){
			
			//AutonManager.lightsCode();
			
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_AUTO);
			
		}
		
	}
	
}
