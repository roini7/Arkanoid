package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Keypress stoppable animation.
 */
public class KeypressStoppableAnimation implements Animation {
    private static final int WIDTH = 800, HEIGHT = 600;
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean stop, isAlreadyPressed = true;

    /**
     * Instantiates a new Keypress stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeypressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    /**
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        if (keyboardSensor.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * condition for stopping the animation loop.
     *
     * @return boolean condition for stopping
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
