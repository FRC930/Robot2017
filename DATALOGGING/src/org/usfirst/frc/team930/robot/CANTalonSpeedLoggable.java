package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;



public class CANTalonSpeedLoggable implements Loggable{
	private final CANTalon talon;
	public CANTalonSpeedLoggable (CANTalon talon) {
		this.talon = talon;
	}
	
	@Override
	public String log(int channel) {
		return Double.toString(talon.getSpeed());
	}

}
