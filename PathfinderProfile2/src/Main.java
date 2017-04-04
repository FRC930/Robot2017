import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

// 260
// 1.9292 pixels per x inch
//1.8096 pixels per y inch


/**
 * Created by greg on 2/28/17.
 */
public class Main extends JComponent{
    private static final double scale = 31;
    private static double time = 0.0;

    private static class Line{
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    public Main() {
//        for(int i = 0; i < 100; i++) {
//            lines.add(new Line(i*10, 300, i*10, 400, Color.GREEN));
//            lines.add(new Line(0, i*10, 100, i*10, Color.GREEN));
//        }
    }

    private final LinkedList<Line> lines = new LinkedList<Line>();
    private final LinkedList<Point> points = new LinkedList<Point>();

    public void addLine(int x1, int x2, int x3, int x4) {
        addLine(x1, x2, x3, x4, Color.black);
    }

    public void addLine(int x1, int x2, int x3, int x4, Color color) {
        lines.add(new Line(x1,x2,x3,x4, color));
        repaint();
    }

    public void addDot(int x, int y) {
        points.add(new Point(x,y));
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D)graphics;
        try {
            BufferedImage img = ImageIO.read(new File("./field.png"));
            g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.setStroke(new BasicStroke(2));

        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
        for (Point point : points) {
            g.setColor(Color.BLACK);
            g.drawOval(point.x-3, point.y-3,6,6);
        }
    }

    public static void main(String[] args) {

        /******* TEST WAYPOINT FOR SCALING ********/
//        Waypoint[] firstPath = new Waypoint[]{
//                new Waypoint(0, 0, 0),
//                new Waypoint(7.75,0, Pathfinder.d2r(0))  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//        };

        /******* 40KPA CLOSE AUTO **********/
//        Waypoint[] firstPath = new Waypoint[]{
//                new Waypoint(0, -3.5, 0),
//                new Waypoint(4.775, -10.75, Pathfinder.d2r(-90))  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//        };
//        Waypoint[] secondPath = new Waypoint[]{
//            new Waypoint(4.775, -10.75, Pathfinder.d2r(90)),
//            new Waypoint(4.775, -10.0, Pathfinder.d2r(90)),  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//            new Waypoint(5.5, -8.5, Pathfinder.d2r(0))
//        };
//        Waypoint[] thirdPath = new Waypoint[]{
//                new Waypoint(5.5, -8.5, Pathfinder.d2r(0)),  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//                new Waypoint(0.75, -8.5, Pathfinder.d2r(45)),
//                new Waypoint(-.25, -9.5, Pathfinder.d2r(45))
//        };


        /******* 40KPA FAR AUTO **********/
//        Waypoint[] firstPath = new Waypoint[]{
//                new Waypoint(0, -3.5, 0),
//                new Waypoint(8, -10.75, Pathfinder.d2r(-90))  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//        };
//        Waypoint[] secondPath = new Waypoint[]{
//            new Waypoint(8, -10.75, Pathfinder.d2r(90)),
//            new Waypoint(8, -9.0, Pathfinder.d2r(90)),  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//            new Waypoint(9, -6.75, Pathfinder.d2r(0))
//        };
//        Waypoint[] thirdPath = new Waypoint[]{
//                new Waypoint(9, -6.75, Pathfinder.d2r(0)),  // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
//                new Waypoint(0.75, -8.5, Pathfinder.d2r(45)),
//                new Waypoint(-.25, -9.5, Pathfinder.d2r(45))
//        };

        /******* GEAR + 10KPA AUTO **********/
        Waypoint[] firstPath = new Waypoint[]{
                new Waypoint(0, 0, 0),
                new Waypoint(-3.775, 1.0, Pathfinder.d2r(-10)),
                new Waypoint(-6.9, -1.25, Pathfinder.d2r(60))
        };

        Waypoint[] secondPath = new Waypoint[]{
                new Waypoint(0, 0, Pathfinder.d2r(0)),  // Waypoint @ x=-4, y=-1, exit angle=-45 degreee
                new Waypoint(-7.25, -7.75, Pathfinder.d2r(45))
        };


        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, 8.0, 4.0, 60.0);

        Trajectory firstTrajectory = Pathfinder.generate(firstPath, config);
        Trajectory secondTrajectory = Pathfinder.generate(secondPath, config);
//        Trajectory thirdTrajectory = Pathfinder.generate(thirdPath, config);

        double wheelbase_width = 29.25/12;

        // Create the Modifier Object
        TankModifier firstTank = new TankModifier(firstTrajectory);
        TankModifier secondTank = new TankModifier(secondTrajectory);
//        TankModifier thirdTank = new TankModifier(thirdTrajectory);

        // Generate the Left and Right trajectories using the original trajectory
        // as the centre
        firstTank.modify(wheelbase_width);
        secondTank.modify(wheelbase_width);
//        thirdTank.modify(wheelbase_width);


        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final Main comp = new Main();
        comp.setPreferredSize(new Dimension(1000, 725));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);

        drawTrajectory(comp, firstTrajectory, Color.PINK);
        drawTrajectory(comp, firstTank.getLeftTrajectory(), Color.BLUE);
        drawTrajectory(comp, firstTank.getRightTrajectory(), Color.RED);
        drawTrajectory(comp, secondTrajectory, Color.PINK);
        drawTrajectory(comp, secondTank.getLeftTrajectory(), Color.RED);
        drawTrajectory(comp, secondTank.getRightTrajectory(), Color.BLUE);
//        drawTrajectory(comp, thirdTrajectory, Color.PINK);
//        drawTrajectory(comp, thirdTank.getLeftTrajectory(), Color.BLUE);
//        drawTrajectory(comp, thirdTank.getRightTrajectory(), Color.RED);
//        drawTrajectory(comp, secondTrajectory);
//        drawTrajectory(comp, thirdTrajectory);

        testFrame.pack();
        testFrame.setVisible(true);

        System.out.println((time/3.0)+2.0);
    }

    public static void drawTrajectory(Main comp, Trajectory trajectory, Color color) {
        for (int i = 1; i < trajectory.length(); i++) {
            Trajectory.Segment prevSeg = trajectory.get(i-1);
            Trajectory.Segment seg = trajectory.get(i);

            time += prevSeg.dt;

            int x1 = (int) (prevSeg.x * scale)+508;
            int x2 = (int) (seg.x * scale)+508;
            int y1 = (int) -(prevSeg.y * scale) + 360;
            int y2 = (int) -(seg.y * scale) + 360;

            if(i == 1) comp.addDot(x1, y1);


            comp.addLine(x1, y1, x2, y2, color);
        }
    }
}