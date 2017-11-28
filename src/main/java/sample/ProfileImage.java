package sample;

import javafx.scene.canvas.GraphicsContext;

/**
 * The attributes and behaviours of a profile image.
 * 
 * @author Adam Taylor
 * @version 1.1
 */
public abstract class ProfileImage {
	public static final int X_INDEX = 0; //The index of x in an array.
	public static final int Y_INDEX = 1; //The index of y in an array.
	private int[] dimension; //The height and width of the profile image.
	private int[] position; //The x, y position of center of profile image.
	
	/**
	 * Creates a profile image.
	 * @param dimX The width of the profile image.
	 * @param dimY The height of the profile image. 
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.
	 */
	public ProfileImage(int dimX, int dimY, int posX, int posY) {
		dimension[X_INDEX] = dimX;
		dimension[Y_INDEX] = dimY;
		position[X_INDEX] = posX;
		position[Y_INDEX] = posY;
	}

	/**
	 * Gets the width of the profile image.
	 * @return The width of the profile image.
	 */
	public int getWidth() {
		return dimension[X_INDEX];
	}
	
	/**
	 * Gets the height of the profile image.
	 * @return The height of the profile image.
	 */
	public int getHeight() {
		return dimension[Y_INDEX];
	}
	
	/**
	 * Gets the x position of the center of the profile image.
	 * @return The x position of the center of the profile image.
	 */
	public int getXPosition() {
		return position[X_INDEX];
	}
	
	/**
	 * Gets the y position of the center of the profile image.
	 * @return The y position of the center of the profile image.
	 */
	public int getYPosition() {
		return position[Y_INDEX];
	}
	
	/**
	 * Resets the width of the profile image.
	 * @param width The width of the profile image.
	 */
	public void setWidth(int width) {
		this.dimension[X_INDEX] = width;
	}
	
	/**
	 * Resets the height of the profile image.
	 * @param height The height of the profile image.
	 */
	public void setHeight(int height) {
		this.dimension[Y_INDEX] = height;
	}
	
	/**
	 * Resets the x position of the center of the profile image.
	 * @param x The x position of the center of the profile image.
	 */
	public void setXPosition(int x) {
		this.position[X_INDEX] = x;
	}
	
	/**
	 * Resets the y position of the center of the profile image.
	 * @param y The y position of the center of the profile image.
	 */
	public void setYPosition(int y) {
		this.position[Y_INDEX] = y;
	}
	
	/**
	 * Converts a profile image to a string.
	 */
	public String toString() {
		String result = "";
		result += "Height:\t" + getHeight() + "\n";
		result += "Width:\t" + getWidth() + "\n";
		result += "X Position:\t" + getXPosition() + "\n";
		result += "Y Position:\t" + getYPosition() + "\n";
		return result;
	}
	
	//Unsure on the type of graphics input.
	public abstract void drawProileImage(GraphicsContext g);
}
