package org.usfirst.frc.team930.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Spark;

public class SparkSpeedLoggable implements Loggable{
	private final Spark spark;
	public SparkSpeedLoggable (Spark spark) {
		this.spark = spark;
	}
	
	@Override
	public String log() {
		return Double.toString(spark.getSpeed());
	}

}
