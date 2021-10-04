

//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.awt.Color;
/**
 * WideEasy - this class will define the Level called
 * "Final Four".
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */
//public class FinalFour implements LevelInformation {
    /**
     * (empty) constructor.
     */
//    public FinalFour() {

//    }
/*
    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }

    @Override
    public void setBlocksStartX(int blocksStartX) {

    }

    @Override
    public void setBlocksStartY(int blocksStartY) {

    }

    @Override
    public int getBlocksStartX() {
        return 0;
    }

    @Override
    public int getBlocksStartY() {
        return 0;
    }

    @Override
    public List<Block> getBlocks() {
        return null;
    }

    @Override
    public void setRowHeight(int rowHeight) {

    }

    @Override
    public String levelName() {
        return null;
    }
/*
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList();

        velocities.add(Velocity.fromAngleAndSpeed(40, 5));
        velocities.add(Velocity.fromAngleAndSpeed(90, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-40, 5));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return (new BackGroundFinalFour());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        int[] hitPoints = {2, 1, 1, 1, 1, 1, 1};
        int y = 100;
        for (int i = 0; i < colors.length; i++) {
            for (int k = 0; k < 15; k++) {
                blockList.add(new Block(new Rectangle(new Point(k * 50 + 25, y), 50, 50), colors[i], hitPoints[i]));
            }
            y += 50;
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
    */
//}
