package org.usfirst.frc.team930.robot;

//Commented out non-used imports
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

//import com.ctre.CANTalon.TalonControlMode;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//--------------------MAIN PROGRAM----------------------\\

public class Robot extends SampleRobot {
	
	//-------------Variables--------------\\
	
	RobotDrive myRobot = new RobotDrive(5, 6);
	
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	Spark motor1 = new Spark(0);
		
	
	
	BufferedWriter bwriter;
	
	File file;
	
	//-------------Constructors---------------\\
	public Robot() {
		myRobot.setExpiration(0.1);
	}
	
	//IDK what this is for, but just in case if needed it's here
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
		    	
		       
		    		  
		 
		  }
	}
	
	//-------------Autonomous------------\\
	@Override
	public void autonomous() {   

	}

	//-------------Tele-Op Mode(Manual)-------------\\
	@Override
	 public void operatorControl() {  
		  myRobot.setSafetyEnabled(true);
		  
		  //While loop runs data you want to output into writer
		  while (isOperatorControl() && isEnabled()) {

			  try {
				 bwriter.write("dank memes");
				 bwriter.flush();
				 bwriter.close();
				
			  } catch (IOException e) {
				  //e.printStackTrace();
				  System.err.println(e);
			  } 
			  
	    			
			  Timer.delay(0.005);
		  }	 //End of while loop here
	 } //End of operatorControl()
} //End of public class Robot extends SampleRobot 




//------------------------Weird stuff------------------\\
/*
	        
	    } catch (IOException e) {
	        System.err.println(e);
	    } finally {
	    	
	        if (writer != null) {

	            try {
	                writer.close();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	        }
	    //}
	}
}
*/
