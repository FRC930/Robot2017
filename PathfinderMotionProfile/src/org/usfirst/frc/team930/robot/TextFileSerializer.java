package org.usfirst.frc.team930.robot;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.modifiers.TankModifier;

/**
 * Serializes a Path to a simple space and CR separated text file.
 * 
 * @author Jared341
 */
public class TextFileSerializer {
  public String serializeLeft(String name, Trajectory path) {
	// The distance between the left and right sides of the wheelbase is 0.6m
			double wheelbase_width = 0.6;

			// Create the Modifier Object
			TankModifier modifier = new TankModifier(path);

			// Generate the Left and Right trajectories using the original trajectory
			// as the centre
			modifier.modify(wheelbase_width);

			Trajectory left  = modifier.getLeftTrajectory();       // Get the Left Side
	  
	    String content = name + "\n";
	    content += left.length() + "\n";
	    content += serializeTrajectory(left);
	    return content;
	  }
  
  public String serializeRight(String name, Trajectory path) {
	    
	// The distance between the left and right sides of the wheelbase is 0.6m
			double wheelbase_width = 0.6;

			// Create the Modifier Object
			TankModifier modifier = new TankModifier(path);

			// Generate the Left and Right trajectories using the original trajectory
			// as the centre
			modifier.modify(wheelbase_width);

			Trajectory right  = modifier.getRightTrajectory();       // Get the Left Side
	  
	  	String content = name + "\n";
	    content += right.length() + "\n";
	    content += serializeTrajectory(right);
	    return content;
	  }
  
  public String serializeTrajectory(Trajectory trajectory) {
    String content = "";
    for (int i = 0; i < trajectory.length(); ++i) {
      Segment segment = trajectory.get(i);
      content += String.format(
              "%.5f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f\n", 
              segment.position, segment.velocity, segment.acceleration, segment.jerk,
              segment.heading, segment.dt, segment.x, segment.y);
    }
    return content;
  }
  
}
