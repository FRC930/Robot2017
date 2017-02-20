package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator implements Runnable{

	@Override
	public void run() {
		
		if (OutputManager.isRobotTeleop()){

			if(DSManager.getDriveElevatorTrigger()>0.02){
			//OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
				OutputManager.setSpeedElevator(-1.0);
			SmartDashboard.putNumber("Elevator Speed", OutputManager.getCommandedSpeed(OutputManager.Motors.ELEVATOR));

			}
			else if(DSManager.getCoDriveRawButtonEight()){
				OutputManager.setSpeedElevator(1.0);
				
			}
			else {
				OutputManager.setSpeedElevator(0.0);

			}
		}
	}
}
