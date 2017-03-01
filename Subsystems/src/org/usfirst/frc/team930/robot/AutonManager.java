package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutonManager {

    enum mode{
		
		MOTION_PROFILE1,
		DEFAULT
		
	}
 
    private static mode Mode;
    
    private static Timer time = new Timer();
    
	public static void init(){
		
		//Mode = getMode();

		time.reset();	
		time.start();
		
	}
	
	//public static mode getMode(){
		
		
	//}
	
	public static void driveCode(){
		
		switch(Mode){
		
		case MOTION_PROFILE1:
			
			//code for motion profile
			break;
			
		case DEFAULT:
			
			if (time.get() > 3) {
				
				OutputManager.setSpeedL(0);
				OutputManager.setSpeedR(0);
			}
				
			else {
					
					OutputManager.setSpeedL(0.25);
					OutputManager.setSpeedR(0.25);
			}
			
		}
		// End Drive Switch Statement
	}
	
	public static void shooterCode(){
		
		switch(Mode){
		
		case MOTION_PROFILE1:
			
			//code for motion profile shooter
			break;
			
		case DEFAULT:
			

			
		}
		// End Shooter Switch Statement
	}
	
	public static void lightsCode(){
		
		switch(Mode){
		
		case MOTION_PROFILE1:
			
			//code for motion profile shooter
			break;
			
		case DEFAULT:
			

			
		}
		// End Lights Switch Statement
	}
	
}
