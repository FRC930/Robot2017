package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;

public class Loggable implements Runnable{
	String rearLeftMotorSpeed = "";
	String rearRightMotorSpeed = "";
	String frontRightMotorSpeed = "";
	String frontLeftMotorSpeed = "";
	String leftSlaveMotorSpeed = "";
	String rightSlaveMotorSpeed = "";
	String shooterMotorSpeed = "";
	public void run(){
		
		BufferedWriter bwriter;
	
					
		rearLeftMotorSpeed = OutputManager.getTalonSpeedRearLeftMotor();
		rearRightMotorSpeed = OutputManager.getTalonSpeedRearRightMotor();
		frontRightMotorSpeed = OutputManager.getTalonSpeedFrontRightMotor();
		frontLeftMotorSpeed = OutputManager.getTalonSpeedFrontLeftMotor();
		leftSlaveMotorSpeed = OuptutManager.getTalonSpeedLeftSlave();
		rightSlaveMotorSpeed = OutputManager.getTalonSpeedRightSlave();
		shooterMotorSpeed = OutputManager.getTalonShooterMotor();
		
		 bwriter.write(rearLeftMotorSpeed);
		 bwriter.flush();
		 bwriter.close();
		
		
			BufferedWriter bwriter;
			
			File file;
			@Override
			public void robotInit() {
				  //Creates writer, and reports errors.
				  try {
					  File file = new File("c" + File.separator + "output.txt");
						bwriter = new BufferedWriter(new FileWriter(file));
					  if (!file.exists()) {
						  file.createNewFile();
					  }
					  
					  
				  } catch (IOException e) {
					  //e.printStackTrace();
					  System.err.println(e);	
				  } finally {
		
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
							 							 
						
					  } catch (IOException e) {
						  //e.printStackTrace();
						  System.err.println(e);

