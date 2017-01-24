
package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	 
	   double targetAngle = 0;
	   double speed = 0;
	   
	   boolean turnRight = true;
	   boolean Doneturning = false; 
   
	   AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	   
	   //Left Motors on robot
	   CANTalon LeftFront = new CANTalon(0);
	   CANTalon LeftMiddle = new CANTalon(1); 
	   CANTalon LeftBack = new CANTalon(2);
	   
	   //Right Motors on robot
	   CANTalon RightFront = new CANTalon(3);    
	   CANTalon RightMiddle = new CANTalon(4);
	   CANTalon RightBack = new CANTalon(5); 
	   
	   //Used to get error
	   double getError(){
		   return targetAngle - gyro.getAngle();
	   }
	   
	   
	   while (getError() != targetAngle) {
		   
		   if (getError() >= 0) {
			   turnRight = true;
		   } else {
			   turnRight = false;
		   }
		   
		   if (turnRight) {
			   
			   while (getError() <= targetAngle) {
				   
				   if (Math.abs(getError()) >  0.6) {
		    		   speed = .6;
				   } else if (Math.abs(getError()) <  0.1) {
					   speed = 0.1;
				   } else {
					   speed = getError();
				   }
				   
				   //Sets left motors to new speed
				   LeftFront.set(speed);
				   LeftMiddle.set(speed);
				   LeftBack.set(speed);
				   
				   //Sets right motors to new speed
				   RightFront.set(-speed);
				   RightMiddle.set(-speed);
				   RightBack.set(-speed);
			   }
		   } else {
		       
			   while (getError() >= targetAngle) {
				   
				   //Maths
				   if (Math.abs(getError()) >  0.6) {
					   speed = .6;
				   } else if (Math.abs(getError()) <  0.1) {
					   speed = 0.1;
				   } else {
					   speed = getError();
				   }
				   
				   //Sets new left motor speeds
				   LeftFront.set(-speed);
				   LeftMiddle.set(-speed);
				   LeftBack.set(-speed);
				   
				   //Sets new right motor speeds
				   RightFront.set(speed);
				   RightMiddle.set(speed);
				   RightBack.set(speed);
			   }
		   }
		   Timer.delay(0.005);
	   }
}