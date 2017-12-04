package co.uk.artatawe.profileImage;

import javafx.scene.image.Image;

/**
 * The attributes and behaviours of a collection of preset images.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public enum PresetImage {
	IMAGE_DOG("PresetImage_Dog.jpg"),		//THIS FILE DOESN'T YET EXIST.
	IMAGE_CAT("PresetImage_Cat.jpg"),		//THIS FILE DOESN'T YET EXIST.
	IMAGE_BEAR("PresetImage_Bear.jpg"),	//THIS FILE DOESN'T YET EXIST.
	IMAGE_LION("PresetImage_Lion.jpg"),		//THIS FILE DOESN'T YET EXIST.
	IMAGE_PENGUIN("PresetImage_Penguin.jpg");	//THIS FILE DOESN'T YET EXIST.
	
	private final Image presetImage; //The preset JavaFX image. 
	private static String filepath = "../PresetImages/"; //The file path of the location of the preset images.
	
	/**
	 * Creates a preset image.
	 * @param fileName The filename of the image e.g. "Dog.jpg" 
	 */
	PresetImage(String fileName) {
		this.presetImage = new Image(fileName);
	}
	
	/**
	 * Gets the preset image as a JavaFX image.
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
