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

	public void run() {

		BufferedWriter bwriter;

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
		
System.out.println("IT GOT HERE BUT NOT THEREEEEEEEEEEEEEEEEEEEEEEEEE" + '\n' + "IT GOT HERE BUT NOT THEREEEEEEEEEEEEEEEEEEEEEEEEE" + '\n' );
			try {
				System.out.println("IT WORK WORK WORK WORK WORK WORK");
				File f;	
				f = new File("C" + File.separator + "shooterspeed.txt");
			
				bwriter = new BufferedWriter(new FileWriter(f));
			if(f.exists()){
				f.createNewFile();
			}	
			if(!f.exists())		{
				System.out.println("it no work :(");
			}
			else{
				System.out.println("IT WORK");
			}
				
				bwriter.write(rearLeftMotorSpeed);
				bwriter.flush();
				bwriter.write(rearRightMotorSpeed);
				bwriter.flush();			
				bwriter.write(frontRightMotorSpeed);
				bwriter.flush();
				bwriter.write(frontLeftMotorSpeed);
				bwriter.flush();
				bwriter.write(leftSlaveMotorSpeed);
				bwriter.flush();
				bwriter.write(rightSlaveMotorSpeed);
				bwriter.flush();
				bwriter.write(shooterMotorSpeed);
				bwriter.flush();
				bwriter.write(intakeMotorSpeed);
				bwriter.flush();
				bwriter.write(climberMotorSpeed);
				bwriter.flush();
				bwriter.write(feederMotorSpeed);
				bwriter.flush();
				bwriter.close();
				

			} catch (IOException e) {

				// e.printStackTrace();
				System.err.println(e);

			}

		
		}
	}