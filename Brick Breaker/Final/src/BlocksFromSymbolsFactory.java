

import java.util.Map;
import java.util.TreeMap;

/**
 * This class converts a symbol to a block.
 *
 * @author - Uriel Schwell.
 * @version - 13.6.2018.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
    private Map<String, BlocksCreator> blocksCreators = new TreeMap<String, BlocksCreator>();

    /**
     * BlocksFromSymbolsFactory -Constructor.
     *
     * @param spaceDef   - map of the spacer definitions.
     * @param blockDef   - map of the block definitions.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spaceDef, Map<String, BlocksCreator> blockDef) {
        this.spacerWidths = spaceDef;
        this.blocksCreators = blockDef;
    }

    /**
     * The function returns if 's' is a valid space symbol.
     *
     * @param s - the symbol to check.
     * @return - true if the symbol is valid, else return false.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * The function returns if 's' is a valid block symbol.
     *
     * @param s - the symbol to check.
     * @return - true if the block symbol is valid, else return false.
     */
    public boolean isBlockSymbol(String s) {
        return this.blocksCreators.containsKey(s);
    }

    /**
     * The function gets specifications of a block and builds a block by them.
     *
     * @param s    - the type of the block.
     * @param xpos - starting point of the X-axis.
     * @param ypos - starting point of the X-axis.
     * @return - a block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blocksCreators.get(s).create(xpos, ypos);
    }

    /**
     * The function returns the width of the space in the level.
     *
     * @param s - the spacer symbol.
     * @return - the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        if (isSpaceSymbol(s)) {
            return this.spacerWidths.get(s);
        } else {
            return -1;
        }
    }
}