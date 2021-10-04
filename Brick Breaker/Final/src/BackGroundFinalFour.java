//import biuoop.DrawSurface;
//
//import java.awt.Color;
//
///**
// * BackGroundFinalFour - this class will define the background
// * for "Final Four".
// *
// * @author Uriel Schwell
// * @version 22.04.2018
// */
//public class BackGroundFinalFour implements Sprite {
//    @Override
//
//    public void drawOn(DrawSurface d) {
//        // draw the rectangles.
//        d.setColor(Color.gray);
//        d.fillRectangle(0, 30, 800, 600);
//        // inner rectangle.
//        d.setColor(Color.cyan);
//        d.fillRectangle(20, 70, 750, 600);
//        // draw the building.
//        d.setColor(Color.pink);
//        d.fillRectangle(270, 420, 110, 190);
//        // first floor windows.
//        for (int j = 0; j < 12; j++) {
//            for (int i = 0; i < 5; i++) {
//                d.setColor(Color.white);
//                d.fillRectangle(280 + i * 20, 590 - j * 15, 10, 10);
//            }
//        }
//        // draw grass in front of the building.
//        // second cluster.
//        Line l;
//        for (int i = 0; i < 100; i++) {
//            l = new Line(new Point(390 + i * 10, 595), new Point(380 + i * 10, 600));
//            l.drawLine(d, Color.green.darker());
//        }
//        for (int i = 0; i < 100; i++) {
//            l = new Line(new Point(390 + i * 10, 595), new Point(395 + i * 10, 600));
//            l.drawLine(d, Color.green.darker());
//        }
//        // draw the bulb of the clouds.
//        // first cluster.
//        d.setColor(Color.lightGray);
//        d.fillCircle(140, 330, 40);
//        d.setColor(Color.lightGray);
//        d.fillCircle(170, 310, 35);
//        d.setColor(Color.gray.brighter());
//        d.fillCircle(200, 350, 30);
//        d.setColor(Color.gray.brighter());
//        d.fillCircle(220, 360, 40);
//        d.setColor(Color.gray.brighter());
//        d.fillCircle(210, 320, 25);
//        // second cluster.
//        d.setColor(Color.lightGray);
//        d.fillCircle(540, 430, 40);
//        d.setColor(Color.lightGray);
//        d.fillCircle(570, 410, 35);
//        d.setColor(Color.gray.brighter());
//        d.fillCircle(600, 450, 30);
//        d.setColor(Color.gray.brighter());
//        d.fillCircle(620, 460, 40);
//        d.setColor(Color.gray.brighter());
//        d.fillCircle(610, 420, 25);
//        // draw the rain.
//        // first cluster.
//        for (int i = 0; i < 10; i++) {
//            l = new Line(new Point(150 + i * 8, 330), new Point(110 + i * 10, 600));
//            l.drawLine(d, Color.gray);
//        }
//        // second cluster.
//        for (int i = 0; i < 10; i++) {
//            l = new Line(new Point(550 + i * 8, 430), new Point(510 + i * 10, 600));
//            l.drawLine(d, Color.gray);
//        }
//    }
//
//    @Override
//    public void timePassed(double dt) {
//
//    }
//}
