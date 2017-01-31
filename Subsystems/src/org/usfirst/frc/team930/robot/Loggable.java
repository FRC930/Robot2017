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
		while (true) {
			
			try {
				
				File f = new File("c" + File.separator + System.currentTimeMillis() + "shooterspeed.txt");
				bwriter = new BufferedWriter(new FileWriter(f));
				
				if (!f.exists()) {
					
					f.createNewFile();
					
				}
				
				break;
				
			} catch (IOException e) {
				
				// e.printStackTrace();
				System.err.println(e);
				
			}
			
		}
		
		try {
			
			bwriter.write(rearLeftMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(rearRightMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(frontRightMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(frontLeftMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(leftSlaveMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(rightSlaveMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(shooterMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(intakeMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(climberMotorSpeed);
			bwriter.flush();
			bwriter.close();
			bwriter.write(feederMotorSpeed);
			bwriter.flush();
			bwriter.close();
		} catch (IOException e) {
			
			// e.printStackTrace();
			System.err.println(e);
			
		}
	}
}