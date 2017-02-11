import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Tank {

    public static void main(String[] args) {
	Pathfinder finder = new Pathfinder();
    	
    	File fileRight = new File("C:/Users/Devin/Desktop/FILES/rightData.csv");
    	File fileLeft = new File("C:/Users/Devin/Desktop/FILES/leftData.csv");
    	
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        Waypoint[] points = new Waypoint[] {
                new Waypoint(-4, -1, Pathfinder.d2r(-45)),
                new Waypoint(-2, -2, 0),
                new Waypoint(0, 0, 0),
                new Waypoint(2,7,-1),
                new Waypoint(8,2,1),
                new Waypoint(3,5,2)
        };

        Trajectory trajectory = Pathfinder.generate(points, config);

        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(0.5);

        // Do something with the new Trajectories...
        Trajectory left = modifier.getLeftTrajectory();
        Trajectory right = modifier.getRightTrajectory();
        
        finder.writeToCSV(fileLeft, left);
        finder.writeToCSV(fileRight, right);
    }

}
