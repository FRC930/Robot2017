package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;

public class AutonManager {

    enum mode{
		
		RED_SHOOTER,
		BLUE_SHOOTER,
		FORWARD,
		BACKWARD,
		DEFAULT
		
	}
 
    private static double  timeo;
    private static mode Mode = mode.FORWARD;
    
	public static void init(){

		timeo = Timer.getFPGATimestamp();
		
		OutputManager.setShooterSpeedMode();
		
		switch (Mode){
		
		case RED_SHOOTER:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
		
		case BLUE_SHOOTER:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case FORWARD:
			break;
			
		case BACKWARD:
			break;
			
		case DEFAULT:
			break;
			
		}
		
	}
	
	public static void changeMode(int i){
		
		switch(i){
		
		case 1:
			Mode = mode.RED_SHOOTER;
			break;
			
		case 2:
			Mode = mode.BLUE_SHOOTER;
			break;
			
		case 3:
			Mode = mode.FORWARD;
			break;
			
		case 4:
			Mode = mode.BACKWARD;
			break;
			
		}
		
	}
	
	public static mode getMode(){
		
		return Mode;
		
	}
	
	public static void driveCode(){
		
		switch(Mode){
		
		case BLUE_SHOOTER:
			
			System.out.println(Mode);
			break;
			
		case FORWARD:
			
			System.out.println(Mode);

	    	OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.PercentVbus);
	    	
			if (( Timer.getFPGATimestamp() - timeo) > 5) {
				
				OutputManager.setSpeedL(0);
				OutputManager.setSpeedR(0);
			}
				
			else {
					
				OutputManager.setSpeedL(0.25);
				OutputManager.setSpeedR(0.25);
			}
			
			break; 
			
		case BACKWARD:
			
			System.out.println(Mode);

	    	OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.PercentVbus);
	    	
			if (( Timer.getFPGATimestamp() - timeo) > 4) {
				
				OutputManager.setSpeedL(0);
				OutputManager.setSpeedR(0);
			}
				
			else {
					
				OutputManager.setSpeedL(-0.25);
				OutputManager.setSpeedR(-0.25);
			}
			
			break; 
		
		case RED_SHOOTER:
			
			System.out.println(Mode);
			break;
			
		case DEFAULT:
			
			System.out.println(Mode);
			
		}
		// End Drive Switch Statement
		
	}
	
	public static void shooterCode(){
		
		switch(Mode){
		
		case BLUE_SHOOTER:
			
			if ( (Timer.getFPGATimestamp() - timeo) > (GeneratedMotionProfileRightBLUE.kNumPoints)/100.0 - 2.0 ){
				
				OutputManager.setShooterSpeed(-Constants.FULL_SHOOT_SPEED);
			
			}
			
			if ( (Timer.getFPGATimestamp() - timeo) > (GeneratedMotionProfileRightBLUE.kNumPoints)/100.0 - 0.5){
				
				OutputManager.setSpeedElevator(Constants.ELEVATOR_SPEED_FORWARDS);
				
			}
			
			break;
			
		case FORWARD:
			
			break;
			
		case BACKWARD:
			
			break;
		
		case RED_SHOOTER:
			
			if ( (Timer.getFPGATimestamp() - timeo) > (GeneratedMotionProfileRightRED.kNumPoints)/100.0 - 2.0 ){
				
				OutputManager.setShooterSpeed(-Constants.FULL_SHOOT_SPEED);
				
			
			}
			
			if ( (Timer.getFPGATimestamp() - timeo) > (GeneratedMotionProfileRightRED.kNumPoints)/100.0 - 0.5 ){
				
				OutputManager.setSpeedElevator(Constants.ELEVATOR_SPEED_FORWARDS);
				
			}
			
			break;
				
		case DEFAULT:
			

			
		}
		// End Shooter Switch Statement
		
	}
	
	public static void lightsCode(){
		
		switch(Mode){
		
		case BACKWARD:
			
			break;
		
		case BLUE_SHOOTER:
			
			break;
			
		case FORWARD:
			
			break;
		
		case RED_SHOOTER:
			
			break;
				
		case DEFAULT:
			
            break;
			
		}
		// End Lights Switch Statement
		
	}
	
}
