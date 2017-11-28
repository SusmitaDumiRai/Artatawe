package ProfileImage;


import javafx.scene.canvas.GraphicsContext;

/**
 * The attributes and behaviours of a custom profile image.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public class CustomProfileImage extends ProfileImage {

	/**
	 * Creates a custom profile image.
	 * @param dimX The width of the profile image.
	 * @param dimY The height of the profile image. 
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.	
	 */
	public CustomProfileImage(int dimX, int dimY, int posX, int posY) {
		super(dimX, dimY, posX, posY);
	}

	/**
	 * Converts a saved profile image to a string.
	 */
	public String toString() {
		String result = "";
		result += super.toString();
		return result;
	}
	
	@Override
	public void drawProileImage(GraphicsContext g) {

	}

}
