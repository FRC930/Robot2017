package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.Record;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	private static OI instance = new OI();
	
    Joystick stick = new Joystick(0);
    public JoystickButton buttonA = new JoystickButton(stick, 1);
    JoystickButton buttonB = new JoystickButton(stick, 2);
    AnalogInput analogInput0;
    AnalogInput analogInput1;
    AnalogInput analogInput2;
    AnalogInput analogInput3;
    
    private OI()
    {
    	buttonB.whenPressed(new Record());
    }
    
    public static OI getInstance() 
    {
		if (instance == null)
			instance = new OI();
		return instance;
	}
    
    public double getLXAxis() 
    {
		return stick.getRawAxis(0);
	}

    public double getLYAxis() 
    {
		return -stick.getRawAxis(1);
	}
    
    public double getRXAxis() 
    {
		return stick.getRawAxis(3);
	}
    
    public double getRYAxis()
    {
		return -stick.getRawAxis(4);
	}
}

