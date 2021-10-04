
import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.HashMap;

/**
 * The class creates blocks from the file specifications.
 *
 * @author - Uriel Schwell.
 * @version - 13.6.2018.
 */
public class BlocksCreator implements BlockCreator {
    private String symbol;
    private int width;
    private int height;
    private int hitPoints;
    private Color stroke;
    private Map<Integer, Color> colors;
    private Map<Integer, Image> images;

    /**
     * Setter.
     *
     * @param width1 - of the block.
     */
    public void setWidth(int width1) {
        this.width = width1;
    }

    /**
     * Setter.
     *
     * @param height1 - of the block.
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * Setter.
     *
     * @param hitPoints1 - of the block.
     */
    public void setHitPoints(int hitPoints1) {
        this.hitPoints = hitPoints1;
    }

    /**
     * Setter.
     *
     * @param stroke1 - of the block.
     */
    public void setStroke(Color stroke1) {
        this.stroke = stroke1;
    }

    /**
     * Setter.
     *
     * @param colors1 - of the block.
     */
    public void setColors(Map<Integer, Color> colors1) {
        this.colors = colors1;
    }

    /**
     * Setter.
     *
     * @param images1 - of the block.
     */
    public void setImages(Map<Integer, Image> images1) {
        this.images = images1;
    }

    /**
     * Setter.
     *
     * @param symbol1 - of the block.
     */
    public void setSymbol(String symbol1) {
        this.symbol = symbol1;
    }

    /**
     * Constructor.
     *
     * @param blockCreatorDef - the default input from the file.
     * @param blockCreators   - the block input from the file.
     */
    public BlocksCreator(Map<String, String> blockCreatorDef, Map<String, String> blockCreators) {
        this.colors = new HashMap<>();
        this.images = new HashMap<>();
        String strokeString;
        try {
            if (blockCreators.containsKey("symbol")) {
                this.setSymbol(blockCreatorDef.get("symbol"));
            }
            if (blockCreators.containsKey("width")) {
                this.setWidth(Integer.parseInt(blockCreators.get("width")));
            } else {
                this.setWidth(Integer.parseInt(blockCreatorDef.get("width")));
            }
            if (blockCreators.containsKey("height")) {
                this.setHeight(Integer.parseInt(blockCreators.get("height")));
            } else {
                this.setHeight(Integer.parseInt(blockCreatorDef.get("height")));
            }
            if (blockCreators.containsKey("hit_points")) {
                this.setHitPoints(Integer.parseInt(blockCreators.get("hit_points")));
            } else {
                this.setHitPoints(Integer.parseInt(blockCreatorDef.get("hit_points")));
            }
            if (blockCreators.containsKey("fill")) {
                if (blockCreators.get("fill").contains("color")) {
                    seperateToHitsColor(blockCreators);
                } else if (blockCreators.get("fill").contains("image")) {
                    seperateToHitsImage(blockCreators);
                }
            }
            for (Map.Entry<String, String> parameter : blockCreators.entrySet()) {
                if (parameter.getKey().startsWith("fill-")) {
                    if (parameter.getValue().contains("color")) {
                        seperateToHitsColor(blockCreators);
                    } else if (parameter.getValue().contains("image")) {
                        seperateToHitsImage(blockCreators);
                    }
                }
            }
            if (colors.size() == 0 && images.size() == 0) {
                for (Map.Entry<String, String> parameter : blockCreatorDef.entrySet()) {
                    if (parameter.getKey().startsWith("fill-")) {
                        if (parameter.getValue().contains("color")) {
                            seperateToHitsColor(blockCreatorDef);
                        } else if (parameter.getValue().contains("image")) {
                            seperateToHitsImage(blockCreatorDef);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("details are not valid");
        }
        if (blockCreators.containsKey("stroke")) {
            strokeString = blockCreators.get("stroke");
            this.stroke = ColorsParser.colorFromString(strokeString.substring(strokeString.indexOf("(") + 1,
                    strokeString.indexOf(")")));
        } else if (blockCreatorDef.containsKey("stroke")) {
            strokeString = blockCreatorDef.get("stroke");
            if (strokeString.contains("RGB")) {
                this.stroke = ColorsParser.colorFromString(strokeString.substring(strokeString.indexOf("(") + 1,
                        strokeString.indexOf(")")));
            } else if (strokeString.contains("color") && !(strokeString.contains("RGB"))) {
                this.stroke = ColorsParser.colorFromString(strokeString.substring(strokeString.indexOf("(") + 1,
                        strokeString.indexOf(")")));
            }
        } else {
            this.stroke = null;
        }
    }

    /**
     * The function gets the x and y values of the upper left point of the block
     * and creates a block by the specifications from the file.
     *
     * @param xpos - X value of the upper left point of the block.
     * @param ypos - Y value of the upper left point of the block.
     * @return - a block object.
     */
    public Block create(int xpos, int ypos) {
        return new Block(new Point(xpos, ypos), this.width, this.height,
                this.stroke, this.hitPoints, this.colors, images);
    }

    /**
     * The function converts the map from strings to colors.
     *
     * @param colorsMap - the map as string.
     */
    public void seperateToHitsColor(Map<String, String> colorsMap) {
        Color color = null;
        for (String string : colorsMap.keySet()) {
            if (string.equals("fill")) {
                color = ColorsParser.colorFromString(String.valueOf(colorsMap.get("fill")));
                this.colors.put(1, color);
            } else if (string.contains("fill-")) {
                String hits = string.substring(5);
                color = ColorsParser.colorFromString(String.valueOf(colorsMap.get("fill-" + hits)));
                this.colors.put(Integer.parseInt(hits), color);
            }
        }
    }

    /**
     * Seperate to hits image.
     *
     * @param imagesMap the map for color
     */
    public void seperateToHitsImage(Map<String, String> imagesMap) {
        Image image = null;
        for (String string : imagesMap.keySet()) {
            if (string.equals("fill")) {
                String imagePath = imagesMap.get("fill").substring(6,
                        imagesMap.get("fill").length() - 1);
                image = ImageParser.parseImage(imagePath);
                this.images.put(1, image);
            } else if (string.contains("fill-")) {
                String hits = string.substring(5);
                String imagePath = imagesMap.get("fill-" + hits).substring(6,
                        imagesMap.get("fill-" + hits).length() - 1);
                image = ImageParser.parseImage(imagePath);
                this.images.put(Integer.parseInt(hits), image);
            }
//                String colorPattern = "[0-9]*";
//                Pattern pattern = Pattern.compile(colorPattern);
//                Matcher matcher = pattern.matcher(string);
//                if (matcher.find()) {
//                    int numOfHit = Integer.parseInt(string.substring(matcher.start(), matcher.end()));
//                    image = ImageParser.parseImage(string);
//                    this.images.put(numOfHit, image);
//                }
        }
    }
}
