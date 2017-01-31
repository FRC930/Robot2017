package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Notifier;

// This class contains the framework for how the subsystems update
public class SubsystemHandler {

		private final Drive myDrive;
		private final Shoot myShoot;
		private final Climb myClimb;
		private final Intake myIntake;
		private final Loggable myLog;
		
		private final Notifier driveNotifier;
		private final Notifier shootNotifier;
		private final Notifier climbNotifier;
		private final Notifier intakeNotifier;
		private final Notifier logNotifier;
		
		public SubsystemHandler(){
			
			myDrive = new Drive(); // Instantiates runnable subsystem classes
			myShoot = new Shoot();
			myClimb = new Climb();
			myIntake = new Intake();
			myLog = new Loggable();
			
			driveNotifier = new Notifier (myDrive); // Instantiates the notifiers for each subsystem
					
			shootNotifier = new Notifier (myShoot);
						
			climbNotifier = new Notifier (myClimb);
				
			intakeNotifier = new Notifier (myIntake);
			
			logNotifier = new Notifier (myLog);
			
		}
		
		public void startSubsystems(){
			//System.out.println("Starting Subsystems");
			
			driveNotifier.startPeriodic(.005); // Gives the update values in seconds for each subsystem notifier
			
			shootNotifier.startPeriodic(.01);
			
			climbNotifier.startPeriodic(.02);
			
			intakeNotifier.startPeriodic(.02);
			
			logNotifier.startPeriodic(.005);
			
		}
}
