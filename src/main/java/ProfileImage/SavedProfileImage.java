package ProfileImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The attributes and behaviours of a saved profile image.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public class SavedProfileImage extends ProfileImage {
	private Image profileImage; //The saved profile image, as JavaFX image.
	
	/**
	 * Creates a saved profile image.
	 * @param dimX The width of the profile image.
	 * @param dimY The height of the profile image. 
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.	
	 * @param fileName The filename of the saved profile image.
	 */
	public SavedProfileImage(int dimX, int dimY, int posX, int posY, 
			String fileName) {
		super(dimX, dimY, posX, posY);
		this.profileImage = new Image(fileName);
	}
	
	/**
	 * Gets the saved profile image, as JavaFX image.
	 * @return The saved profile image, as JavaFX image.
	 */
	public Image getProfileImage() {
		return profileImage;
	}
	
	/**
	 * Resets the saved profile image.
	 * @param fileName The filename of the saved profile image.
	 */
	public void setProfileImage(String fileName) {
		profileImage = new Image(fileName);
	}
	
	/**
	 * Converts a saved profile image to a string.
	 */
	public String toString() {
		String result = "";
		result += super.toString();
		result += "Image:\t" + this.profileImage.toString();
		return result;
	}
	
	@Override
	public void drawProileImage(GraphicsContext g) {

	}

}
