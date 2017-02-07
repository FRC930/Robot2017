package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;

public class Loggable implements Runnable {

	String rearLeftMotorSpeed = "";
	String rearRightMotorSpeed = "";
	String frontRightMotorSpeed = "";
	String frontLeftMotorSpeed = "";
	String leftSlaveMotorSpeed = "";
	String rightSlaveMotorSpeed = "";
	String shooterMotorSpeed = "";
	String intakeMotorSpeed = "";
	String climberMotorSpeed = "";
	String feederMotorSpeed = ""; 
	BufferedWriter bwriter;
	File file = null;{
	
	if(!file.exists()) {
		try {
			  file = new File("DataLogging" + File.separator + "output.txt");
				bwriter = new BufferedWriter(new FileWriter(file));
			  if (!file.exists()) {
				  file.createNewFile();
			  }
			  
			  
		  } catch (IOException e) {
			  //e.printStackTrace();
			  System.err.println(e);	
		  } 
		}
	}
		 public void run() {

		// Gets values from output manager to pass to .txt -> .xl
		rearLeftMotorSpeed = Double.toString(OutputManager.getTalonSpeedRearLeftMotor());
		rearRightMotorSpeed = Double.toString(OutputManager.getTalonSpeedRearRightMotor());
		frontRightMotorSpeed = Double.toString(OutputManager.getTalonSpeedFrontRightMotor());
		frontLeftMotorSpeed = Double.toString(OutputManager.getTalonSpeedFrontLeftMotor());
		leftSlaveMotorSpeed = Double.toString(OutputManager.getTalonSpeedLeftSlave());
		rightSlaveMotorSpeed = Double.toString(OutputManager.getTalonSpeedRightSlave());
		shooterMotorSpeed = Double.toString(OutputManager.getTalonShooterMotor());
		intakeMotorSpeed = Double.toString(OutputManager.getSparkSpeedIntake());
		climberMotorSpeed = Double.toString(OutputManager.getSparkSpeedClimber());
		feederMotorSpeed = Double.toString(OutputManager.getSparkSpeedFeeder());

		// Creates writer, and reports errors.

		try {

			bwriter.write(rearLeftMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(rearRightMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(frontRightMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(frontLeftMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(leftSlaveMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(rightSlaveMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(shooterMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(intakeMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(climberMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.write(feederMotorSpeed);
			bwriter.flush();
			bwriter.newLine();
			bwriter.close();

		} catch (IOException e) {

			// e.printStackTrace();
			System.err.println(e);

		}

	}

}	
