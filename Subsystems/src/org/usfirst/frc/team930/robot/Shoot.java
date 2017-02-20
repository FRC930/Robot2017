package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot implements Runnable {

	double targetX;
	double targetY;
	private static double shooterSpeed = -.8;
	
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
		
			if (DSManager.getCoDriveShootTrigger()){ // Replace with onBool for buttons
				
				OutputManager.setShooterSpeed(shooterSpeed);
				//OutputManager.setShooterSpeed(Constants.FULL_SHOOT_SPEED);
				
			}
			else {
				//OutputManager.setShooterSpeedMode();
				OutputManager.setShooterSpeed(0.0);
				//OutputManager.setShooterDisabledMode();
				//OutputManager.setShooterSpeed(0.0);
			}
			if (DSManager.getCoDriveIncrementShootTrigger()){
				shooterSpeed -= .025;
			}
			if (DSManager.getCoDriveDecrementShootTrigger()){
				shooterSpeed += .025;
			}

			
			System.out.println("Speed of Shooter: " + OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER));
			SmartDashboard.putNumber("Commanded Speed of Shooter: ", shooterSpeed);
			SmartDashboard.putNumber("Speed of Shooter", ((OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER)) *-1));
			SmartDashboard.putNumber("Current of Shooter", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL11));
			SmartDashboard.putNumber("Current of Shooter2", OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL10));

			//Setting Lights for Shooting
			if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL12)>5){
			
				OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_SHOOT);
			
			}
			
		}
		
		else if (OutputManager.isRobotAuton()){
			
		}
		
	}

	
}


