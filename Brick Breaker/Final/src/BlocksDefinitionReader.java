
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The class reads blocks specifications from a file and creates
 * the blocks for the level.
 *
 * * @author Uriel Schwell
 * @version 13.06.2018
 */
public class BlocksDefinitionReader {
    /**
     * The function reads specification for the blocks from a file.
     *
     * @param reader - the file with the specifications.
     * @return - the blocks to create.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacer = new HashMap<>();
        Map<String, String> defaultBlockCreator = new HashMap<>();
        Map<String, BlocksCreator> blockFinalMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                Map<String, String> tempBlocks = new HashMap<>();
                String blockSymbol = null;
                String spaceSymbol = null;
                if (line.startsWith("default")) {
                    defaultBlockCreator = fillMap(line);
                } else if (line.contains("bdef")) {
                    tempBlocks = fillMap(line);
                } else if (line.contains("sdef")) {
                    String[] defSpace = line.split(" ");
                    String symbol = defSpace[1].substring(defSpace[1].indexOf(":") + 1);
                    int factor = Integer.parseInt(defSpace[2].substring(defSpace[2].indexOf(":") + 1));
                    spacer.put(symbol, factor);
                }
                if (!tempBlocks.isEmpty()) {
                    blockSymbol = String.valueOf(tempBlocks.get("symbol"));
                    blockFinalMap.put(blockSymbol, new BlocksCreator(defaultBlockCreator, tempBlocks));
                }
                spaceSymbol = String.valueOf(spacer.get("symbol"));
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
            }
        } catch (IOException e) {
            // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed reading object");
            }
        }
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory(spacer, blockFinalMap);
        return blocksFromSymbolsFactory;
    }

    /**
     * The function gets a string of specifications for a specific block type
     * and returns the specifications.
     *
     * @param line - the string.
     * @return - the specifications.
     */
    public static Map<String, String> fillMap(String line) {
        Map<String, String> tempMap = new HashMap<>();
        String[] defBlock = line.split(" ");
        String leftParam = null;
        String rightParam = null;
        for (String parameter : defBlock) {
            if (parameter.equals("default") || parameter.equals("bdef")) {
                continue;
            }
            leftParam = parameter.substring(0, parameter.indexOf(":"));
            rightParam = parameter.substring(parameter.indexOf(":") + 1);
            tempMap.put(leftParam, rightParam);
        }
        return tempMap;
    }
}