package co.uk.artatawe.profileImage;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	 * @param size The size of the profile image, which is square.
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.	
	 * @param presetImage The selected preset profile image.
	 */
	public PresetProfileImage(int size, int posX, int posY, 
			PresetImage presetImage) {
		super(size, posX, posY);
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

	/**
	 * This should display a preset profile image.
	 * @param p The parent scene object the image is being displayed on.
	 */
	@Override
    public void displayProfileImage(Parent p) {
		ImageView imageView1 = new ImageView();
		imageView1.setImage(getImage());
		imageView1.setFitWidth(getSize());
		imageView1.setFitHeight(getSize());
		p.getChildrenUnmodifiable().add(imageView1);
    }


}