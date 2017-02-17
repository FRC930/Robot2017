package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot implements Runnable {

	double targetX;
	double targetY;
	
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
		
			if (DSManager.getCoDriveShootTrigger()){ // Replace with onBool for buttons
				OutputManager.setShooterPercentVbusMode();
				OutputManager.setShooterSpeed(-0.7);
				//OutputManager.setShooterSpeedMode();
				//OutputManager.setShooterSpeed(Constants.FULL_SHOOT_SPEED);
			}
			else{
				//OutputManager.setShooterSpeedMode();
				OutputManager.setShooterSpeed(0.0);
				//OutputManager.setShooterDisabledMode();
				//OutputManager.setShooterSpeed(0.0);
			}
			
			System.out.println("Speed of Shooter: " + OutputManager.getTalonShooterMotor());
			SmartDashboard.putNumber("Speed of Shooter", ((OutputManager.getTalonShooterMotor()) *-1));
			
			//Setting Lights for Shooting
			if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL12)>5){
			
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_SHOOT);
			
			}
			
		}
		
		else if (OutputManager.isRobotAuton()){
			
		}
		
	}

	
}


