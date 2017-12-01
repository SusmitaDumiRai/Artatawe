package co.uk.artatawe.profileImage;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The attributes and behaviours of a circle.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public class Circle extends ProfileImageComponent {
	private int radius; //The radius of the circle, from the starting point.
	
	/**
	 * Creates a circle.
	 * @param xStartPosition The x position at which the circle starts.
	 * @param yStartPosition The y position at which the circle starts.
	 * @param colour The colour of the circle.
	 * @param radius The radius of the circle.
	 */
	public Circle(int xStartPosition, int yStartPosition, Color colour, 
			int radius) {
		super(xStartPosition, yStartPosition, colour);
		this.radius = radius;
	}

	/**
	 * Gets the radius of the circle.
	 * @return The radius of the circle.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Resets the radius of the circle.
	 * @param radius The radius of the circle.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void displayComponent(GraphicsContext g) {
		// TODO Auto-generated method stub

	}
}
