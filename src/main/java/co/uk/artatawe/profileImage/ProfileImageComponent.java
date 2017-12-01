package co.uk.artatawe.profileImage;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The attributes and behaviours of a profile image component.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public abstract class ProfileImageComponent {
	public static final int X_INDEX = 0; //The index of x in an array.
	public static final int Y_INDEX = 1; //The index of y in an array.
	private int[] startPosition; //The point at which the component starts.
	private Color colour; //The colour of the component.
	
	/**
	 * Creates a profile image component.
	 * @param xStartPosition The x position at which the component starts.
	 * @param yStartPosition The y position at which the component starts.
	 * @param colour The colour of the component.
	 */
	public ProfileImageComponent(int xStartPosition, int yStartPosition, Color colour) {
		this.startPosition[X_INDEX] = xStartPosition;
		this.startPosition[Y_INDEX] = yStartPosition;
		this.colour = colour;
	}

	/**
	 * Gets the x position at which the component starts.
	 * @param xStartPosition The x position at which the component starts.
	 */
	public int getXStartPosition() {
		return startPosition[X_INDEX];
	}

	/**
	 * Gets the y position at which the component starts.
	 * @param yStartPosition The y position at which the component starts.
	 */
	public int getYStartPosition() {
		return startPosition[Y_INDEX];
	}
	
	/**
	 * Gets the colour of the component.
	 * @return The colour of the component.
	 */
	public Color getColour() {
		return colour;
	}

	/**
	 * Resets the x position at which the component starts.
	 * @return The x position at which the component starts.
	 */
	public void setXStartPosition(int xStartPosition) {
		this.startPosition[X_INDEX] = xStartPosition;
	}
	
	/**
	 * Resets the y position at which the component starts.
	 * @return The y position at which the component starts.
	 */
	public void setYStartPosition(int yStartPosition) {
		this.startPosition[Y_INDEX] = yStartPosition;
	}
	
	/**
	 * Resets the colour of the component.
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
	
	//Unsure on implementation
	public abstract void displayComponent(GraphicsContext g);
}
