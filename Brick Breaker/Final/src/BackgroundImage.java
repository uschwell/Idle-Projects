
import biuoop.DrawSurface;
import java.awt.image.BufferedImage;

/**
 * This class defines a Background  as an Image.

 * @author Uriel Schwell
 * @version 13.06.2018
 */
public class BackgroundImage implements Sprite {
    private BufferedImage image;

    /**
     *BackgroundImage -Constructor.
     *
     * @param image - the image for the background.
     */
    public BackgroundImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * The function draws the sprite to the screen.
     *
     * @param d - the screen to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    /**
     * The function adds a sprite to a game.
     *
     * @param g - the game to add the sprite to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function removes a sprite from a game.
     *
     * @param g - the game to remove the sprite from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * The function notifies the sprites that time has passed.
     *
     * @param dt - speed.
     */
    public void timePassed(double dt) {

    }
}