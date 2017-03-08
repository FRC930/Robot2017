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
		    new Waypoint(5.0, -6.3, Pathfinder.d2r(-89))                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
		};

		// Create the Trajectory Configuration
		//
		// Arguments:
		// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
		// Sample Count:        SAMPLES_HIGH (100 000)
//		                      SAMPLES_LOW  (10 000)
//		                      SAMPLES_FAST (1 000)
		// Time Step:           0.05 Seconds
		// Max Velocity:        1.7 m/s
		// Max Acceleration:    2.0 m/s/s
		// Max Jerk:            60.0 m/s/s/s
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
