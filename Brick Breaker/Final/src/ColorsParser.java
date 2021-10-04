
import java.awt.Color;

/**
 * This class parses our colors from files.
 *
 * @author - Uriel Schwell.
 * @version - 13.6.2018.
 */
public class ColorsParser {
    /**
     * The function converts a string to a color.
     *
     * @param s - the color as string.
     * @return - the color.
     */
    public static Color colorFromString(String s) {
        Color color = null;
        if (s.startsWith("color(RGB")) {
            // start index in the 10-th character because of "color(RGB"
            // end index s.length() - 2 because of the 2 closing parenthesis in the end of the line.
            String colorsLine = s.substring(10, s.length() - 2);
            String[] colors = colorsLine.split(",");
            int r = Integer.parseInt(colors[0]);
            int g = Integer.parseInt(colors[1]);
            int b = Integer.parseInt(colors[2]);
            return new Color(r, g, b);
        } else {
            switch (s) {
                case "black":
                    color = Color.black;
                    break;
                case "blue":
                    color = Color.blue;
                    break;
                case "cyan":
                    color = Color.cyan;
                    break;
                case "gray":
                    color = Color.gray;
                    break;
                case "lightGray":
                    color = Color.lightGray;
                    break;
                case "green":
                    color = Color.green;
                    break;
                case "orange":
                    color = Color.orange;
                    break;
                case "pink":
                    color = Color.pink;
                    break;
                case "red":
                    color = Color.red;
                    break;
                case "white":
                    color = Color.white;
                    break;
                case "yellow":
                    color = Color.yellow;
                    break;
                default:
                    break;
            }
            return color;
        }
    }
}