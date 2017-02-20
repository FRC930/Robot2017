package org.usfirst.frc.team930.robot;

public class Intake implements Runnable {
   
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
			
			//System.out.println("Intake " + Timer.getFPGATimestamp());
			
			//Setting lights for the Intake
		
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
			
		}
		
		else if (OutputManager.isRobotAuton()){
			
		}
		
	}
	
}
	

