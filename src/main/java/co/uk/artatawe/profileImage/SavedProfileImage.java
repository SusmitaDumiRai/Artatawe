package co.uk.artatawe.profileImage;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	 * @param size The size of the profile image, which is square.
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.	
	 * @param fileName The filename of the saved profile image.
	 */
	public SavedProfileImage(int size, int posX, int posY, 
			String fileName) {
		super(size, posX, posY);
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
	
	/**
	 * This should display a preset profile image.
	 * @param p The pane scene object the image is being displayed on.
	 */
	@Override
    public void displayProfileImage(Pane p) {
		ImageView presetImageView = new ImageView();
		presetImageView.setImage(getProfileImage());
		presetImageView.setTranslateX(getXPosition());
		presetImageView.setTranslateY(getYPosition());
		presetImageView.setFitWidth(getSize());
		presetImageView.setFitHeight(getSize());
		presetImageView.setPreserveRatio(true);
		p.getChildren().add(presetImageView);
    }
}
