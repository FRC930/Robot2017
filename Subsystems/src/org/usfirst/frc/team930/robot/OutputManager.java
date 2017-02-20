package org.usfirst.frc.team930.robot;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

//import org.usfirst.frc.team930.robot.DriveMotionProfiler.MotionProfileDrivetrainSide;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OutputManager {
	
	//Enum for Light Patterns on Arduino
	 enum LightPatterns {
		 LIGHTS_AUTO,
		 LIGHTS_TELE,
		 LIGHTS_DISABLE,
		 LIGHTS_SHOOT,
		 LIGHTS_DRIVE,
		 LIGHTS_CLIMB,
		 LIGHTS_INTAKE,
		 LIGHTS_FUUN
	 }
	 
	public enum MotionProfileDrivetrainSide {
		
		DRIVE_LEFT_SIDE,
		DRIVE_RIGHT_SIDE
		
	}
	 
	 enum Motors {
		 L1MASTER,
		 L2SLAVE,
		 L3SLAVE,
		 R1MASTER,
		 R2SLAVE,
		 R3SLAVE,
		 SHOOTER,
		 INTAKE,
		 CLIMBER,
		 ELEVATOR
		 
	 }
	 
	 
	 // Declaring Pins on RoboRio
	 
	private static	DigitalOutput lightPin0 = new DigitalOutput(0);
	private static	DigitalOutput lightPin1 = new DigitalOutput(1);
	private static	DigitalOutput lightPin2 = new DigitalOutput(2);
	
	//PDP Class used to monitor current
	private static	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	// Declaring robot motors
	// 6 Drivetrain motor declarations
	
	public static CANTalon L1Master; 	
	private static CANTalon L2Slave;
	private static CANTalon L3Slave;
	public static CANTalon R1Master;
	private static CANTalon R2Slave;
	private static CANTalon R3Slave; 
		
	// 1 Shooter Talon declaration
	private static CANTalon shooter;
	private static CANTalon shooterMotor2;
	
	// 1 Intake  Spark Motor controller declaration
	private static Spark intake;
	
	// 1 Climber  Spark Motor controller declaration
	private static Spark climber;
	
	// Elevator 1 Spark, either combine with shooter or make own subsystem
	private static Spark elevator;
	
	private static boolean isRobotTeleop;
	private static boolean isRobotAuton;
	
	
	private static CANTalon.MotionProfileStatus statusL = new CANTalon.MotionProfileStatus();
	private static CANTalon.MotionProfileStatus statusR = new CANTalon.MotionProfileStatus();

	
	//public static MotionProfilingHandler motionProfilerLeft;
	//public static MotionProfilingHandler motionProfilerRight;
	
	public static void init(){
		
		// Initializing motors
		// Initializes Motors for drivetrain	

		L1Master = new CANTalon(Constants.L1_MOTOR_CHANNEL, 5);
		L2Slave = new CANTalon(Constants.L2_MOTOR_CHANNEL, 5);
		L3Slave = new CANTalon(Constants.L3_MOTOR_CHANNEL, 5);
		
		L1Master.setInverted(true);
		L2Slave.setInverted(true);
		L3Slave.setInverted(true);
		
		L1Master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		L1Master.configEncoderCodesPerRev(250);
		L1Master.setVoltageRampRate(6400);
		L1Master.setF(1.0);
		L1Master.setP(10.0);
		L1Master.setI(0.0003);
		
		R1Master = new CANTalon(Constants.R1_MOTOR_CHANNEL, 5);
		R2Slave = new CANTalon(Constants.R2_MOTOR_CHANNEL, 5);
		R3Slave = new CANTalon(Constants.R3_MOTOR_CHANNEL, 5);
		
		R1Master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		R1Master.configEncoderCodesPerRev(250);
		R1Master.setVoltageRampRate(6400);
		R1Master.setF(1.0);
		R1Master.setP(10.0);
		R1Master.setI(0.0003);
		

        //motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE);
        //motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE);       
		
		shooter = new CANTalon (Constants.SHOOTER_MOTOR_CHANNEL);
        shooterMotor2 = new CANTalon (Constants.SHOOTER_MOTOR_CHANNEL2);
        //shooter.setF(1);
        //shooter.setP(100);
        //shooter.setD(0);
        //shooter.setI(0);
        //F1
        //P100
        //I
        //d0
		shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		

        //motionProfilerLeft = new MotionProfilingHandler(OutputManager.getL1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE);
        //motionProfilerRight = new MotionProfilingHandler(OutputManager.getR1(), MotionProfilingHandler.MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE);       

		// Spark Range is 2.003 ms Full Forward - .999 ms Full Reverse
		intake = new Spark (Constants.INTAKE_MOTOR_CHANNEL);
		
		climber = new Spark (Constants.CLIMBER_MOTOR_CHANNEL);
		
		elevator = new Spark (Constants.ELEVATOR_MOTOR_CHANNEL);
		
		isRobotTeleop = false;
		isRobotAuton = false;
		
		
	}
	
	// Sets the speed for the motors on the right side of the robot drivetrain.
	public static void setSpeedR(double speed){
		
		R1Master.set(speed);

		
	}
	
	// Sets the speed for the motors on the left side of the robot drivetrain.
	public static void setSpeedL(double speed){
		
		L1Master.set(speed);

		
	}
	
	// Sets the Drivetrain motors to Speed Mode.
	public static void setDrivetrainMode(CANTalon.TalonControlMode mode){
		
		L1Master.changeControlMode(mode);
		L2Slave.changeControlMode(CANTalon.TalonControlMode.Follower);
		L3Slave.changeControlMode(CANTalon.TalonControlMode.Follower);
	
		L2Slave.set(L1Master.getDeviceID());
		L3Slave.set(L1Master.getDeviceID());
		
		//------------------------------------\\
		
		R1Master.changeControlMode(mode);
		R2Slave.changeControlMode(CANTalon.TalonControlMode.Follower);
		R3Slave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		R2Slave.set(R1Master.getDeviceID());
		R3Slave.set(R1Master.getDeviceID());
		
	}
	
	// Sets the Drivetrain motors to Disabled Mode.
	public static void setDrivetrainDisabledMode(){
		
		R1Master.changeControlMode(CANTalon.TalonControlMode.Disabled);
		R2Slave.changeControlMode(CANTalon.TalonControlMode.Disabled);
		R3Slave.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
		L1Master.changeControlMode(CANTalon.TalonControlMode.Disabled);
		L2Slave.changeControlMode(CANTalon.TalonControlMode.Disabled);
		L3Slave.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
	}
	public static void setLeftDrivetrainCustomMode(CANTalon.SetValueMotionProfile setValue){
		
		L1Master.set(setValue.value);
		
	}
	public static void setRightDrivetrainCustomMode(CANTalon.SetValueMotionProfile setValue){
		
		R1Master.set(setValue.value);
		
	}
	
	// Mutator method to set the speed of the shooter [-1,1]
	public static void setShooterSpeed( double speed ){
		
		shooter.set(speed);
		shooterMotor2.set(speed);

		
	}
	
	// Changes the mode of the shooter to speed
	public static void setShooterSpeedMode(){
		
		shooter.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterMotor2.set(Constants.SHOOTER_MOTOR_CHANNEL);
		
	}
	
	// Changes the mode of the shooter to disabled
	public static void setShooterDisabledMode(){
		
		shooter.changeControlMode(CANTalon.TalonControlMode.Disabled);
		
	}
	
	// Changes the mode of the shooter to PercentVbus
	public static void setShooterPercentVbusMode(){
		
		shooter.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterMotor2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
	}

	// Mutator method to set the speed of the intake motor [-1,1]
	public static void setIntakeSpeed( double speed ){
		
		intake.set(speed);
		
	}
	//Sets the Elevator Speed
	public static void setSpeedElevator(double speed){
		
		 elevator.set(speed);
		
	}
	// Mutator method to set the speed of the climber motor [-1,1]
	public static void setClimberSpeed( double speed ){
		
		climber.set(speed);
		
	}
	
	// Series of Talon accessor classes, mainly used in loggable.
	// Return a string value of the speed units will be in the sensor's native ticks per 100ms.
	
	public static double getCommandedSpeed(Motors motor){
	
		switch(motor) {
		
		case L1MASTER:
			return L1Master.get();
			
		case L2SLAVE:
			return L2Slave.get();
			
		case L3SLAVE:
			return L3Slave.get();
			
		case R1MASTER:
			return R1Master.get();
			
		case R2SLAVE:
			return R2Slave.get();
			
		case R3SLAVE:
			return R3Slave.get();
			
		case SHOOTER:
			return shooter.get();
			
		case ELEVATOR:
			return elevator.get();
			
		case INTAKE:
			return intake.get();
			
		case CLIMBER:
			return climber.get();
			
		default:
			return -999;
		}
		
	}
	public static double getFeedbackSpeed(Motors motor){
		
		switch(motor) {
		
		case L1MASTER:
			return L1Master.getSpeed();
			
		case R1MASTER:
			return R1Master.getSpeed();
			
		case SHOOTER:
			return shooter.getSpeed();
			
		default:
			return -999;
		}
		
	}
	
	public static double getPDPChannelCurrent(int Channel){
		
		return PDP.getCurrent(Channel);
		
	}
	public static double getL1Voltage() {
		
		return L1Master.getOutputVoltage();
		
	}
	public static double getR1Voltage() {
		
		return R1Master.getOutputVoltage();
		
	}

	public static void setLights(LightPatterns L){
		switch(L){
		
		case LIGHTS_AUTO:	
			lightPin0.set(true);
			lightPin1.set(true);
			lightPin2.set(false);
			break;
		
		
		case LIGHTS_TELE:	
			lightPin0.set(false);
			lightPin1.set(false);
			lightPin2.set(false);
			break;
		
		
		case LIGHTS_DISABLE:	
			lightPin0.set(true);
			lightPin1.set(false);
			lightPin2.set(false);
			break;
		
		
		case LIGHTS_SHOOT:	
			lightPin0.set(true);
			lightPin1.set(true);
			lightPin2.set(true);
			break;
			
			
		case LIGHTS_CLIMB:	
			lightPin0.set(true);
			lightPin1.set(false);
			lightPin2.set(true);
			break;
			
			
		case LIGHTS_DRIVE:	
			lightPin0.set(false);
			lightPin1.set(true);
			lightPin2.set(true);
			break;
	
			
		case LIGHTS_INTAKE:	
			lightPin0.set(false);
			lightPin1.set(true);
			lightPin2.set(false);
			break;
			
			
		case LIGHTS_FUUN:	
			lightPin0.set(false);
			lightPin1.set(false);
			lightPin2.set(true);
			break;
		}
		// Why is there no false true true?
	}
	
	public static void teleopInit(){
			
		isRobotAuton = false;
			
		isRobotTeleop = true;
			
	}
	public static void disabledInit(){
		
		isRobotTeleop = false;
		isRobotAuton = false;
		
	}
	public static void autonomousInit(){
			
		isRobotTeleop = false;	
		
		isRobotAuton = true;
		
		/*
		OutputManager.setDrivetrainMode(CANTalon.TalonControlMode.MotionProfile);
    	
	    motionProfilerLeft.control();
		motionProfilerRight.control();
    	
		CANTalon.SetValueMotionProfile setOutputLeft = motionProfilerLeft.getSetValue();
		OutputManager.setLeftDrivetrainCustomMode(setOutputLeft);
		
		CANTalon.SetValueMotionProfile setOutputRight = motionProfilerRight.getSetValue();
		OutputManager.setRightDrivetrainCustomMode(setOutputRight);
    	
		motionProfilerLeft.startMotionProfile();
		motionProfilerRight.startMotionProfile(); 
		*/
	}
	
	public static boolean isRobotTeleop(){
		
		return isRobotTeleop;
		
	}
	
	public static boolean isRobotAuton(){
		
		return isRobotAuton;  
		
	}
	
	public static void startMotionProfiler(){
		
		L1Master.set(CANTalon.SetValueMotionProfile.Enable.value);
		R1Master.set(CANTalon.SetValueMotionProfile.Enable.value);
	
	}
public static void endMotionProfiler(){
		
		L1Master.set(CANTalon.SetValueMotionProfile.Disable.value);
		R1Master.set(CANTalon.SetValueMotionProfile.Disable.value);
		profilerRun(false);
	
	}
	
	
	public static void OnUnderrun() {
		
		
		System.out.format("%s\n", "UNDERRUN");
	
	}
	
	public static void OnNoProgress() {
		
		System.out.format("%s\n", "NOPROGRESS");
		
	}
	
	public static CANTalon getTalon(MotionProfileDrivetrainSide side){
		if(side == MotionProfileDrivetrainSide.DRIVE_LEFT_SIDE){
			return L1Master;
		}
		else if(side == MotionProfileDrivetrainSide.DRIVE_RIGHT_SIDE){
			return R1Master;
		}
		else{
			return null;
		}

	} 
	public static void profilerRun(boolean check){
		
		DriveMotionProfiler.isRunning = check;
		
	}
	
	public static void changeMotionControlFramePeriod(){
		
		L1Master.changeMotionControlFramePeriod(5);
		R1Master.changeMotionControlFramePeriod(5);
	
	}
	
	public static void getMotionProfileStatus(){
      
		L1Master.getMotionProfileStatus(statusL);

		R1Master.getMotionProfileStatus(statusR);
		
	}
	
	public static void clearMotionProfileHasUnderrun(){

		L1Master.clearMotionProfileHasUnderrun();

		R1Master.clearMotionProfileHasUnderrun();
	
	}
	
	public static void pushMotionProfileTrajectoryLeft(CANTalon.TrajectoryPoint point){
		
		L1Master.pushMotionProfileTrajectory(point);

	}
	
	public static void pushMotionProfileTrajectoryRight(CANTalon.TrajectoryPoint point) {
		
		R1Master.pushMotionProfileTrajectory(point);
	
	}
	
	public  static void bufferTalons(){
		
		L1Master.processMotionProfileBuffer();;

		R1Master.processMotionProfileBuffer();;
	
	}
	
	public static void clearMotionProfileTrajectories(){
		
		L1Master.clearMotionProfileTrajectories();;

		R1Master.clearMotionProfileTrajectories();;
		
	}
	
	public static int GetBtmBufferCntRight(){
		
		getMotionProfileStatus();
		
		return statusR.btmBufferCnt;
		
	}
	
	public static int GetBtmBufferCntLeft(){
		
		getMotionProfileStatus();
		
		return statusL.btmBufferCnt;
		
	}
	
	public static boolean isLeftStatusUnderrun(){
		
		getMotionProfileStatus();
		
		return statusL.isUnderrun;
		
	}
	
	public static boolean isRightStatusUnderrun(){
		
		getMotionProfileStatus();
		
		return statusR.isUnderrun;
		
	}
	public static void switchTeleopBool(){
		if (isRobotTeleop)
			isRobotTeleop = false;
		else
			isRobotTeleop = true;
	}
	public static void switchAutonBool(){
		if (isRobotAuton)
			isRobotAuton = false;
		else
			isRobotAuton = true;
	}
	
	public static boolean isRightActivePointValid(){
		
		getMotionProfileStatus();
		
		if (statusR.activePointValid && statusR.activePoint.isLastPoint){
		
			return true;
		
		}
		
		else{ 
		
			return false;
		
		}
	
	}
	
	public static boolean isLeftActivePointValid(){
		
		getMotionProfileStatus();
		
		if (statusL.activePointValid && statusL.activePoint.isLastPoint){
		
			return true;
		
		}
		
		else{ 
		
			return false;
		
		}
	
	}
	
}