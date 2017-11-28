package co.uk.artatawe.artwork;

import java.util.ArrayList;

/**
 * Sculpture class inherits from Artwork class, creates sculpture object
 */
public class Sculpture extends Artwork {

    private double depth;
    private String mainMaterial;
    private ArrayList<String> extraPhoto = new ArrayList<>();

    /**
     * constructor that creates an sculpture object
     * @param typeOfArtwork the type of an artwork, in this class it's a sculpture
     * @param title         title of the sculpture
     * @param description   description of the sculpture
     * @param photo         photo of the sculpture
     * @param nameOfCreator name of the person who made the sculpture
     * @param reservedPrice reserved price of the sculpture
     * @param dateEntered   date of when the sculpture has been put to auction
     * @param bidsAllowed   amount of the bids allowed for the sculpture
     * @param mainMaterial  material the sculpture is made of
     * @param extraPhotos   some extra photos for the sculpture
     * @param width         width of the sculpture
     * @param height        height of the sculpture
     * @param depth         depth of the sculpture
     */
    public Sculpture(String typeOfArtwork, String title, String description, String photo,
                     String nameOfCreator, double reservedPrice, String dateEntered, int bidsAllowed,
                     String mainMaterial, ArrayList<String> extraPhotos, double width, double height, double depth) {
        super(typeOfArtwork, title, description, photo, nameOfCreator, reservedPrice, dateEntered, bidsAllowed, width, height);

        this.depth = depth;
        this.mainMaterial = mainMaterial;
        this.extraPhoto = extraPhoto;
    }

    /**
     * method that gets depth of a sculpture
     * @return the depth of a sculpture
     */
    public double getDepth() {
        return depth;
    }

    /**
     * method that sets the depth of a sculpture
     * @param depth depth of a sculpture
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }

    /**
     * method that gets the main material of a sculpture
     * @return the main material a sculpture is made of
     */
    public String getMainMaterial() {
        return mainMaterial;
    }

    /**
     * method that sets the main material of a sculpture
     * @param mainMaterial the main material a sculpture is made of
     */
    public void setMainMaterial(String mainMaterial) {
        this.mainMaterial = mainMaterial;
    }

    /**
     * method that gets extra photos of the sculpture
     * @return extra photos of the sculpture
     */
    public ArrayList<String> getExtraPhoto() {
        return extraPhoto;
    }

    /**
     * method that sets the extra photos of a sculpture
     * @param extraPhoto some extra photos of the sculpture
     */
    public void setExtraPhoto(ArrayList<String> extraPhoto) {
        this.extraPhoto = extraPhoto;
    }
}
