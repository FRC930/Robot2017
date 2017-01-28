package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.Spark;

public class SparkSpeedLoggable implements Loggable{
	private final Spark spark;
	public SparkSpeedLoggable (Spark spark) {
		this.spark = spark;
	}
	
	@Override
	public String log(int channel) {
		return Double.toString(spark.getSpeed());
	}

}
