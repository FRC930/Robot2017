package org.usfirst.frc.team930.robot;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;

public class SensorManager {

	public static AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
}
