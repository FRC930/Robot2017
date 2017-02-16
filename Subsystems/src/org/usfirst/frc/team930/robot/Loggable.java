package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;    //Imports necessary files 

public class Loggable implements Runnable {

	String rearLeftMotorSpeed = "";  // Creates strings to use for ease of importing motor encoder values
	String rearRightMotorSpeed = ""; 
	String frontRightMotorSpeed = "";
	String frontLeftMotorSpeed = "";
	String leftSlaveMotorSpeed = "";
	String rightSlaveMotorSpeed = "";
	String shooterMotorSpeed = "";
	String intakeMotorSpeed = "";
	String climberMotorSpeed = "";
	String feederMotorSpeed = "";
	private static BufferedWriter bwriter;
	private static File file;
	

	public void run() {

		// Gets values from output manager to pass to .csv
		rearLeftMotorSpeed = Double.toString(OutputManager.getTalonSpeedRearLeftMotor());
		rearRightMotorSpeed = Double.toString(OutputManager.getTalonSpeedRearRightMotor());
		frontRightMotorSpeed = Double.toString(OutputManager.getTalonSpeedFrontRightMotor());
		frontLeftMotorSpeed = Double.toString(OutputManager.getTalonSpeedFrontLeftMotor());
		leftSlaveMotorSpeed = Double.toString(OutputManager.getTalonSpeedLeftSlave());
		rightSlaveMotorSpeed = Double.toString(OutputManager.getTalonSpeedRightSlave());
		shooterMotorSpeed = Double.toString(OutputManager.getTalonShooterMotor());
		intakeMotorSpeed = Double.toString(OutputManager.getSparkSpeedIntake());
		climberMotorSpeed = Double.toString(OutputManager.getSparkSpeedClimber());
		feederMotorSpeed = Double.toString(OutputManager.getSparkSpeedElevator());

		// Creates writer, and reports errors.

		try {
			bwriter.write(rearLeftMotorSpeed + ", ");
			
			bwriter.write(rearRightMotorSpeed + ", ");
			
			bwriter.write(frontRightMotorSpeed + ", ");
			
			bwriter.write(frontLeftMotorSpeed + ", ");
			
			bwriter.write(leftSlaveMotorSpeed + ", ");
			
			bwriter.write(rightSlaveMotorSpeed + ", ");
			
			bwriter.write(shooterMotorSpeed + ", ");
			
			bwriter.write(intakeMotorSpeed + ", ");
			
			bwriter.write(climberMotorSpeed + ", ");
			
			bwriter.write(feederMotorSpeed + ", ");
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

