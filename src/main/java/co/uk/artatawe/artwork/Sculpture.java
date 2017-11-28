package co.uk.artatawe.artwork;

import java.util.ArrayList;

/**
 * Created by Plamena on 27.11.2017
 */
public class Sculpture extends Artwork {

    private double depth;
    private String mainMaterial;
    private ArrayList<String> extraPhoto = new ArrayList<>();

    public Sculpture(String typeOfArtwork, String title, String description, String photo,
                     String nameOfCreator, double reservedPrice, String dateEntered, int bidsAllowed,
                     String mainMaterial, ArrayList<String> extraPhotos, double width, double height, double depth) {
        super(typeOfArtwork, title, description, photo, nameOfCreator, reservedPrice, dateEntered, bidsAllowed, width, height);

        this.depth = depth;
        this.mainMaterial = mainMaterial;
        this.extraPhoto = extraPhoto;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(String mainMaterial) {
        this.mainMaterial = mainMaterial;
    }

    public ArrayList<String> getExtraPhoto() {
        return extraPhoto;
    }

    public void setExtraPhoto(ArrayList<String> extraPhoto) {
        this.extraPhoto = extraPhoto;
    }
}
