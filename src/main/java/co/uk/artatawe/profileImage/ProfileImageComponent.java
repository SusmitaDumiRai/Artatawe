package co.uk.artatawe.profileImage;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * The attributes and behaviours of a profile image component.
 *
 * @author Adam Taylor
 * @version 1.0
 */
public abstract class ProfileImageComponent {
    public static final int NO_OF_DIMENSIONS = 2;
    public static final int X_INDEX = 0; //The index of x in an array.
    public static final int Y_INDEX = 1; //The index of y in an array.
    private double[] startPosition; //The point at which the component starts.
    private Color colour; //The colour of the component.

    /**
     * Creates a profile image component.
     *
     * @param xStartPosition The x position at which the component starts.
     * @param yStartPosition The y position at which the component starts.
     * @param colour         The colour of the component.
     */
    public ProfileImageComponent(double xStartPosition, double yStartPosition, Color colour) {
        startPosition = new double[NO_OF_DIMENSIONS];
        this.startPosition[X_INDEX] = xStartPosition;
        this.startPosition[Y_INDEX] = yStartPosition;
        this.colour = colour;
    }

    /**
     * Gets the x position at which the component starts.
     *
     * @return The x position at which the component starts.
     */
    public double getXStartPosition() {
        return startPosition[X_INDEX];
    }

    /**
     * Gets the y position at which the component starts.
     *
     * @return The y position at which the component starts.
     */
    public double getYStartPosition() {
        return startPosition[Y_INDEX];
    }

    /**
     * Gets the colour of the component.
     *
     * @return The colour of the component.
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Resets the x position at which the component starts.
     *
     * @param xStartPosition x start position.
     */
    public void setXStartPosition(double xStartPosition) {
        this.startPosition[X_INDEX] = xStartPosition;
    }

    /**
     * Resets the y position at which the component starts.
     *
     * @param yStartPosition y start position.
     */
    public void setYStartPosition(double yStartPosition) {
        this.startPosition[Y_INDEX] = yStartPosition;
    }

    /**
     * Resets the colour of the component.
     *
     * @param colour The colour of the component.
     */
    public void setColour(Color colour) {
        this.colour = colour;
    }

    /**
     * Converts a profile image component to a string.
     */
    public String toString() {
        String result = "";
        result += "X Starting Position: " + getXStartPosition() + "\n";
        result += "Y Starting Position: " + getYStartPosition() + "\n";
        result += "Colour: " + getColour().toString() + "\n";
        return result;
    }


    public abstract void displayComponent(Canvas canvas);
}
