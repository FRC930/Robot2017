
package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	SubsystemHandler SH;

    public void robotInit() {
    	 	
    	OutputManager.init();
    	OutputManager.teleopInit();
    	Drive.init();
    	//Loggable.init();
    	AutonManager.init();
    	DriveMotionProfiler.startFilling();
        SH = new SubsystemHandler(); // Begins the SystemHandler, which controls the speeds at which the subsystems are updated.
        SH.startSubsystems();
        
       
       // OutputManager.motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE);
       // OutputManager.motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE);
        
    }
    
    public void autonomousInit() {

    	// COMMENTED THIS OUT SO AUTONOMOUS WOULD NOT RUN
    	OutputManager.autonomousInit();
    	
    	//OutputManager.motionProfilerLeft.reset();
    	//OutputManager.motionProfilerRight.reset();
    	
    	DriveMotionProfiler.init();
    	
    	//GeneratedMotionProfileLeft.pointGenerate();
    	GeneratedMotionProfileRight.pointGenerate();
    	
    	//OutputManager.setDrivetrainMotionProfileMode();
    	
    	//OutputManager.motionProfilerLeft.control();
    	//OutputManager.motionProfilerRight.control();
    	OutputManager.profilerRun(true);
    	
		//CANTalon.SetValueMotionProfile setOutputLeft = OutputManager.motionProfilerLeft.getSetValue();
		//OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
		
		//CANTalon.SetValueMotionProfile setOutputRight = OutputManager.motionProfilerRight.getSetValue();
		//OutputManager.setRightDrivetrainCustomMode(setOutputRight);
    	
		//OutputManager.motionProfilerLeft.startMotionProfile();
		//OutputManager.motionProfilerRight.startMotionProfile();

    }

    public void autonomousPeriodic() {
    	
    	
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
    
}
