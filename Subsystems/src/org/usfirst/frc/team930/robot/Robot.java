
package org.usfirst.frc.team930.robot;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	SubsystemHandler SH;
	
	MotionProfilingHandler motionProfilerLeft;
	MotionProfilingHandler motionProfilerRight;

    public void robotInit() {
    	 	
    	OutputManager.init();
    	OutputManager.teleopInit();
    	Drive.init();
    	//Loggable.init();
        SH = new SubsystemHandler(); // Begins the SystemHandler, which controls the speeds at which the subsystems are updated.
        SH.startSubsystems();
        
        motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1());
        motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1());       

        
    }
    
    public void autonomousInit() {
    	
    	OutputManager.autonomousInit();
    	
    	OutputManager.setDrivetrainMotionProfileMode();
    	
		motionProfilerLeft.control();
		motionProfilerRight.control();
    	
		CANTalon.SetValueMotionProfile setOutputLeft = motionProfilerLeft.getSetValue();
		OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
		
		CANTalon.SetValueMotionProfile setOutputRight = motionProfilerRight.getSetValue();
		OutputManager.setLeftDrivetrainCustomMode(setOutputRight);
    	
		motionProfilerLeft.startMotionProfile();
		motionProfilerRight.startMotionProfile();

    }

    public void autonomousPeriodic() {
    	OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_AUTO);
    	
    	motionProfilerLeft.control();
		motionProfilerRight.control();
    }

    public void teleopPeriodic() {
        OutputManager.teleopInit();
    }

    public void testPeriodic() {
    
    }
    
}
