package org.usfirst.frc.team930.robot;

public class Elevator implements Runnable{

	@Override
	public void run() {
		
		if (OutputManager.isRobotTeleop()){
			
			OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
		
		}
	}
}
