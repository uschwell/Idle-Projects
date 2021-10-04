
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class defines a Background  as a Color.

 * @author Uriel Schwell
 * @version 13.06.2018
 */
public class BackgroundColor implements Sprite {
    private Color color;

    /**
     * BackgroundColor -Constructor.
     *
     * @param color - the color.
     */
    public BackgroundColor(Color color) {
        this.color = color;
    }

    /**
     * The function draws the sprite to the screen.
     *
     * @param d - the screen to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
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
     * @param dt - speed balance.
     */
    public void timePassed(double dt) {

    }
}