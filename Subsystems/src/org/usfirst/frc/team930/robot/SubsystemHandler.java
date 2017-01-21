package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Notifier;

public class SubsystemHandler {

		private final Drive myDrive;
		private final Shoot myShoot;
		private final Climb myClimb;
		private final Intake myIntake;
		
		
		private final Notifier driveNotifier;
		private final Notifier shootNotifier;
		private final Notifier climbNotifier;
		private final Notifier intakeNotifier;
	
		
		public SubsystemHandler(){
			
			myDrive = new Drive();
			myShoot = new Shoot();
			myClimb = new Climb();
			myIntake = new Intake();
			
			driveNotifier = new Notifier (myDrive);
					
			shootNotifier = new Notifier (myShoot);
						
			climbNotifier = new Notifier (myClimb);
				
			intakeNotifier = new Notifier (myIntake);
			
		}
		
		public void startSubsystems(){
			System.out.println("Starting Subsystems");
			
			driveNotifier.startPeriodic(.005);
			
			shootNotifier.startPeriodic(.01);
			
			climbNotifier.startPeriodic(.02);
			
			intakeNotifier.startPeriodic(.02);
			
		}
}
