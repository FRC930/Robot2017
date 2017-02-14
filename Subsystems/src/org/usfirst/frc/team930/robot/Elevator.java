package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator implements Runnable{

	@Override
	public void run() {
		
		if (OutputManager.isRobotTeleop()){
<<<<<<< HEAD
			if(DSManager.getDriveElevatorTrigger()>0.02){
			//OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
				OutputManager.setSpeedElevator(0.8);
			SmartDashboard.putNumber("Elevator Speed", OutputManager.getSparkSpeedElevator());
=======
			if(DSManager.getDriveElevatorTrigger()>0.1){
				//OutputManager.setSpeedElevator(DSManager.getDriveElevatorTrigger());
				OutputManager.setSpeedElevator(0.8);
				SmartDashboard.putNumber("Elevator Speed", OutputManager.getSparkSpeedElevator());
			}
			else{
				OutputManager.setSpeedElevator(0.0);
>>>>>>> 9ce009853c3eb90e7c0e16576bbb7765fb414361
			}
		}
	}
}
