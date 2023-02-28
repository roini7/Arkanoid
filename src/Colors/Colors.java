package Colors;

import java.awt.Color;
import java.util.Random;

/**
 * Class to get arbitrary colors.
 */
public class Colors {
    private Color color;

    /**
     * Constructor for Colors.Colors.
     */
    public Colors() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        this.color = new Color(r, g, b);
    }

    /**
     * Get color.
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set color to desired color.
     *
     * @param color color to set to
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
