//import biuoop.DrawSurface;
//
//import java.awt.Color;
///**
// * BackGroundWideEasy - this class will define the background
// * for "Wide Easy".
// *
// * @author Uriel Schwell
// * @version 22.04.2018
// */
//public class BackGroundWideEasy implements Sprite {
//    @Override
//    public void drawOn(DrawSurface d) {
//        d.setColor(Color.decode("#7EBBCA"));
//        d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
//
//        d.setColor(Color.decode("#efe7b0"));
//        d.fillCircle(150, 150, 60);
//
//        int numRays = 100;
//        int startX = 25;
//        int endX = 775;
//
//        for (int i = 1; i <= numRays; i++) {
//            d.drawLine(150, 150, (endX - startX) / numRays * i, 250);
//        }
//
//        d.setColor(Color.decode("#ecd749"));
//        d.fillCircle(150, 150, 50);
//
//        d.setColor(Color.decode("#ffe118"));
//
//    }
//
//    @Override
//    public void timePassed(double dt) {
//
//    }
//}
