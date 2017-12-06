package co.uk.artatawe.profileImage;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * The attributes and behaviours of a profile image.
 * 
 * @author Adam Taylor
 * @version 1.2
 */
public abstract class ProfileImage {
	//The no of dimensions the profile image uses (e.g. 2 for 2D or 3 for 3D).
	public static final int NO_OF_DIMENSIONS = 2; 	
	public static final int X_INDEX = 0; //The index of x in an array.
	public static final int Y_INDEX = 1; //The index of y in an array.
	private double size; //The size of the profile image, which is square.
	private double[] position; //The x, y position of center of profile image.
	
	/**
	 * Creates a profile image.
	 * @param size The size of the profile image, which is square.
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.
	 */
	public ProfileImage(double size, double posX, double posY) {
		this.size = size;
		position = new double[NO_OF_DIMENSIONS];
		position[X_INDEX] = posX;
		position[Y_INDEX] = posY;
	}

	/**
	 * Gets the size of the profile image, which is square.
	 * @return The size of the profile image.
	 */
	public double getSize() {
		return size;
	}
	
	/**
	 * Gets the x position of the center of the profile image.
	 * @return The x position of the center of the profile image.
	 */
	public double getXPosition() {
		return position[X_INDEX];
	}
	
	/**
	 * Gets the y position of the center of the profile image.
	 * @return The y position of the center of the profile image.
	 */
	public double getYPosition() {
		return position[Y_INDEX];
	}
	
	/**
	 * Resets the size of the profile image.
	 * @param width The size of the profile image.
	 */
	public void setSize(double size) {
		this.size = size;
	}
	
	/**
	 * Resets the x position of the center of the profile image.
	 * @param x The x position of the center of the profile image.
	 */
	public void setXPosition(double x) {
		this.position[X_INDEX] = x;
	}
	
	/**
	 * Resets the y position of the center of the profile image.
	 * @param y The y position of the center of the profile image.
	 */
	public void setYPosition(double y) {
		this.position[Y_INDEX] = y;
	}
	
	/**
	 * Converts a profile image to a string.
	 */
	public String toString() {
		String result = "";
		result += "Size:\t" + getSize() + "\n";
		result += "X Position:\t" + getXPosition() + "\n";
		result += "Y Position:\t" + getYPosition() + "\n";
		return result;
	}
	
	//Unsure on the type of graphics input.
	public abstract void displayProfileImage(Pane p);
}

