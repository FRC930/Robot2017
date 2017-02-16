package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator implements Runnable{

	@Override
	public void run() {
		
		if (OutputManager.isRobotTeleop()){

			if(DSManager.getDriveElevatorTrigger()>0.02){
			//OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
				OutputManager.setSpeedElevator(0.5);
			SmartDashboard.putNumber("Elevator Speed", OutputManager.getSparkSpeedElevator());

			}
			else{
				OutputManager.setSpeedElevator(0.0);

			}
		}
	}
}
