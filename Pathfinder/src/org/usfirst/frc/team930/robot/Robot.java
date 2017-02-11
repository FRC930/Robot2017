package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Robot extends SampleRobot {
	RobotDrive myRobot = new RobotDrive(0, 1);


	public Robot() {
		
	}

	public void robotInit() {
	    File file;
	    file = new File("c" + File.separator + "HoldUPWEDEMPOINTS.csv");
	    try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
		        Waypoint[] points = new Waypoint[] {
		                new Waypoint(-4, -1, Pathfinder.d2r(-45)),
		                new Waypoint(-2, -2, 0),
		                new Waypoint(0, 0, 0)
		        };

		        Trajectory trajectory = Pathfinder.generate(points, config);

		        // Wheelbase Width = 0.5m
		        TankModifier modifier = new TankModifier(trajectory).modify(0.5);

		        // Do something with the new Trajectories...
		        Trajectory left = modifier.getLeftTrajectory();
		        Trajectory right = modifier.getRightTrajectory();
		        Pathfinder.writeToCSV(file, trajectory);
		        }
	

			
		
		/*	File file;
		file = new File("c" + File.separator + "HoldUPWEDEMPOINTS.csv");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pathfinder PF = new Pathfinder();
		Waypoint WP0 = new Waypoint(0, 0, 0);
		Waypoint WP1 = new Waypoint(0, 1, 0);
		Waypoint WP2 = new Waypoint(0, 2, 0);
		Waypoint WP3 = new Waypoint(0, 3, 0);
		Waypoint[] myWayPointArray = {WP0, WP1, WP2, WP3};
		Trajectory TJ = new Trajectory(3);
		Trajectory.Config TJCNFG = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, 100000, 1, 9, 1, 1);
		try {
		TJ = PF.generate(myWayPointArray, TJCNFG );
		
		PF.writeToCSV(file, TJ);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	@Override
	public void autonomous() {
	}
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
		}
}
	
	}
