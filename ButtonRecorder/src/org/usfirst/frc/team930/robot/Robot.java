
package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.ArrayList;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.subsystems.AutonomousSaver;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.SystemA;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final SystemA systemA = new SystemA();
	public static final AutonomousSaver autonomousSaver = new AutonomousSaver();
	public static OI oi;
	
	//ButtonTracker: Initializing variables
	public static int step = 0;
	public static ArrayList<String> ATracker = new ArrayList<String>(); //temporary
    
    public static ArrayList<String> a0 = new ArrayList<String>();public static ArrayList<String> a1 = new ArrayList<String>();
    public static ArrayList<String> a2 = new ArrayList<String>();public static ArrayList<String> a3 = new ArrayList<String>();
    public static ArrayList<String> a4 = new ArrayList<String>();public static ArrayList<String> a5 = new ArrayList<String>();
    
    public static ArrayList<String> b0 = new ArrayList<String>();public static ArrayList<String> b1 = new ArrayList<String>();
    public static ArrayList<String> b2 = new ArrayList<String>();public static ArrayList<String> b3 = new ArrayList<String>();
    public static ArrayList<String> b4 = new ArrayList<String>();public static ArrayList<String> b5 = new ArrayList<String>();
    public static ArrayList<String> b6 = new ArrayList<String>();public static ArrayList<String> b7 = new ArrayList<String>();
    public static ArrayList<String> b8 = new ArrayList<String>();public static ArrayList<String> b9 = new ArrayList<String>();
    public static ArrayList<String> b10 = new ArrayList<String>();public static ArrayList<String> b11 = new ArrayList<String>();
    public static ArrayList<String> b12 = new ArrayList<String>();public static ArrayList<String> b13 = new ArrayList<String>();
    public static ArrayList<String> b14 = new ArrayList<String>();
    
    public static ArrayList<String> ai0 = new ArrayList<String>();public static ArrayList<String> ai1 = new ArrayList<String>();
    public static ArrayList<String> ai2 = new ArrayList<String>();public static ArrayList<String> ai3 = new ArrayList<String>();
    
    
    //-1 indicates unused ports, and is the default. Make sure to set these before teleop is started with the SmartDashboard
    public static int a0port = 0;public static int a1port = 1;public static int a2port = 2;
    public static int a3port = 3;public static int a4port = -1;public static int a5port = -1;
    
    public static int b0port = 1;public static int b1port = -1;public static int b2port = -1;public static int b3port = -1;
    public static int b4port = -1;public static int b5port = -1;public static int b6port = -1;public static int b7port = -1;
    public static int b8port = -1;public static int b9port = -1;public static int b10port = -1;public static int b11port = -1;
    public static int b12port = -1;public static int b13port = -1;public static int b14port = -1;
    /*Trying to read analog inputs crashed program. 
    public static int ai0port = 0;public static int ai1port = -1;public static int ai2port = -1;public static int ai3port = -1;
    //*/
    Preferences prefs;

    Command autonomousCommand;
    SendableChooser<Drive> chooser;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		OI.getInstance();
		prefs = Preferences.getInstance();
		
		prefs.putInt("Axis Number 0", a0port);
		prefs.putInt("Axis Number 1", a1port);
		prefs.putInt("Axis Number 2", a2port);
		prefs.putInt("Axis Number 3", a3port);
		prefs.putInt("Axis Number 4", a4port);
		prefs.putInt("Axis Number 5", a5port);
		
		prefs.putInt("Button Number 0", b0port);
		prefs.putInt("Button Number 1", b1port);
		prefs.putInt("Button Number 2", b2port);
		prefs.putInt("Button Number 3", b3port);
		prefs.putInt("Button Number 4", b4port);
		prefs.putInt("Button Number 5", b5port);
		prefs.putInt("Button Number 6", b6port);
		prefs.putInt("Button Number 7", b7port);
		prefs.putInt("Button Number 8", b8port);
		prefs.putInt("Button Number 9", b9port);
		prefs.putInt("Button Number 10", b10port);
		prefs.putInt("Button Number 11", b11port);
		prefs.putInt("Button Number 12", b12port);
		prefs.putInt("Button Number 13", b13port);
		prefs.putInt("Button Number 14", b14port);
		/*
		prefs.putInt("Analog Number 0", ai3port);
		prefs.putInt("Analog Number 1", ai1port);
		prefs.putInt("Analog Number 2", ai2port);
		prefs.putInt("Analog Number 3", ai3port);
		//*/
        //chooser = new SendableChooser<Drive>();
        //chooser.addDefault("Default Auto", new Drive());
        //chooser.addObject("My Auto", new MyAutoCommand());
        //SmartDashboard.putData("Auto mode", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        //condenseOutput = (Command) COChooser.getSelected();
        
        //ButtonTracker: At start of teleop, axis and button ports are updated with temporary changes
        a0port = prefs.getInt("Axis Number 0", -1);
        a1port = prefs.getInt("Axis Number 1", -1);
        a2port = prefs.getInt("Axis Number 2", -1);
        a3port = prefs.getInt("Axis Number 3", -1);
        a4port = prefs.getInt("Axis Number 4", -1);
        a5port = prefs.getInt("Axis Number 5", -1);
        
        b0port = prefs.getInt("Button Number 0", -1);
        b1port = prefs.getInt("Button Number 1", -1);
        b2port = prefs.getInt("Button Number 2", -1);
        b3port = prefs.getInt("Button Number 3", -1);
        b4port = prefs.getInt("Button Number 4", -1);
        b5port = prefs.getInt("Button Number 5", -1);
        b6port = prefs.getInt("Button Number 6", -1);
        b7port = prefs.getInt("Button Number 7", -1);
        b8port = prefs.getInt("Button Number 8", -1);
        b9port = prefs.getInt("Button Number 9", -1);
        b10port = prefs.getInt("Button Number 10", -1);
        b11port = prefs.getInt("Button Number 11", -1);
        b12port = prefs.getInt("Button Number 12", -1);
        b13port = prefs.getInt("Button Number 13", -1);
        b14port = prefs.getInt("Button Number 14", -1);
        /*
        ai0port = prefs.getInt("Analog Number 0", -1);
        ai1port = prefs.getInt("Analog Number 1", -1);
        ai2port = prefs.getInt("Analog Number 2", -1);
        ai3port = prefs.getInt("Analog Number 3", -1);
        //*/
        step = 0;
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        //ButtonTracker: Recording inputs into temporary memory
        if(OI.getInstance().buttonA.get()) ATracker.add(Robot.step, "true"); //temporary
    	else ATracker.add(step, "false");
        
        if(a0port >= 0 && a0port <= 5) a0.add(step, String.valueOf(OI.getInstance().stick.getRawAxis(a0port)));
        else a0.add(step, "null");
        if(a1port >= 0 && a1port <= 5) a1.add(step, String.valueOf(OI.getInstance().stick.getRawAxis(a1port)));
        else a1.add(step, "null");
        if(a2port >= 0 && a2port <= 5) a2.add(step, String.valueOf(OI.getInstance().stick.getRawAxis(a2port)));
        else a2.add(step, "null");
        if(a3port >= 0 && a3port <= 5) a3.add(step, String.valueOf(OI.getInstance().stick.getRawAxis(a3port)));
        else a3.add(step, "null");
        if(a4port >= 0 && a4port <= 5) a4.add(step, String.valueOf(OI.getInstance().stick.getRawAxis(a4port)));
        else a4.add(step, "null");
        if(a5port >= 0 && a5port <= 5) a5.add(step, String.valueOf(OI.getInstance().stick.getRawAxis(a5port)));
        else a5.add(step, "null");
        
        if(b0port >= 0 && b0port <= 14 && b0port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b0port)) b0.add(step, "true");
        	else b0.add(step, "false");
        }
        else b0.add(step, "null");
        if(b1port >= 0 && b1port <= 14 && b1port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b1port)) b1.add(step, "true");
        	else b1.add(step, "false");
        }
        else b1.add(step, "null");
        if(b2port >= 0 && b2port <= 14 && b2port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b2port)) b2.add(step, "true");
        	else b2.add(step, "false");
        }
        else b2.add(step, "null");
        if(b3port >= 0 && b3port <= 14 && b3port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b3port)) b3.add(step, "true");
        	else b3.add(step, "false");
        }
        else b3.add(step, "null");
        if(b4port >= 0 && b4port <= 14 && b4port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b4port)) b4.add(step, "true");
        	else b4.add(step, "false");
        }
        else b4.add(step, "null");
        if(b5port >= 0 && b5port <= 14 && b5port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b5port)) b5.add(step, "true");
        	else b5.add(step, "false");
        }
        else b5.add(step, "null");
        if(b6port >= 0 && b6port <= 14 && b6port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b6port)) b6.add(step, "true");
        	else b6.add(step, "false");
        }
        else b6.add(step, "null");
        if(b7port >= 0 && b7port <= 14 && b7port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b7port)) b7.add(step, "true");
        	else b7.add(step, "false");
        }
        else b7.add(step, "null");
        if(b8port >= 0 && b8port <= 14 && b8port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b8port)) b8.add(step, "true");
        	else b8.add(step, "false");
        }
        else b8.add(step, "null");
        if(b9port >= 0 && b9port <= 14 && b9port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b9port)) b9.add(step, "true");
        	else b9.add(step, "false");
        }
        else b9.add(step, "null");
        if(b10port >= 0 && b10port <= 14 && b10port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b10port)) b10.add(step, "true");
        	else b10.add(step, "false");
        }
        else b10.add(step, "null");
        if(b11port >= 0 && b11port <= 14 && b11port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b11port)) b11.add(step, "true");
        	else b11.add(step, "false");
        }
        else b11.add(step, "null");
        if(b12port >= 0 && b12port <= 14 && b12port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b12port)) b12.add(step, "true");
        	else b12.add(step, "false");
        }
        else b12.add(step, "null");
        if(b13port >= 0 && b13port <= 14 && b13port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b13port)) b13.add(step, "true");
        	else b13.add(step, "false");
        }
        else b13.add(step, "null");
        if(b14port >= 0 && b14port <= 14 && b14port != RobotMap.saveButtonPort) 
        {
        	if(OI.getInstance().stick.getRawButton(b14port)) b14.add(step, "true");
        	else b14.add(step, "false");
        }
        else b14.add(step, "null");
        /*
        
        if(ai0port >= 0 && ai0port <= 3) 
    	{
    		OI.getInstance().analogInput0 = new AnalogInput(ai0port);
    		ai0.add(step, String.valueOf(OI.getInstance().analogInput0.getValue()));
    	}
        else ai0.add(step, "null");
        if(ai1port >= 0 && ai1port <= 3) 
        {
        	OI.getInstance().analogInput1 = new AnalogInput(ai1port);
        	ai1.add(step, String.valueOf(OI.getInstance().analogInput1.getValue()));
       	}
        else ai1.add(step, "null");
        if(ai2port >= 0 && ai2port <= 3) 
        {
        	OI.getInstance().analogInput2 = new AnalogInput(ai2port);
        	ai2.add(step, String.valueOf(OI.getInstance().analogInput2.getValue()));
       	}
        else ai2.add(step, "null");
        if(ai3port >= 0 && ai3port <= 3) 
        {
        	OI.getInstance().analogInput3 = new AnalogInput(ai3port);
        	ai3.add(step, String.valueOf(OI.getInstance().analogInput3.getValue()));
       	}
        else ai3.add(step, "null");
        //*/
        step += 1;
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
