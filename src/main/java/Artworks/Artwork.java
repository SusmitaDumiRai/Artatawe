package Artworks;

/**
 * Created by Plamena on 27.11.2017
 */
public class Artwork {

    private String title;
    private String description;
    private String photo;
    private String nameOfCreator;
    private double reservedPrice;
    private String dateEntered;
    private int bidsAllowed;
    private int artworkID;
    private static int previousArtworkID;
    private String typeOfArtwork;
    private double width;
    private double height;


    public Artwork() {

    }

    public Artwork(String typeOfArtwork, String title, String description, String photo,
                   String nameOfCreator, double reservedPrice, String dateEntered, int bidsAllowed, double width, double height) {
        this.typeOfArtwork = typeOfArtwork;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.nameOfCreator = nameOfCreator;
        this.reservedPrice = reservedPrice;
        this.dateEntered = dateEntered;
        this.bidsAllowed = bidsAllowed;
        this.width = width;
        this.height = height;
    }

    /**
     *
     */
    public void addNewArtwork() {
    }

    /**
     *
     */
    public void displayAllArtworks() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNameOfCreator() {
        return nameOfCreator;
    }

    public void setNameOfCreator(String nameOfCreator) {
        this.nameOfCreator = nameOfCreator;
    }

    public double getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(double reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public int getBidsAllowed() {
        return bidsAllowed;
    }

    public void setBidsAllowed(int bidsAllowed) {
        this.bidsAllowed = bidsAllowed;
    }

    public int getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }

    public static int getPreviousArtworkID() {
        return previousArtworkID;
    }

    public static void setPreviousArtworkID(int previousArtworkID) {
        Artwork.previousArtworkID = previousArtworkID;
    }

    public String getTypeOfArtwork() {
        return typeOfArtwork;
    }

    public void setTypeOfArtwork(String typeOfArtwork) {
        this.typeOfArtwork = typeOfArtwork;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
