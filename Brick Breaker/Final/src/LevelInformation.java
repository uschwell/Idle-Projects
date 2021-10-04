import java.io.Reader;
import java.util.List;

/**
 * LevelInformation - this interface will define all the requirements for
 * a Game Level.
 *
 * @author Uriel Schwell
 * @version 13.06.2018
 */

public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     *
     * @return name of the level.
     */
    String levelName();

    /**
     *
     * @return background (as Sprite).
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level.
     * @return -The Blocks that make up this level
     */
    List<Block> blocks();


    /**
     *Number of blocks that should be removed to clear the level.
     * @return Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();

    /**
     * setBlocksStartX -X value of the blocks starting Point.
     *
     * @param blocksStartX - value of the Y-axis start point of the blocks.
     */
    void setBlocksStartX(int blocksStartX);

    /**
     * setBlocksStartY -Y value of the blocks starting Point.
     *
     * @param blocksStartY - value of the Y-axis start point of the blocks.
     */
    void setBlocksStartY(int blocksStartY);


    /**
     * getBlocksStartX -get the X startingPoint Value.
     *
     * @return - x starting point.
     */
    int getBlocksStartX();

    /**
     * getBlocksStartY -get the Y startingPoint Value.
     *
     * @return - y starting point.
     */
    int getBlocksStartY();

    /**
     * getBlocks -The list of Blocks that make up this level (each block contains
     * its size, color and location).
     *
     * @return - the blocks of the game.
     */
    List<Block> getBlocks();


    /**
     * setRowHeight -set the height of each Row.
     *
     * @param rowHeight - the height of each row.
     */
    void setRowHeight(int rowHeight);

    /**
     * sets the Level name.
     * @param s -string/
     */
    void setlevelName(String s);

    /**
     * Set the speed of the Paddle.
     * @param i -spped.
     */
    void setpaddleSpeed(int i);

    /**
     * Set the width of the Paddle.
     * @param i -width
     */
    void setPaddleWidth(int i);

    /**
     * set the Number of Blocks we will need to remove.
     * @param i -number of Blocks.
     */
    void setNumberOfBlocksToRemove(int i);

    /**
     * set the various Ball velocities.
     * @param velocityList -list of velocities.
     */
    void setVelocities(List<Velocity> velocityList);

    /**
     * set the Background Sprite.
     * @param background1 -background to set.
     */
    void setBackground(Sprite background1);

    /**
     * sets the file.
     *
     * @param file1 - the block definition file.
     */
    void setFile(Reader file1);

    /**
     * gets the file.
     *
     * @return - the block definition file.
     */
    Reader getFile();


    /**
     * gets the height of each row of blocks.
     *
     * @return -the height of each row of blocks in the level.
     */
    int getRowHeight();
}
