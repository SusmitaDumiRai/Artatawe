package co.uk.artatawe.profileImage;

import javafx.scene.canvas.GraphicsContext;

/**
 * The attributes and behaviours of a profile image.
 *
 * @author Adam Taylor
 * @version 1.0
 */
public abstract class ProfileImage {
    public static final int X_INDEX = 0;
    public static final int Y_INDEX = 1;
    private int[] dimension;
    private int[] position;

    /**
     * Creates a profile image.
     *
     * @param dimX The width of the profile image.
     * @param dimY The height of the profile image.
     * @param posX The x position of the center of the profile image.
     * @param posY The y position of the center of the profile image.
     */
    public ProfileImage(int dimX, int dimY, int posX, int posY) {
        dimension[X_INDEX] = dimX;
        dimension[Y_INDEX] = dimY;
        position[X_INDEX] = posX;
        position[Y_INDEX] = posY;
    }

    /**
     * TODO comments
     */
    public int[] getDimension() {
        return dimension;
    }


    public void setDimension(int[] dimension) {
        this.dimension = dimension;
    }


    public int[] getPosition() {
        return position;
    }


    public void setPosition(int[] position) {
        this.position = position;
    }

    //Unsure on the type of graphics input.
    public abstract void drawProfileImage(GraphicsContext g);
}
