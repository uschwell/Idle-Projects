import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LevelName -class finds the name of the level.
 *
 * @version 13.06.2018
 * @author Uriel Schwell.
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * constructor.
     *
     * @param name - the name of the level.
     */
    public LevelName(String name) {
        this.name = name;
    }

    /**
     * The function draws the sprite to the screen.
     *
     * @param d - the screen to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(600, 17, "Level Name: " + this.name, 15);
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
        g.addSprite(this);
    }

    /**
     * The function notifies the sprites that time has passed.
     *
     * @param dt - speed balance.
     */
    @Override
    public void timePassed(double dt) {
    }
}
