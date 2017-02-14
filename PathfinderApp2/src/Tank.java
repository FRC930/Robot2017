import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Tank {

    public static void main(String[] args) {
	Pathfinder finder = new Pathfinder();
    	
    	File fileRight = new File("C:/Users/Scott/Desktop/PathfinderFiles/rightData.csv");
    	File fileLeft = new File("C:/Users/Scott/Desktop/PathfinderFiles/leftData.csv");
    	
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, 3.15, 6.3, 630);
        Waypoint[] points = new Waypoint[] {
                new Waypoint(0, 0, 0),
                new Waypoint(9.75, 0, 0),
                new Waypoint(14.75, -4.2, finder.d2r(30))
        };

        Trajectory trajectory = Pathfinder.generate(points, config);
        

        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(0.813);

        // Do something with the new Trajectories...
        Trajectory left = modifier.getLeftTrajectory();
        Trajectory right = modifier.getRightTrajectory(); 
        
        finder.writeToCSV(fileLeft, left);
        finder.writeToCSV(fileRight, right);
    }

}
