package org.usfirst.frc.team930.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Loggable implements Runnable {

	  // Creates strings to use for ease of importing motor encoder values
	String frontRightMotorSpeed = "";
	String frontLeftMotorSpeed = "";
	String shooterMotorSpeed = "";
	String intakeMotorSpeed = "";
	String climberMotorSpeed = "";
	String elevatorMotorSpeed = "";
	private static BufferedWriter bwriter;
	private static File file;
	

	public void run() {

		// Gets values from output manager to pass to .csv
		frontLeftMotorSpeed = Double.toString(OutputManager.getFeedbackSpeed(OutputManager.Motors.L1MASTER));
		frontRightMotorSpeed = Double.toString(OutputManager.getFeedbackSpeed(OutputManager.Motors.R1MASTER));
		climberMotorSpeed = Double.toString(OutputManager.getCommandedSpeed(OutputManager.Motors.CLIMBER));
		elevatorMotorSpeed = Double.toString(OutputManager.getCommandedSpeed(OutputManager.Motors.ELEVATOR));
		intakeMotorSpeed = Double.toString(OutputManager.getCommandedSpeed(OutputManager.Motors.INTAKE));
		shooterMotorSpeed = Double.toString(OutputManager.getFeedbackSpeed(OutputManager.Motors.SHOOTER));

		// Creates writer, and reports errors.

		try {			
			bwriter.write(frontRightMotorSpeed + ", ");
			
			bwriter.write(frontLeftMotorSpeed + ", ");
									
			bwriter.write(shooterMotorSpeed + ", ");
			
			bwriter.write(intakeMotorSpeed + ", ");
			
			bwriter.write(climberMotorSpeed + ", ");
			
			bwriter.write(elevatorMotorSpeed + ", ");
			bwriter.newLine();
			
			
		} catch (IOException e) {

			// e.printStackTrace();
			System.err.println(e);

		}

	}
	// In the init method it creates one file that has all the data, and labels each set of values within the file
	public static void init() {
		try {
    		file = new File("DataLogging" + File.separator + System.currentTimeMillis() + "ShooterSpeed.csv");
    		bwriter = new BufferedWriter(new FileWriter(file));
    		
    			file.createNewFile();
    			bwriter.write("rearLeftMotorSpeed, ");
    			bwriter.write("rearRightMotorSpeed, ");
    			bwriter.write("frontRightMotorSpeed, ");
    			bwriter.write("frontLeftMotorSpeed, ");
    			bwriter.write("leftSlaveMotorSpeed, ");
    			bwriter.write("rightSlaveMotorSpeed, ");
    			bwriter.write("shooterMotorSpeed, ");
    			bwriter.write("intakeMotorSpeed, ");
    			bwriter.write("climberMotorSpeed, ");
    			bwriter.write("feederMotorSpeed, ");
    			bwriter.newLine();
    					

    	}catch(
    	IOException e)
    	{
    		// e.printStackTrace();
    		System.err.println(e);
    	}
	}
}

