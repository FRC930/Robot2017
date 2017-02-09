package org.usfirst.frc.team930.robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot implements Runnable {

	double targetX;
	double targetY;
	double speed = 0.50;
	double rpmActual = 0;
	//double fGain = 0.0;
	double kF = 0.0;
	
	boolean Apressed = false; 
	boolean Bpressed = false;
	boolean Ypressed = false;
	
	boolean onBool = false;
	
	public void run(){
		if (DSManager.getDriveRawButtonFour() && (!Ypressed)) { // Y
			speed += 0.025;                      
			Ypressed = true;
		} else if((!DSManager.getDriveRawButtonFour()) && Ypressed) {
			Ypressed = false;
		}

		if(DSManager.getDriveRawButtonOne() && (!Apressed)) { // A
		    speed -= 0.025;  
		    Apressed = true;
		} else if((!DSManager.getDriveRawButtonOne()) && Apressed) {
		    Apressed = false;
		}

		if(DSManager.getDriveRawButtonOne() && (!Bpressed)) { // B
		    onBool = !onBool;
		    Bpressed = true; 
		} else if((!DSManager.getDriveRawButtonTwo()) && Bpressed) {
			Bpressed = false;
		}
		
		if (onBool){
			// Bang Bang
			//rpmActual = myTal.getEncVelocity() / 4096.0 * 60.0 * 10.0;
			/*if (rpmActual < (speed * 4550)) {
				myTal.set(1.0);
			}
			else {
				myTal.set(0.0);
			}*/
			OutputManager.setShooterSpeedMode();
			OutputManager.setShooterSpeed(speed*4500);
		}
		else{
			OutputManager.setShooterDisabledMode();
			OutputManager.setShooterSpeed(0.0);
		}
		
		//System.out.println("TS: " +  (speed * 4500) + "   S: " + (OutputManager.getTalonShooterMotor())); // ln
		//System.out.println("     Volt: " + myTal.getOutputVoltage() + "    E: " + myTal.getClosedLoopError());
		SmartDashboard.putNumber("Speed of Shooter", OutputManager.getTalonShooterMotor());
		//if (onBool) {
			//myTal.set((speed * 4550 * 4096.0 / 60.0 / 10.0));
			
		//}
		
		//System.out.println("Shoot " + Timer.getFPGATimestamp());
		
		//Setting Lights for Shooting
		if(OutputManager.getPDPChannelCurrent(Constants.PDP_CHANNEL7)>5){
		
			OutputManager.setLights(OutputManager.LightPatterns.LIGHTS_SHOOT);
		
		}
			
	}

	
}


