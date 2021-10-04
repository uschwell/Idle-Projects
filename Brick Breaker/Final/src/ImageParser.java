import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;
import java.io.IOException;
/**
 * The class parses the Image from the file specifications.
 *
 * @author - Uriel Schwell.
 * @version - 13.6.2018.
 */
public class ImageParser {
    /**
     *  returns an Image.
     * @param path -path to follow.
     * @return -the image.
     */
    public static Image parseImage(String path) {
        Image image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Can't open Image");
        }
        return image;
    }
}