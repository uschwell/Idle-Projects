import biuoop.DrawSurface;

/**
 * Animation - interface in charge of our animations.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */
public interface Animation {
    /**
     * doOneFrame -print one frame.
     * @param d -drawsurface
     * @param dt - framerate.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop -if we should stop.
     * @return -stop condition.
     */
    boolean shouldStop();
}
