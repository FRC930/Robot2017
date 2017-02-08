
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

    public void robotInit() {
    	 	
    	OutputManager.init();
    	Drive.init();
    	Loggable.init();
        SH = new SubsystemHandler(); // Begins the SystemHandler, which controls the speeds at which the subsystems are updated.
        SH.startSubsystems();

       
       
        
    }
    
    public void autonomousInit() {
    	MotionProfilingHandler motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1());
    	MotionProfilingHandler motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1());
    	
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
    }

    public void teleopPeriodic() {
        
    }

    public void testPeriodic() {
    
    }
    
}
