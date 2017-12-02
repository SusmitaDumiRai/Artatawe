package co.uk.artatawe.profileImage;

import javafx.scene.Parent;

/**
 * The attributes and behaviours of a profile image.
 * 
 * @author Adam Taylor
 * @version 1.2
 */
public abstract class ProfileImage {
	public static final int X_INDEX = 0; //The index of x in an array.
	public static final int Y_INDEX = 1; //The index of y in an array.
	private int size; //The size of the profile image, which is square.
	private int[] position; //The x, y position of center of profile image.
	
	/**
	 * Creates a profile image.
	 * @param size The size of the profile image, which is square.
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.
	 */
	public ProfileImage(int size, int posX, int posY) {
		this.size = size;
		position[X_INDEX] = posX;
		position[Y_INDEX] = posY;
	}

	/**
	 * Gets the size of the profile image, which is square.
	 * @return The size of the profile image.
	 */
	public int getSize() {
		return size;
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
	 * Resets the size of the profile image.
	 * @param width The size of the profile image.
	 */
	public void setSize(int size) {
		this.size = size;
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
		result += "Size:\t" + getSize() + "\n";
		result += "X Position:\t" + getXPosition() + "\n";
		result += "Y Position:\t" + getYPosition() + "\n";
		return result;
	}
	
	//Unsure on the type of graphics input.
	public abstract void displayProfileImage(Parent p);
}

