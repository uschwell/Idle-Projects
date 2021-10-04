import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * The class reads level specifications from a file and creates
 * a level information object.
 *
 * @author Uriel Schwell
 * @version 13.06.2018
 */
public class LevelSpecificationReader {
    private File file;

    /**
     * fromReader -function translates the file to a list
     * of specifications for the level creation method.
     *
     * @param reader - the file.
     * @return - list of specifications.
     */
    public static List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInformation = new ArrayList<LevelInformation>();
        LevelInformation li = new Level();
        BufferedReader in = null;
        try {
            in = new BufferedReader(reader);
            String line = in.readLine();
            while (line != null) {
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("START_LEVEL")) {
                    line = in.readLine();
                    continue;
                }
                while (!line.equals("START_BLOCKS")) {
                    if (line.equals("END_LEVEL")) {
                        levelInformation.add(li);
                        line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        li = new Level();
                        continue;
                    }
                    String[] splitedLine = line.split(":");
                    try {
                        switch (splitedLine[0]) {
                            case "level_name":
                                li.setlevelName(splitedLine[1]);
                                break;
                            case "paddle_speed":
                                li.setpaddleSpeed(Integer.parseInt(splitedLine[1]));
                                break;
                            case "paddle_width":
                                li.setPaddleWidth(Integer.parseInt(splitedLine[1]));
                                break;
                            case "num_blocks":
                                li.setNumberOfBlocksToRemove(Integer.parseInt(splitedLine[1]));
                                break;
                            case "blocks_start_x":
                                li.setBlocksStartX(Integer.parseInt(splitedLine[1]));
                                break;
                            case "blocks_start_y":
                                li.setBlocksStartY(Integer.parseInt(splitedLine[1]));
                                break;
                            case "row_height":
                                li.setRowHeight(Integer.parseInt(splitedLine[1]));
                                break;
                            case "ball_velocities":
                                List<Velocity> velocityList = new ArrayList<Velocity>();
                                String[] velocities = splitedLine[1].split(" ");
                                for (String str : velocities) {
                                    String[] velocity = str.split(",", 2);
                                    int angle = Integer.parseInt(velocity[0]);
                                    int speed = Integer.parseInt(velocity[1]);
                                    velocityList.add(Velocity.fromAngleAndSpeed((double) angle, (double) speed));
                                }
                                li.setVelocities(velocityList);
                                break;
                            case "background":
                                ColorsParser colorsParser = new ColorsParser();
                                if (splitedLine[1].startsWith("color")) {
                                    try {
                                        Color color = colorsParser.colorFromString(splitedLine[1]);
                                        BackgroundColor backgroundColor = new BackgroundColor(color);
                                        li.setBackground(backgroundColor);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (splitedLine[1].startsWith("image")) {
                                    String lineImage = splitedLine[1].substring(6,
                                            splitedLine[1].length() - 1);
                                    try {
                                        InputStream is =
                                                ClassLoader.getSystemClassLoader().getResourceAsStream(lineImage);
                                        BufferedImage image = ImageIO.read(is);
                                        BackgroundImage backgroundImage = new BackgroundImage(image);
                                        li.setBackground(backgroundImage);
                                    } catch (IOException e) {
                                        System.err.println("Failed reading image");
                                        System.exit(0);
                                    }
                                }
                                break;
                            case "block_definitions":
                                //this.file = new File(splitedLine[1]);
                                InputStream inputStream = ClassLoader.getSystemClassLoader()
                                        .getResourceAsStream(splitedLine[1]);
                                Reader blocksFile = new InputStreamReader(inputStream);
                                BufferedReader background = new BufferedReader(blocksFile);
                                li.setFile(blocksFile);
//                                try {
//                                  //FileReader blockFileReder = new FileReader(blocksFile);
//                                } catch (Exception e) {
//                                    System.out.println("Can't open file");
//                                }
                                break;
                            default:
                                break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Unable to complete level creation");
                        e.printStackTrace();
                    }
                    line = in.readLine();
                    if (line == null) {
                        break;
                    }
                }

                if (line == null) {
                    break;
                }
                BlocksFromSymbolsFactory blocksFromSymbolsFactory =
                        BlocksDefinitionReader.fromReader(li.getFile());
                int xStartPoint = li.getBlocksStartX();
                int yStartPoint = li.getBlocksStartY();
                while (!line.startsWith("END_BLOCKS")) {
                    if (line.startsWith("START_BLOCKS")) {
                        line = in.readLine();
                        continue;
                    }
                    int startX = xStartPoint;
                    for (char c : line.toCharArray()) {
                        String s = String.valueOf(c);
                        if (blocksFromSymbolsFactory.isSpaceSymbol(s)) {
                            startX += blocksFromSymbolsFactory.getSpaceWidth(s);
                        } else if (blocksFromSymbolsFactory.isBlockSymbol(s)) {
                            Block block = blocksFromSymbolsFactory.getBlock(s, startX, yStartPoint);
                            li.getBlocks().add(block);
                            startX += block.getCollisionRectangle().getWidth();
                        }
                    }
                    startX = xStartPoint;
                    yStartPoint += li.getRowHeight();
                    line = in.readLine();
                }
                line = in.readLine();
            }

        } catch (IOException e) {
            System.out.println("Unable to open the file");
            e.printStackTrace();
        }
        return levelInformation;
    }
}
