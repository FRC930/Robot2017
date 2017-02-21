package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;

public class BackupAuton implements Runnable {

	static Timer time = new Timer();
	
	@Override
	public void run() {
		
		if (OutputManager.isRobotAuton()) {
		
			if (time.get() > 3) {
				
				OutputManager.setSpeedL(0);
				OutputManager.setSpeedR(0);
			}
			
			else {
				
				OutputManager.setSpeedL(0.25);
				OutputManager.setSpeedR(0.25);
			}
		
		}
		
	}
	
	public static void init(){
		
		time.reset();	
		
		time.start();
	
	}
	

}
