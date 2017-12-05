package co.uk.artatawe.profileImage;

import java.util.ArrayList;

import javafx.scene.Parent;

/**
 * The attributes and behaviours of a custom profile image.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public class CustomProfileImage extends ProfileImage {
	//The list of components that create the custom profile image.
	private ArrayList<ProfileImageComponent> component; 
	
	/**
	 * Creates a blank custom profile image.
	 * @param size The size of the profile image, which is square.
	 * @param posX The x position of the center of the profile image.
	 * @param posY The y position of the center of the profile image.	
	 */
	public CustomProfileImage(int size, int posX, int posY) {
		super(size, posX, posY);
	}

	/**
	 * Adds a component to the custom profile image.
	 * @param component the component to be added to the custom profile image.
	 */
	public void addComponent(ProfileImageComponent component) {
		this.component.add(component);
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
    public void displayProfileImage(Parent p) {


    }  
}
