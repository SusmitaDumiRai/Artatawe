package co.uk.artatawe.profileImage;

import javafx.scene.image.Image;

/**
 * The attributes and behaviours of a collection of preset images.
 *
 * @author Adam Taylor
 * @version 1.0
 */
public enum PresetImage {
    //Preset images taken from "https://www.vecteezy.com/vector-art/96013-cute-animal-avatars".
    IMAGE_BEAR("PresetImage_Bear.jpg"),        //A preset profile image of a bear.
    IMAGE_CAT("PresetImage_Cat.jpg"),        //A preset profile image of a cat.
    IMAGE_DOG("PresetImage_Dog.jpg"),        //A preset profile image of a dog.
    IMAGE_LION("PresetImage_Lion.jpg"),        //A preset profile image of a lion.
    IMAGE_PENGUIN("PresetImage_Penguin.jpg");        //A preset profile image of a penguin.

    private static final String FILE_PATH = "/co/uk/artatawe/profileImage/SavedProfileImages/"; //The file path of the location of the preset images.
    private final Image presetImage; //The preset JavaFX image.

    /**
     * Creates a preset image.
     *
     * @param fileName The filename of the image e.g. "Dog.jpg"
     */
    PresetImage(String fileName) {
        this.presetImage = new Image(FILE_PATH + fileName);
    }

    /**
     * Gets the preset image as a JavaFX image.
     *
     * @return The preset image, as a JavaFX image.
     */
    public Image getImage() {
        return presetImage;
    }

    /**
     * Converts a preset image to a string.
     */
    public String toString() {
        return presetImage.toString();
    }
}
