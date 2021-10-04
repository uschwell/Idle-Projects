

/*
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
*/

import java.util.List;

/**
 * WideEasy - this class will define the Level called
 * "Wide Easy".
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */

/*
public class WideEasy implements LevelInformation {
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
    /**
     * constructor.
     */
    /*
    public WideEasy() {

    }
    */
/*
    @Override
    public int numberOfBalls() {
        return 10;
    }
*/
/*
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new LinkedList();
        double speed = 5;
        ballVelocities.add(Velocity.fromAngleAndSpeed(50, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(40, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(30, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(20, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(10, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(-10, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(-20, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(-30, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(-40, speed));
        ballVelocities.add(Velocity.fromAngleAndSpeed(-50, speed));

        return ballVelocities;
    }
*/
/*
    @Override
    public int paddleSpeed() {
        return 8;
    }
*/
/*
    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return (new BackGroundWideEasy());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList();

        int y = 250;
        int blockHeight = 25;

        blocks.add(new Block(new Rectangle(new Point(25, y), 50, blockHeight), Color.RED, 1));
        blocks.add(new Block(new Rectangle(new Point(75, y), 50, blockHeight), Color.RED, 1));
        blocks.add(new Block(new Rectangle(new Point(125, y), 50, blockHeight), Color.ORANGE, 1));
        blocks.add(new Block(new Rectangle(new Point(175, y), 50, blockHeight), Color.ORANGE, 1));
        blocks.add(new Block(new Rectangle(new Point(225, y), 50, blockHeight), Color.YELLOW, 1));
        blocks.add(new Block(new Rectangle(new Point(275, y), 50, blockHeight), Color.YELLOW, 1));
        blocks.add(new Block(new Rectangle(new Point(325, y), 50, blockHeight), Color.GREEN, 1));
        blocks.add(new Block(new Rectangle(new Point(375, y), 50, blockHeight), Color.GREEN, 1));
        blocks.add(new Block(new Rectangle(new Point(425, y), 50, blockHeight), Color.GREEN, 1));
        blocks.add(new Block(new Rectangle(new Point(475, y), 50, blockHeight), Color.BLUE, 1));
        blocks.add(new Block(new Rectangle(new Point(525, y), 50, blockHeight), Color.BLUE, 1));
        blocks.add(new Block(new Rectangle(new Point(575, y), 50, blockHeight), Color.PINK, 1));
        blocks.add(new Block(new Rectangle(new Point(625, y), 50, blockHeight), Color.PINK, 1));
        blocks.add(new Block(new Rectangle(new Point(675, y), 50, blockHeight), Color.CYAN, 1));
        blocks.add(new Block(new Rectangle(new Point(725, y), 50, blockHeight), Color.CYAN, 1));

        return blocks;
    }
*/
/*
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
    */

//}
