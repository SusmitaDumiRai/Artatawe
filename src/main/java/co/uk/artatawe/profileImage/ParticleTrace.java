package co.uk.artatawe.profileImage;

import java.util.LinkedList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The attributes and behaviours of a particle trace.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public class ParticleTrace extends ProfileImageComponent {
	private LinkedList<Circle> particleTrace;
	
	/**
	 * Creates a blank particle trace.
	 * @param xStartPosition The x position at which the component starts.
	 * @param yStartPosition The y position at which the component starts.
	 * @param colour The colour of the component.
	 */
	public ParticleTrace(double xStartPosition, double yStartPosition, Color colour) {
		super(xStartPosition, yStartPosition, colour);
		particleTrace = new LinkedList<Circle>();
	}

	/**
	 * Extends the particle trace.
	 * @param c The circle that extends the particle trace.
	 */
	public void addToTrace(Circle c) {
		particleTrace.add(c);
	}
	
	@Override
	public void displayComponent(Canvas canvas) {
	}
}
