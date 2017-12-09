package co.uk.artatawe.profileImage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The attributes and behaviours of a saved profile image.
 *
 * @author Adam Taylor (905448)
 * @version 1.0
 */
public class SavedProfileImage extends ProfileImage {
    //The default size of a saved profile image.
    public static final double DEFAULT_SIZE = 400;
    private Image profileImage; //The saved profile image, as JavaFX image.

    /**
     * Creates a saved profile image of default size.
     *
     * @param fileName The filename of the saved profile image.
     */
    public SavedProfileImage(String fileName) {
        super(DEFAULT_SIZE, 0, 0);
        this.profileImage = new Image(fileName);
    }

    /**
     * Creates a saved profile image.
     *
     * @param size     The size of the profile image, which is square.
     * @param posX     The x position of the top left of the profile image.
     * @param posY     The y position of the top left of the profile image.
     * @param fileName The filename of the saved profile image.
     */
    public SavedProfileImage(int size, int posX, int posY,
                             String fileName) {
        super(size, posX, posY);
        this.profileImage = new Image(fileName);
    }

    /**
     * Gets the saved profile image, as JavaFX image.
     *
     * @return The saved profile image, as JavaFX image.
     */
    public Image getProfileImage() {
        return profileImage;
    }

    /**
     * Resets the saved profile image.
     *
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
     *
     * @param imageView The ImageView scene object the image is being displayed on.
     */
    @Override
    public void displayProfileImage(ImageView imageView) {
        imageView.setImage(getProfileImage());
        imageView.setTranslateX(getXPosition());
        imageView.setTranslateY(getYPosition());
        imageView.setFitWidth(getSize());
        imageView.setFitHeight(getSize());
        imageView.setPreserveRatio(true);
    }
}
