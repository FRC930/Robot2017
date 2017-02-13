package org.usfirst.frc.team930.robot;

public class Elevator implements Runnable{

	@Override
	public void run() {
		
		if (OutputManager.isRobotTeleop()){
			if(DSManager.getDriveElevatorTrigger()>0.02){
			OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
				//OutputManager.setSpeedElevator(0.6);
			}
		}
	}
}


