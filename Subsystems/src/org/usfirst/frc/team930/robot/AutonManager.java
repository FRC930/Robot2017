package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;

public class AutonManager {

    enum mode{
		
		RED_SHOOTER,
		BLUE_SHOOTER,
		BLUE_RIGHT_GEAR,
		BLUE_LEFT_GEAR,
		RED_LEFT_GEAR,
		RED_RIGHT_GEAR,
		MIDDLE_GEAR,
		RED_GEAR_SHOOT,
		BLUE_GEAR_SHOOT,
		RED_GEAR_HOPPER,
		BLUE_GEAR_HOPPER,
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
			
		case BLUE_RIGHT_GEAR:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case BLUE_LEFT_GEAR:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case RED_LEFT_GEAR:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
		case RED_RIGHT_GEAR:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case MIDDLE_GEAR:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case RED_GEAR_SHOOT:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case BLUE_GEAR_SHOOT:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case RED_GEAR_HOPPER:
			DriveMotionProfiler.init();
			OutputManager.profilerRun(true);
			break;
			
		case BLUE_GEAR_HOPPER:
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
			Mode = mode.BLUE_RIGHT_GEAR;
			break;
			
		case 4:
			Mode = mode.RED_LEFT_GEAR;
			break;
			
		case 5:
			Mode = mode.RED_RIGHT_GEAR;
			break;
			
		case 6:
			Mode = mode.BLUE_LEFT_GEAR;
			break;
			
		case 7:
			Mode = mode.MIDDLE_GEAR;
			break;
			
		case 8:
			Mode = mode.RED_GEAR_SHOOT;
			break;
			
		case 9:
			Mode = mode.BLUE_GEAR_SHOOT;
			break;
			
		case 10:
			Mode = mode.RED_GEAR_HOPPER;
			break;
			
		case 11:
			Mode = mode.BLUE_GEAR_HOPPER;
			break;
			
		case 12:
			Mode = mode.FORWARD;
			break;
			
		case 13:
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
		
		case RED_SHOOTER:
			
			System.out.println(Mode);
			break;
			
		case BLUE_RIGHT_GEAR:
			
			System.out.println(Mode);
			break;
			
		case BLUE_LEFT_GEAR:
			
			System.out.println(Mode);
			break;
			
		case RED_LEFT_GEAR:
			
			System.out.println(Mode);
			break;
			
		case RED_RIGHT_GEAR:
	
			System.out.println(Mode);
			break;
	
		case MIDDLE_GEAR:
	
			System.out.println(Mode);
			break;
	
		case RED_GEAR_SHOOT:
	
			System.out.println(Mode);
			break;
	
		case BLUE_GEAR_SHOOT:
			
			System.out.println(Mode);
			break;
	
		case RED_GEAR_HOPPER:
	
			System.out.println(Mode);
			break;
	
		case BLUE_GEAR_HOPPER:
	
			System.out.println(Mode);
			break;
			
		case FORWARD:
			
			System.out.println(Mode);

	    	OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.PercentVbus);
	    	
			if (( Timer.getFPGATimestamp() - timeo) > 5 + Constants.AUTO_DELAY) {
				
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
	    	
			if (( Timer.getFPGATimestamp() - timeo) > 4 + Constants.AUTO_DELAY) {
				
				OutputManager.setSpeedL(0);
				OutputManager.setSpeedR(0);
			}
				
			else {
					
				OutputManager.setSpeedL(-0.25);
				OutputManager.setSpeedR(-0.25);
			}
			
			break; 
			
		case DEFAULT:
			
			System.out.println(Mode);
			
		}
		// End Drive Switch Statement
		
	}
	
	public static void gearCode(){
		
		switch(Mode){
		
		case BLUE_SHOOTER:
			
			break;

		case RED_SHOOTER:
			
			break;
			
		case RED_GEAR_SHOOT:
			
			if ( ((Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_SHOOT_DOWN + Constants.AUTO_DELAY) && ((Timer.getFPGATimestamp() - timeo) < Constants.GEAR_AUTO_SHOOT_UP + Constants.AUTO_DELAY) ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {

				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
				
			}
			
			break;
		
		case BLUE_GEAR_SHOOT:
			
			if ( ((Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_SHOOT_DOWN + Constants.AUTO_DELAY) && ((Timer.getFPGATimestamp() - timeo) < Constants.GEAR_AUTO_SHOOT_UP + Constants.AUTO_DELAY) ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {

				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
				
			}
			
			break;
			
		case BLUE_RIGHT_GEAR:
			
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_LOADING + Constants.AUTO_DELAY ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {

				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
				
			}
			
			break;
			
		case RED_LEFT_GEAR:
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_LOADING + Constants.AUTO_DELAY ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {
				
				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
			
			}
				
			break;
			
		case RED_RIGHT_GEAR:
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_HOPPER + Constants.AUTO_DELAY ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {
				
				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
				
			}
			
			break;
			
		case BLUE_LEFT_GEAR:
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_HOPPER + Constants.AUTO_DELAY ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {
				
				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
				
			}
			
			break;
			
		case MIDDLE_GEAR:
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.GEAR_AUTO_MIDDLE + Constants.AUTO_DELAY ){
				
				OutputManager.setGearWheelsSpeed(Constants.GEAR_WHEELS_SPEED_OUT);
				OutputManager.setGearArmPos(Constants.GEAR_ARM_DOWN);
				
			}
			else {
				
				OutputManager.setGearArmPos(Constants.GEAR_ARM_UP);
				OutputManager.setGearWheelsSpeed(0.2);
				
			}
			
			break;
			
		case RED_GEAR_HOPPER:
			
			break;
			
		case BLUE_GEAR_HOPPER:
	
			break;
			
		case FORWARD:
			
			break;
			
		case BACKWARD:
			
			break;
				
		case DEFAULT:
			
		}
		// End Gear Switch Statement
		
	}
	
	public static void shooterCode(){
		
		switch(Mode){
		
		case BLUE_SHOOTER:
			
			OutputManager.setIntakeSpeed(-1.0);
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.SHOOT_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setShooterSpeed(-Constants.FULL_SHOOT_SPEED);
			
			}
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.ELEVATOR_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setSpeedElevator(Constants.ELEVATOR_SPEED_FORWARDS);
				
			}
			
			break;

		case RED_SHOOTER:
			
			OutputManager.setIntakeSpeed(-1.0);
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.SHOOT_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setShooterSpeed(-Constants.FULL_SHOOT_SPEED);
				
			
			}
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.ELEVATOR_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setSpeedElevator(Constants.ELEVATOR_SPEED_FORWARDS);
				
			}
			
			break;
			
		case RED_GEAR_SHOOT:
			
			OutputManager.setIntakeSpeed(-1.0);
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.SHOOT_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setShooterSpeed(-Constants.FULL_SHOOT_SPEED);
				
			
			}
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.ELEVATOR_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setSpeedElevator(Constants.ELEVATOR_SPEED_FORWARDS);
				
			}
			
			break;
		
		case BLUE_GEAR_SHOOT:
			
			OutputManager.setIntakeSpeed(-1.0);
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.SHOOT_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setShooterSpeed(-Constants.FULL_SHOOT_SPEED);
				
			
			}
			
			if ( (Timer.getFPGATimestamp() - timeo) > Constants.ELEVATOR_AUTO_ON + Constants.AUTO_DELAY ){
				
				OutputManager.setSpeedElevator(Constants.ELEVATOR_SPEED_FORWARDS);
				
			}
			
			break;
			
		case BLUE_RIGHT_GEAR:
			
			break;
			
		case RED_LEFT_GEAR:
			
			break;
			
		case RED_RIGHT_GEAR:
			
			break;
			
		case BLUE_LEFT_GEAR:
	
			break;
			
		case MIDDLE_GEAR:
			
			break;
			
		case RED_GEAR_HOPPER:
			
			break;
			
		case BLUE_GEAR_HOPPER:
	
			break;
			
		case FORWARD:
			
			break;
			
		case BACKWARD:
			
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
