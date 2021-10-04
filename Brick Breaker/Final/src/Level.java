
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface holds all the needed information for a level.
 *
 * @author - Uriel Schwell.
 * @version - 13.6.2018.
 */
public class Level implements LevelInformation {
    private String levelName;
    private List<Velocity> velocities;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numberOfBlocksToRemove;
    private Reader file;
    private List<Block> blocks;

    /**
     * Constructor.
     */
    public Level() {
        this.levelName = null;
        this.velocities = null;
        this.background = null;
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.blocksStartX = 0;
        this.blocksStartY = 0;
        this.rowHeight = 0;
        this.numberOfBlocksToRemove = 0;
        this.file = null;
        this.blocks = new ArrayList<>();
    }

    /**
     * sets the file.
     *
     * @param file1 - the block definition file.
     */
    public void setFile(Reader file1) {
        this.file = file1;
    }

    /**
     * reads the file.
     *
     * @return -the file.
     */
    public Reader getFile() {
        return file;
    }

    /**
     * Setter.
     *
     * @param rowHeight1 - the height of each row in the level.
     */
    public void setRowHeight(int rowHeight1) {
        this.rowHeight = rowHeight1;
    }

    @Override
    public void setlevelName(String s) {
        this.levelName = s;
    }

    @Override
    public void setpaddleSpeed(int i) {
        this.paddleSpeed = i;
    }

    /**
     * Setter.
     *
     * @param blocksStartX1 - value of the X-axis start point of the blocks.
     */
    public void setBlocksStartX(int blocksStartX1) {
        this.blocksStartX = blocksStartX1;
    }

    /**
     * Setter.
     *
     * @param blocksStartY1 - value of the Y-axis start point of the blocks.
     */
    public void setBlocksStartY(int blocksStartY1) {
        this.blocksStartY = blocksStartY1;
    }

    /**
     * setter.
     *
     * @param velocities1 - list of velocities to the balls in the level.
     */
    public void setVelocities(List<Velocity> velocities1) {
        this.velocities = velocities1;
    }

    /**
     * setPaddleSpeed -set the Paddle Speed.
     *
     * @param paddleSpeed1 - the speed of the paddle.
     */
    public void setPaddleSpeed(int paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**
     * setter.
     *
     * @param paddleWidth1 - the width of the paddle in the level.
     */
    public void setPaddleWidth(int paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    /**
     * setter.
     *
     * @param levelName1 - the name of the level.
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }

    /**
     * setter.
     *
     * @param background1 - the background of the level.
     */
    @Override
    public void setBackground(Sprite background1) {
        this.background = background1;
    }

    /**
     * setter.
     *
     * @param blocks1 - list of the blocks of the level.
     */
    public void setBlocks(List<Block> blocks1) {
        this.blocks = blocks;
    }

    /**
     * setter.
     *
     * @param numberOfBlocksToRemove1 - number of blocks to remove for passing the level.
     */
    public void setNumberOfBlocksToRemove(int numberOfBlocksToRemove1) {
        this.numberOfBlocksToRemove = numberOfBlocksToRemove1;
    }

    /**
     * Getter.
     *
     * @return - the height of each row of blocks in the level.
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * getter.
     *
     * @return - X-axis start point of the blocks.
     */
    public int getBlocksStartX() {
        return blocksStartX;
    }

    /**
     * getter.
     *
     * @return - Y-axis start point of the blocks.
     */
    public int getBlocksStartY() {
        return blocksStartY;
    }

    /**
     * The function returns the number of balls in the level.
     *
     * @return - number of level.
     */
    public int getNumberOfBalls() {
        return this.velocities.size();
    }

    /**
     * The initial velocity of each ball.
     *
     * @return - list of velocities.
     */
    public List<Velocity> getVelocities() {
        return this.velocities;
    }

    /**
     * The function holds the speed of the paddle.
     *
     * @return - speed of the paddle.
     */
    public int getPaddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function holds the width of the paddle.
     *
     * @return - width of the paddle.
     */
    public int getPaddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The function holds the name of the level.
     *
     * @return - name of the level.
     */
    public String getLevelName() {
        return this.levelName;
    }

    /**
     * The function returns the number of balls in the level.
     *
     * @return - number of level.
     */
    @Override
    public int numberOfBalls() {
        return velocities.size();
    }

    /**
     * The initial velocity of each ball.
     *
     * @return - list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * The function holds the speed of the paddle.
     *
     * @return - speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function holds the width of the paddle.
     *
     * @return - width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The function holds the name of the level.
     *
     * @return - name of the level.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }


    /**
     * The function holds the sprite of the background of the level.
     *
     * @return - sprite - the background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - the blocks of the game.
     */
    @Override
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * number of blocks to remove for ending the level.
     *
     * @return - number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - the blocks of the game.
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }
}