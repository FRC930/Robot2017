
package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team930.robot.commands.ExampleCommand;
import org.usfirst.frc.team930.robot.subsystems.ExampleSubsystem;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;


	public class Robot extends SampleRobot {
	   double targetAngle = 0;
	   boolean Doneturning = false; 
	   double speed = 0;
	   boolean turnRight = true;
	   double getError(){
	   return targetAngle - gyro.getAngle();
	   }
	   AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	   CANTalon Motor1 = new CANTalon(0);
	   CANTalon Motor2 = new CANTalon(1);{
		   while(gyro.getAngle() != targetAngle ){   
	double chooseDirection = getError(); {
		if(chooseDirection <= 0){
	    turnRight = true;
		} else { 
		turnRight = false;
	} if(turnRight){
		while(gyro.getAngle() >= targetAngle){
	
			if(Math.abs(getError()) <= 60){
			speed = 0.6;
	} 		else if(Math.abs(getError()) >= 60){
			speed = 0.1;
			} else {
				speed = getError();
				Motor1.set(speed); 
				Motor2.set(speed * -1); 
			}
				else {
		while(gyro.getAngle() <= targetAngle){
	
		if(Math.abs(getError()) <= 60){
				speed = 0.6;
		} else if(Math.abs(getError()) >= 60){
				speed = 0.1;
		} else {
			speed = getError();
		} Motor2.set(speed);
			Motor1.set(speed * -1);	
	}
		}	

	}
	}
		
	}
}
