package co.uk.artatawe.profileImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * TODO comments



/**
 * The attributes and behaviours of a preset profile image.
 * 
 * @author Adam Taylor
 * @version 1.0

 */
public class PresetProfileImage extends ProfileImage {
	private PresetImage presetImage; //The selected preset profile image.
	
	/**
	 * Creates a preset profile image.
	 * @param dimX The width of the profile image.
	 * @param dimY The height of the profile image. 
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.	
	 * @param presetImage The selected preset profile image.
	 */
	public PresetProfileImage(int dimX, int dimY, int posX, int posY, 
			PresetImage presetImage) {
		super(dimX, dimY, posX, posY);
		this.presetImage = presetImage;
	}

	/**
	 * Resets the selected preset profile image.
	 * @param image The selected preset profile image.
	 */
	public void setPresetImage(PresetImage image) {
		this.presetImage = image;
	}
	
	/**
	 * Gets the selected preset profile image.
	 * @return The selected preset profile image.
	 */
	public PresetImage getPresetImage() {
		return presetImage;
	}
	
	/**
	 * Gets the selected preset profile image, as a JavaFX image.
	 * @return The selected preset profile image, as a JavaFX image.
	 */
	public Image getImage() {
		return presetImage.getImage();
	}
	
	/**
	 * Converts a preset profile image to a string.
	 */
	public String toString() {
		String result = "";
		result += super.toString();
		result += "Image:\t" + this.presetImage.toString();
		return result;
	}

	@Override
        public void drawProfileImage(GraphicsContext g) {

        }


}