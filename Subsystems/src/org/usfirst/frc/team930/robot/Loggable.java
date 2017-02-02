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
		rearLeftMotorSpeed = OutputManager.getTalonSpeedRearLeftMotor();
		rearRightMotorSpeed = OutputManager.getTalonSpeedRearRightMotor();
		frontRightMotorSpeed = OutputManager.getTalonSpeedFrontRightMotor();
		frontLeftMotorSpeed = OutputManager.getTalonSpeedFrontLefttMotor();
		leftSlaveMotorSpeed = OutputManager.getTalonSpeedLeftSlave();
		rightSlaveMotorSpeed = OutputManager.getTalonSpeedRightSlave();
		shooterMotorSpeed = OutputManager.getTalonShooterMotor();
		intakeMotorSpeed = OutputManager.getSparkSpeedIntake();
		climberMotorSpeed = OutputManager.getSparkSpeedClimber();
		feederMotorSpeed = OutputManager.getSparkSpeedFeeder();

		// Creates writer, and reports errors.
		
System.out.println("IT GOT HERE BUT NOT THEREEEEEEEEEEEEEEEEEEEEEEEEE" + '\n' + "IT GOT HERE BUT NOT THEREEEEEEEEEEEEEEEEEEEEEEEEE" + '\n' );
			try {
				System.out.println("IT WORK WORK WORK WORK WORK");
				File f;	
				f = new File("/VISION" + File.separator + "shooterspeed.txt");
			
				bwriter = new BufferedWriter(new FileWriter(f));
			
				f.createNewFile();
			if(!f.exists())		{
				System.out.println("it no work :(");
			}
			else{
				System.out.println("IT WORK");
			}
				
				/*bwriter.write(rearLeftMotorSpeed);
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
	*/			

			} catch (IOException e) {

				// e.printStackTrace();
				System.err.println(e);

			}

		
		}
	}