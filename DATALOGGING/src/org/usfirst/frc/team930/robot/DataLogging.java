package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DataLogging extends SampleRobot {
	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick stick = new Joystick(0);
	CANTalon cantalon = new CANTalon(5);
	Spark spark = new Spark(0);
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();
	ArrayList<Loggable> logArray = new ArrayList<> ();

	public DataLogging() {
		myRobot.setExpiration(0.1);
		Loggable motorLog = new SparkSpeedLoggable(spark);
		logArray.add(motorLog);
	}
BufferedWriter bwriter;
	
	File file;


	
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
	}{
	  try {
		  File file = new File("DataLogging" + File.separator + "output.txt");
			bwriter = new BufferedWriter(new FileWriter(file));
		  if (!file.exists()) {
			  file.createNewFile();
		  }
		  
		  
	  } catch (IOException e) {
		  //e.printStackTrace();
		  System.err.println(e);	
	  } finally {
	    	
	  }
}

	@Override
	public void autonomous() {
		String autoSelected = chooser.getSelected();
				System.out.println("Auto selected: " + autoSelected);
	}
				
		

	
	@Override
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			  try {
				  for(int x = 0; x > logArray.size(); x++){
					 bwriter.write(logArray.get(x).log());
					 bwriter.flush();
				  }
					 bwriter.close();
					
				  } catch (IOException e) {
					  //e.printStackTrace();
					  System.err.println(e);
				  } 
				  
		    			
				  Timer.delay(0.005);


		}
	}

	
	@Override
	public void test() {
	}
}
