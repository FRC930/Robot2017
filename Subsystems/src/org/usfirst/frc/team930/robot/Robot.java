
package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	SubsystemHandler SH;

    public void robotInit() {
    	DSManager.init();
    	OutputManager.init();
        SH = new SubsystemHandler(); // Begins the SystemHandler, which controls the speeds at which the subsystems are updated.
        SH.startSubsystems();
        
    }
    
    public void autonomousInit() {

    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        
    }

    public void testPeriodic() {
    
    }
    
}
