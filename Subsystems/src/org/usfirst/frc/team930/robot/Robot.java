
package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	SubsystemHandler SH;
	
	public SendableChooser <Integer> autoChooser;
	
	public int chosenVal;
	
	private static Timer time = new Timer();
	
    public void robotInit() {
    	 	
    	OutputManager.init();
    	OutputManager.teleopInit();
    	Drive.init();
    	//Loggable.init();
    	
    	///////////////////////////////////////
    	//DriveMotionProfiler.startFilling();//
    	///////////////////////////////////////
    	
        SH = new SubsystemHandler(); // Begins the SystemHandler, which controls the speeds at which the subsystems are updated.
        SH.startSubsystems();
        
       
       // OutputManager.motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE);
       // OutputManager.motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE);
        
        autoChooser = new SendableChooser<Integer>();
        autoChooser.addDefault("DEFAULT", new Integer(0));
    	autoChooser.addObject("RED_SHOOTER", new Integer(1));
        autoChooser.addObject("BLUE_SHOOTER", new Integer(2));
        autoChooser.addObject("RED_RIGHT_GEAR", new Integer(5));
        autoChooser.addObject("RED_LEFT_GEAR", new Integer(4));
        autoChooser.addObject("BLUE_LEFT_GEAR", new Integer(6));
        autoChooser.addObject("BLUE_RIGHT_GEAR", new Integer(3));
        autoChooser.addObject("MIDDLE_GEAR", new Integer(7));
        autoChooser.addObject("RED_GEAR_SHOOT", new Integer(8));
        autoChooser.addObject("FORWARD", new Integer(9));
        autoChooser.addObject("BACKWARD", new Integer(10));
        
        System.out.println("AutoChooser");
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        
        chosenVal = 0;
    }
    
    public void autonomousInit() {

    	AutonManager.init();
    	OutputManager.autonomousInit();
    	AutonManager.driveCode();

    	// COMMENTED THIS OUT SO AUTONOMOUS WOULD NOT RUN
    	 	
    	//OutputManager.motionProfilerLeft.reset();
    	//OutputManager.motionProfilerRight.reset();
    	//DriveMotionProfiler.init();
    	
    	//OutputManager.setDrivetrainMotionProfileMode();
    	
    	//OutputManager.motionProfilerLeft.control();
    	//OutputManager.motionProfilerRight.control();
    	//OutputManager.profilerRun(true);
    	
		//CANTalon.SetValueMotionProfile setOutputLeft = OutputManager.motionProfilerLeft.getSetValue();
		//OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
		
		//CANTalon.SetValueMotionProfile setOutputRight = OutputManager.motionProfilerRight.getSetValue();
		//OutputManager.setRightDrivetrainCustomMode(setOutputRight);
    	
		//OutputManager.motionProfilerLeft.startMotionProfile();
		//OutputManager.motionProfilerRight.startMotionProfile();

    }

    public void autonomousPeriodic() {
    	
    	AutonManager.driveCode();
    	AutonManager.shooterCode();
    	AutonManager.gearCode();
    	
    	OutputManager.setClimberSpeed(1.0);
    	
    	System.out.println("Left Encoder: " + OutputManager.L1Master.getEncVelocity() + "           " + "Right Encoder: " + OutputManager.R1Master.getEncVelocity());
    	
    	/*
    	CANTalon.SetValueMotionProfile setOutputLeft = motionProfilerLeft.getSetValue();
		OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
		
		CANTalon.SetValueMotionProfile setOutputRight = motionProfilerRight.getSetValue();
		OutputManager.setRightDrivetrainCustomMode(setOutputRight);
    	
    	motionProfilerLeft.control();
		motionProfilerRight.control();
    	 */
	//	System.out.println((OutputManager.frontLeftMotor.getSpeed()) + "," + (OutputManager.frontLeftMotor.getClosedLoopError()) + "," + OutputManager.frontLeftMotor.getOutputVoltage() + "," + pdp.getCurrent(15) + "," + pdp.getVoltage() + "," + OutputManager.frontLeftMotor.getBusVoltage() + "," + (OutputManager.frontRightMotor.getSpeed()) + "," + (OutputManager.frontRightMotor.getClosedLoopError()) + "," + OutputManager.frontRightMotor.getOutputVoltage() + "," + pdp.getCurrent(0) + "," + pdp.getVoltage() + "," + OutputManager.frontRightMotor.getBusVoltage() + "," + Timer.getFPGATimestamp());
		
		/*
		 * F: 2
		 * P: 10
		 * I: 0.0003
		 */
    }
    
    public void teleopInit(){
    	
    	//OutputManager.setShooterSpeedMode();
    	//OutputManager.setShooterPercentVbusMode();
    	OutputManager.setShooterSpeedMode();
    	OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.PercentVbus);
    	
    }
    
    public void teleopPeriodic() {
    	
        OutputManager.teleopInit();
        
    }

    public void testPeriodic() {
    
    }
    
   public void disabledInit(){
	   
	   	OutputManager.turnRelaysOff();
    	OutputManager.disabledInit();
    	OutputManager.setShooterSpeed(0);
    	
    }
   
   public void disabledPeriodic(){
	   
	   if (autoChooser.getSelected().intValue() != chosenVal){
		   AutonManager.changeMode(autoChooser.getSelected().intValue());
		   chosenVal = autoChooser.getSelected().intValue();
		   System.out.println(autoChooser.getSelected().intValue());
		   if (autoChooser.getSelected().intValue() < 9 && autoChooser.getSelected().intValue() > 0 )
			   DriveMotionProfiler.startFilling(autoChooser.getSelected().intValue());
	   }
	   
   }
   
    
}
