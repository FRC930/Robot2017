package org.usfirst.frc.team930.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *ButtonRecorder: Saves temporal recordings to RoboRIO memory for long-lasting storage
 */
public class Record extends Command {
//TODO: start sensor recording
    public Record() {
    	requires(Robot.autonomousSaver);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    void save(String value, int port, int step, BufferedWriter writer, String type) throws IOException
    {
    	if(value != "null" && port >= 0 && port <= 5) 
		{
    		writer.write(type + " Port " + port + ": " + value);
			writer.newLine();
			System.out.println("Wrote status of " + type + " Port " + port + "(" + value + ") successfully");
		}
    	else if (port >= 0 && port <= 5) 
    		System.out.println(type + " Port " + port + " returned null at step " + step);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	try 
    	{
    		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SS";
    		String fileName = "C" + File.separator + new SimpleDateFormat(pattern).format(new Date()) + " ButtonRecorder.txt";
    		File file = new File(fileName);
    		file.getParentFile().mkdirs();
    	    file.createNewFile();
    	    if(file.exists()) System.out.println("File existance verified");
    	    else System.out.println("File does not exist");
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter(file));
			
			for(int i = 0; i < Robot.step; i++)
	    	{
				outputWriter.write("Actions for Step " + (i+1));
				outputWriter.newLine();
				System.out.println("Actions for Step " + (i+1));
				
				save(Robot.a0.get(i), Robot.a0port, i+1, outputWriter, "Axis");
				save(Robot.a1.get(i), Robot.a1port, i+1, outputWriter, "Axis");
				save(Robot.a2.get(i), Robot.a2port, i+1, outputWriter, "Axis");
				save(Robot.a3.get(i), Robot.a3port, i+1, outputWriter, "Axis");
				save(Robot.a4.get(i), Robot.a4port, i+1, outputWriter, "Axis");
				save(Robot.a5.get(i), Robot.a5port, i+1, outputWriter, "Axis");
				
				save(Robot.b0.get(i), Robot.b0port, i+1, outputWriter, "Button");
				save(Robot.b1.get(i), Robot.b1port, i+1, outputWriter, "Button");
				save(Robot.b2.get(i), Robot.b2port, i+1, outputWriter, "Button");
				save(Robot.b3.get(i), Robot.b3port, i+1, outputWriter, "Button");
				save(Robot.b4.get(i), Robot.b4port, i+1, outputWriter, "Button");
				save(Robot.b5.get(i), Robot.b5port, i+1, outputWriter, "Button");
				save(Robot.b6.get(i), Robot.b6port, i+1, outputWriter, "Button");
				save(Robot.b7.get(i), Robot.b7port, i+1, outputWriter, "Button");
				save(Robot.b8.get(i), Robot.b8port, i+1, outputWriter, "Button");
				save(Robot.b9.get(i), Robot.b9port, i+1, outputWriter, "Button");
				save(Robot.b10.get(i), Robot.b10port, i+1, outputWriter, "Button");
				save(Robot.b11.get(i), Robot.b11port, i+1, outputWriter, "Button");
				save(Robot.b12.get(i), Robot.b12port, i+1, outputWriter, "Button");
				save(Robot.b13.get(i), Robot.b13port, i+1, outputWriter, "Button");
				save(Robot.b14.get(i), Robot.b14port, i+1, outputWriter, "Button");
				/*
				save(Robot.ai0.get(i), Robot.ai0port, i+1, outputWriter, "Analog");
				save(Robot.ai1.get(i), Robot.ai1port, i+1, outputWriter, "Analog");
				save(Robot.ai2.get(i), Robot.ai2port, i+1, outputWriter, "Analog");
				save(Robot.ai3.get(i), Robot.ai3port, i+1, outputWriter, "Analog");
				//*/
				outputWriter.newLine();
				System.out.println("");
	    	}
			
			outputWriter.flush();
			outputWriter.close();
			System.out.println("");
			System.out.println("Look on roborio for " + fileName);
		}  
    	catch (FileNotFoundException ex) 
    	{
            ex.printStackTrace();
        }
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
