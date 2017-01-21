package org.usfirst.frc.team930.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;


	public class Robot extends SampleRobot {
	
double kp = 0.1;        //Creates and defines necessary variables
double ki = 0;
double kd = 0;
double midpoint = 0;
double integral = 0;
double derivative = 0;
double error = 0;
double correction = 0;
double baseSpeed = 0.0;
double lastError = 0;
int hi = 0;


Joystick stick = new Joystick(0); //creates objects for the 2 Spark motors, the joystick, and the gyro sensor
Spark Motor1 = new Spark(0);
Spark Motor2 = new Spark(1);
AHRS gyro = new AHRS(SerialPort.Port.kUSB);


public void operatorControl() {
	while(hi == 0){
		if(stick.getRawButton(1)){
			hi ++;
		}
	System.out.println("GYRO ANGLE IS " + gyro.getAngle());
System.out.println("button 2 " + stick.getRawButton(2));
	while(stick.getRawButton(2)) {  //while the button is pressed the robot executes the PID code
		 error = midpoint - gyro.getAngle();                                         
				 derivative = error - lastError;
				  correction = (error * kp) + (integral * ki) + (derivative * kd);
						Motor2.set(baseSpeed + correction);
						Motor1.set(baseSpeed + (-1 * correction));
						lastError = error;
						System.out.println("GYRO ANGLE IS " + gyro.getAngle());
						
/*It subtracts the current gyro value from the desired gyro value(midpoint)
and calls that the error. The error is then added to the current integral and
that sum is the new value of the integral. Then the current error is subtracted
from the last error and that difference becomes the current value of the derivative. 
Then the (current error)*kp is added to the integral*ki and the derivative*kd and that
sum is set as the correction which is subtracted from one mototr's base speed and 
added to the other's. Finally current error becomes the last error and the program 
repeats until the user ________________________________
*/	}
	}
	}
}
