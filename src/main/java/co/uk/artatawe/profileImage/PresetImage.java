package co.uk.artatawe.profileImage;

import javafx.scene.image.Image;

/**
 * The attributes and behaviours of a collection of preset images.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public enum PresetImage {
	IMAGE_DOG("Dog.jpg"),		//THIS FILE DOESN'T YET EXIST.
	IMAGE_CAT("Cat.jpg"),		//THIS FILE DOESN'T YET EXIST.
	IMAGE_RABBIT("Rabbit.jpg"),	//THIS FILE DOESN'T YET EXIST.
	IMAGE_FISH("Fish.jpg"),		//THIS FILE DOESN'T YET EXIST.
	IMAGE_DRAGON("Dragon.jpg");	//THIS FILE DOESN'T YET EXIST.
	
	private final Image presetImage; //The preset JavaFX image. 
	
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
