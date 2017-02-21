package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot implements Runnable {

	double targetX;
	double targetY;
	private static double shooterSpeed = -.8;
	
	public void run(){
		
		if (OutputManager.isRobotTeleop()){
		
			if (DSManager.getCoDriveShootTrigger()){ // Replace with onBool for buttons
				
				if(OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER) >= Constants.FULL_SHOOT_SPEED + Constants.BANG_BANG_VARIABLES){
					OutputManager.setShooterSpeed(0.0);
				}
				else if(OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER) < Constants.FULL_SHOOT_SPEED - Constants.BANG_BANG_VARIABLES){
					OutputManager.setShooterSpeed(-1.0);
				}
				/*
				OutputManager.setShooterSpeed(shooterSpeed);
				//OutputManager.setShooterSpeed(Constants.FULL_SHOOT_SPEED);
*/				
			}
			else{
				OutputManager.setShooterSpeed(0.0);	
			}
			/*else {
				//OutputManager.setShooterSpeedMode();
				OutputManager.setShooterSpeed(0.0);
				//OutputManager.setShooterDisabledMode();
				//OutputManager.setShooterSpeed(0.0);
			}
			if (DSManager.increaseShooterSpeed()){
				shooterSpeed -= .025;
			}
			if (DSManager.decreaseShooterSpeed()){
				shooterSpeed += .025;
			}*/

			

			System.out.println("Speed of Shooter: " + OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER));
			SmartDashboard.putNumber("Commanded Speed of Shooter: ", shooterSpeed);

			System.out.println("Speed of Shooter: " + OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER));

			SmartDashboard.putNumber("Speed of Shooter", ((OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER))));
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


