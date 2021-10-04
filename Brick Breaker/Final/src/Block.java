import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.Map;
import java.awt.Color;
import java.awt.Image;

/**
 * Block - this class will define all Blocks
 * that will exist in the GameLevel Environment
 * and all the functions that interact with
 * said blocks.
 *
 * @author Uriel Schwell
 * @version 05.04.2018
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //members
    private Rectangle rect;
    private Color colour;
    private Color stroke;
    private int hits;
    private ArrayList<HitListener> hitListeners;
    private Map<Integer, Color> colors;
    private Map<Integer, Image> images;


    /**
     * Block - this function builds/defines a Block.
     *
     * @param rect - the shape of the block
     * @param cl   -the color of the block
     * @param hits - the amount of hits the block has left
     */
    public Block(Rectangle rect, Color cl, int hits) {
        this.rect = rect;
        this.colour = cl;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     *Block - Constructor.
     *
     * @param upperLeft - the upper left point of the block.
     * @param width     - the width of the block.
     * @param height    - the height of the block.
     * @param stroke     - the color of the borders of the block.
     * @param numOfHits - the number of hits given to the ball.
     * @param colors - the colors of the block.
     * @param images - the images of the block.
     */
    public Block(Point upperLeft, double width, double height, Color stroke, int numOfHits, Map<Integer, Color> colors,
                 Map<Integer, Image> images) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.stroke = stroke;
        this.hits = numOfHits;
        this.hitListeners = new ArrayList<HitListener>();
        this.images = images;
        this.colors = colors;
    }


    /**
     * getCollisionRectangle - function returns the rectangle
     * we collided with.
     *
     * @return this.rect - the rectangle we collided with
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * getBlockColor - this function returns the color of our
     * current block.
     *
     * @return this.color - the color of our block.
     */
    public Color getBlockColor() {
        return this.colour;
    }


    /**
     * drawOn - function receives a rectangular block and draws
     * it (in the specified color) onto our GUI.
     *
     * @param object - the (rectangular) object we wish to draw
     */
    public void drawOn(DrawSurface object) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int placementX = (int) (x + (this.rect.getWidth() / 2));
        int placementY = (int) (y + (this.rect.getHeight() / 2));
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();
        object.setColor(this.colour);
        object.fillRectangle(x, y, width, height);
        object.setColor(Color.GRAY);
        object.drawRectangle(x, y, width, height);
        //this (legacy) was used to write how many hits a block had left
        //object.drawText(placementX, placementY, String.valueOf(this.hits), 10);
    }

    @Override
    public void timePassed(double dt) {

    }


    /**
     * addToGame - this function adds a game item (a block)
     * to both the collidable and the sprite lists.
     *
     * @param g - the game item we wanted to add
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * timePassed - this function will determine how
     * much time has passed.
     */
    public void timePassed() {
    }


    /**
     * remove Block from the game.
     * @param game -game we are rmoving from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);

    }

    /**
     * addHitListener - adds a listener.
     *
     * @param hl -listener to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * removeHitListener - removes the given Listener.
     *
     * @param hl - listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifyHit - function informs all Listeners that our Block has just taken a hit.
     *
     * @param hitter -the object (ball)doing the hitting.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * hit -this function informs us as to the changes in our balls' velocity upon hitting
     * a Block.
     *
     * @param hitter          - the ball hitting our Block.
     * @param collisionPoint  -the point of Collision
     * @param currentVelocity - the Balls' current velocity.
     * @return newV -the new velocity (direction having been changed).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xPoint = this.rect.getUpperLeft().getX();
        double yPoint = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        Velocity newV = hitter.getVelocity();
        //if we hit along a horizontal line
        if ((collisionPoint.getX() > xPoint) && (collisionPoint.getX() < (xPoint + this.rect.getWidth()))) {
            newV = new Velocity(hitter.getVelocity().getDx(), (-1) * hitter.getVelocity().getDy());
        }
        //if we hit along a vertical line
        if ((collisionPoint.getY() > yPoint) && (collisionPoint.getY() < (yPoint + this.rect.getHeight()))) {
            newV = new Velocity((-1) * hitter.getVelocity().getDx(), hitter.getVelocity().getDy());
            //if we hit EXACTLY on a corner
        } else if (collisionPoint.equals(this.rect.getUpperLeft())
                || ((collisionPoint.getX() == xPoint + width) && (collisionPoint.getY() == yPoint))
                || ((collisionPoint.getX() == xPoint) && (collisionPoint.getY() == (yPoint + height)))
                || ((collisionPoint.getX() == xPoint + width) && (collisionPoint.getY() == yPoint + height))
                ) {
            newV = new Velocity((-1) * hitter.getVelocity().getDx(), (-1) * hitter.getVelocity().getDy());
        }

        this.notifyHit(hitter);
        return newV;
    }


    /**
     * @return current hitpoints.
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     *
     * @param newHitPoints -new amount of hitpoints.
     */
    public void setHitPoints(int newHitPoints) {
        this.hits = newHitPoints;

    }

}
