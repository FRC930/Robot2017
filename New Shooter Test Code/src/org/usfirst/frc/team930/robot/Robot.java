package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



		public class Robot extends SampleRobot {
			RobotDrive myRobot = new RobotDrive(5, 6);
			Joystick stick = new Joystick(0);
			final String defaultAuto = "Default";   //creates the class and the constructors for the motors and the joystick.
			final String customAuto = "My Auto";
			SendableChooser<String> chooser = new SendableChooser<>();
			Spark hello = new Spark(0);     
			Spark hello1 = new Spark(1);
			CANTalon Motor1 = new CANTalon(5);
			CANTalon Motor2 = new CANTalon(6);
			PowerDistributionPanel PDP = new PowerDistributionPanel();
			SmartDashboard SD = new SmartDashboard();
			
	double speed2 = 50;
	boolean Apressed = false;  //Sets variables necessary to operate the buttons.
	boolean Bpressed = false;
	boolean Ypressed = false;
	boolean onoff = false;
	double clamppos = 1;
	double clampneg = -1;
	


	public Robot() {
		myRobot.setExpiration(0.1);
}

@Override
	public void robotInit() {
	chooser.addDefault("Default Auto", defaultAuto);
	chooser.addObject("My Auto", customAuto);
	Motor1.changeControlMode(TalonControlMode.PercentVbus);
	Motor2.changeControlMode(TalonControlMode.PercentVbus); 
}  //changes the control mode of the motors to PercentVbus

@Override
	public void autonomous() {    //puts the robot in autonomous mode
	String autoSelected = chooser.getSelected();
	System.out.println("Auto selected: " + autoSelected);
}

@Override
	  public void operatorControl() {  //puts the robot into teleop mode.
	  myRobot.setSafetyEnabled(true);
	  while (isOperatorControl() && isEnabled()) {


		  if(stick.getRawButton(4) && (!Bpressed)){
			  speed2 += 0.025;                      //if the Y button is pressed it increases the speed by 2.5 percent 
			  Bpressed = true;
		  } else if((!stick.getRawButton(4)) && Bpressed){
			  Bpressed = false;
		  }

		  if(stick.getRawButton(1) && (!Apressed)){
			  speed2 -= 0.025;  // If the A button is pressed it decreases the speed by 2.5 percent
			  Apressed = true;
		  } else if((!stick.getRawButton(1)) && Apressed){
			  Apressed = false;
		  }

		  if(stick.getRawButton(2) && (!Ypressed)){
			  Ypressed = true; //If the B button is pressed it either starts or stops the motor based on whether the motor was on or off
  			  onoff = !onoff;
		  } else if((!stick.getRawButton(2)) && Ypressed){
			  Ypressed = false;
		  }

		  if (onoff){
			  hello.set(speed2);       // actually turns on the motors 
			  hello1.set(speed2*-1.0);
			  SmartDashboard.putNumber("Channel 11's Current is", PDP.getCurrent(11));
		  } else {
			  hello.set(0);
			  hello1.set(0);
			  System.out.print("system is off"); 
		  }
		  if(speed2 >= clamppos){ //if the speed is too high it will clamp it and the controller will rumble
			  speed2 = 1;
			  stick.setRumble(RumbleType.kLeftRumble, Math.abs(stick.getRawAxis(0)));
			  stick.setRumble(RumbleType.kRightRumble, Math.abs(stick.getRawAxis(0)));
		  }
		  if(speed2<= clampneg){  //if the speed is too low it clamps it and the controller rumbles
			  speed2 = -1;
			  stick.setRumble(RumbleType.kLeftRumble, Math.abs(stick.getRawAxis(0)));
			  stick.setRumble(RumbleType.kRightRumble, Math.abs(stick.getRawAxis(0)));
		  }
		  System.err.println(speed2);


		  Timer.delay(0.005); // wait for a motor update time
	  }
 }


@Override
public void test() {
}
}


