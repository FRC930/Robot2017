package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon.TalonControlMode;

public class MotionProfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Motion profiling
				/*boolean [] btns= new boolean [btnsLast.length];
				for(int i=1; i<btnsLast.length; ++i)
					btns[i] = stick.getRawButton(i);

				// get the left joystick axis on Logitech Gampead
				double leftYjoystick = -1 * stick.getY(); // multiple by -1 so joystick forward is positive

				// call this periodically, and catch the output.  Only apply it if user wants to run MP.
				// example.control();
				
				if (btns[5] == false) {
					
					// button5 is off so straight drive
					L1.changeControlMode(TalonControlMode.Voltage);
					L1.set(12.0 * leftYjoystick);
					R1.changeControlMode(TalonControlMode.Voltage);
					R1.set(12.0 * leftYjoystick);

					// example.reset();
				} 
				else {
					
					// Button5 is held down so switch to motion profile control mode
					L1.changeControlMode(TalonControlMode.MotionProfile);
					R1.changeControlMode(TalonControlMode.MotionProfile);
					
					// CANTalon.SetValueMotionProfile setOutput = example.getSetValue();
							
					// L1.set(setOutput.value);
					// R1.set(setOutput.value);

					// if btn is pressed and was not pressed last time
					if( (btns[6] == true) && (btnsLast[6] == false) ) {
						// user just tapped button 6
						// example.startMotionProfile();
					}
				}

				// save buttons states for on-press detection
				for(int i=1;i<10;++i) {
					
					btnsLast[i] = btns[i];
				}*/
	}

}
