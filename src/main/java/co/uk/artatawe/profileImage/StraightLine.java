package co.uk.artatawe.profileImage;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The attributes and behaviours of a straight line.
 * 
 * @author Adam Taylor
 * @version 1.0
 */
public class StraightLine extends ProfileImageComponent {
	private int[] finishPosition; //The point at which the straight line finishes.
	
	/**
	 * Creates a straight line.
	 * @param xStartPosition The x position at which the straight 
	 * line starts.
	 * @param yStartPosition The y position at which the straight 
	 * line starts.
	 * @param xFinishPosition The x position at which the straight 
	 * line finishes.
	 * @param yFinishPosition The y position at which the straight 
	 * line finishes.
	 * @param colour The colour of the straight line.
	 */
	public StraightLine(int xStartPosition, int yStartPosition, 
			int xFinishPosition, int yFinishPosition, Color colour) {
		super(xStartPosition, yStartPosition, colour);
		this.finishPosition[X_INDEX] = xFinishPosition;
		this.finishPosition[Y_INDEX] = yFinishPosition;
	}

	/**
	 * Gets the x position at which the straight line finishes.
	 * @return The x position at which the straight line finishes.
	 */
	public int getXFinishPosition() {
		return finishPosition[X_INDEX];
	}

	/**
	 * Gets the y position at which the straight line finishes.
	 * @return The y position at which the straight line finishes.
	 */
	public int getYFinishPosition() {
		return finishPosition[Y_INDEX];
	}
	
	/**
	 * Resets the x position at which the straight line finishes.
	 * @param xFinishPosition The x position at which the straight 
	 * line finishes.
	 */
	public void setXFinishPosition(int xFinishPosition) {
		this.finishPosition[X_INDEX] = xFinishPosition;
	}
	
	/**
	 * Resets the y position at which the straight line finishes.
	 * @param yFinishPosition The y position at which the straight 
	 * line finishes.
	 */
	public void setYFinishPosition(int yFinishPosition) {
		this.finishPosition[Y_INDEX] = yFinishPosition;
	}
	
	/**
	 * Converts a straight line to a string.
	 */
	public String toString() {
		String result = "";
		result += super.toString();
		result += "X Finishing Position: " + getXFinishPosition() + "\n";
		result += "Y Finishing Position: " + getYFinishPosition() + "\n";
		return result;
	}
	
	@Override
	public void displayComponent(GraphicsContext g) {
		// TODO Auto-generated method stub

	}

}
