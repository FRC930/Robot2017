import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Main {

	public static void main(String[] args) {
	      final String path_name = "DriveToHopper";    
	      String directory = "../../../Paths";


		// 3 Waypoints
		Waypoint[] points = new Waypoint[] {
		    new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
		    //new Waypoint(-3.775, 4.7, Pathfinder.d2r(-10)),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
		    new Waypoint(3.8, 0, Pathfinder.d2r(0))
		};
		
		/* Red Alliance */			/* Blue Alliance */
		
		/* Hopper & Shoot */
		/* Slower */
		// 5.7  -6.1  -89			//  5.7   6.1   89
		// 4.3   2.5   65			//  4.3  -2.5  -65
		// 8.0    0    0			//  8.0    0    0
		/* Faster */
		// 4.5  -6.1  -89			// -4.5   6.1   89
		// 3.8   2.5   89			//  3.8  -2.5  -89
		// 8.0    0    0			//  8.0    0    0
		
		/* Right Gear */
		/* Slower */
		// 8.5   2.0   74			//  8.5  -2.0  -74
		
		/* Left Gear */
		/* Slower */
		// 8.2  -2.2  -66			//  8.2   1.9   64
		
		/* Middle Gear */
		/* Slower */
		// 6.0    0    0 
		
		/* Gear Shoot */
		/* Slower */
		// 8.5   2.0   74
		// 1.5    0    0		// BACKUP 1.5 ft
		// 3.8    0    0		// PIVOT to 150 degrees
		// 8.0    0    0		// DRIVE TO BOILER
		
		// Create the Trajectory Configuration
		//
		// Arguments:
		// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
		// Sample Count:        SAMPLES_HIGH (100 000)
//		                      SAMPLES_LOW  (10 000)
//		                      SAMPLES_FAST (1 000)
		// Time Step:           0.01 Seconds
		
		/* Slower */
		// Max Velocity:        4.0 m/s
		// Max Acceleration:    4.0 m/s/s
		// Max Jerk:            50.0 m/s/s/s
		
		/* Faster */
		// Max Velocity:		8.0 m/s
		// Max Acceleration:	8.0 m/s/s
		// Max Jerk:			50.0 m/s/s/s
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, 4.0, 4.0, 50.0);

		// Generate the trajectory
		Trajectory trajectory = Pathfinder.generate(points, config);
		
		
		TextFileSerializer js = new TextFileSerializer();
	      String serialized = js.serializeLeft("left", trajectory);
	      //System.out.print(serialized);
	      String fullpath = joinPath(directory, path_name + "_Left.csv");
	      if (!writeFile(fullpath, serialized)) {
	        System.err.println(fullpath + " could not be written!!!!1");
	        System.exit(1);
	      } else {
	        System.out.println("Wrote " + fullpath);
	      }
	      
	   // Outputs to the directory supplied as the first argument.
	      js = new TextFileSerializer();
	      serialized = js.serializeRight("right", trajectory);
	      //System.out.print(serialized);
	      fullpath = joinPath(directory, path_name + "_Right.csv");
	      if (!writeFile(fullpath, serialized)) {
	        System.err.println(fullpath + " could not be written!!!!1");
	        System.exit(1);
	      } else {
	        System.out.println("Wrote " + fullpath);
	      }
	}

	  private static boolean writeFile(String path, String data) {
		    try {
		      File file = new File(path);

		      // if file doesnt exists, then create it
		      if (!file.exists()) {
		          file.createNewFile();
		      }

		      FileWriter fw = new FileWriter(file.getAbsoluteFile());
		      BufferedWriter bw = new BufferedWriter(fw);
		      bw.write(data);
		      bw.close();
		    } catch (IOException e) {
		      return false;
		    }
		    
		    return true;
		  }
	  
	  public static String joinPath(String path1, String path2)
	  {
	      File file1 = new File(path1);
	      File file2 = new File(file1, path2);
	      return file2.getPath();
	  }
}
