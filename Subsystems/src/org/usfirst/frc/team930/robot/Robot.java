
package org.usfirst.frc.team930.robot;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	SubsystemHandler SH;
	
	//MotionProfilingHandler motionProfilerLeft;
	//MotionProfilingHandler motionProfilerRight;
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();

    public void robotInit() {
    	 	
    	OutputManager.init();
    	OutputManager.teleopInit();
    	Drive.init();
    	//Loggable.init();
        SH = new SubsystemHandler(); // Begins the SystemHandler, which controls the speeds at which the subsystems are updated.
        SH.startSubsystems();
        
       
        OutputManager.motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE);
        OutputManager.motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE);
        
    }
    
    public void autonomousInit() {
    	
    	//OutputManager.motionProfilerLeft.reset();
    	//OutputManager.motionProfilerRight.reset();
    	MotionProfilerSubsystem.reset();
    	
    	OutputManager.autonomousInit();
    	
    	OutputManager.setDrivetrainMotionProfileMode();
    	
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
    	OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_AUTO);
    	
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

    public void teleopPeriodic() {
        OutputManager.teleopInit();
    }

    public void testPeriodic() {
    
    }
    
}
