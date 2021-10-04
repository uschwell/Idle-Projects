/**
 * The interface holds the creation of blocks.
 *
 *  * @author Uriel Schwell
 * @version 13.6.2018
 */
public interface BlockCreator {
    /**
     * The function gets the x and y values of the upper left point of the block
     * and creates a block by the specifications from the file.
     *
     * @param x - value of the upper left point of the block.
     * @param y - value of the upper left point of the block.
     * @return - an object of a block.
     */
    Block create(int x, int y);
}