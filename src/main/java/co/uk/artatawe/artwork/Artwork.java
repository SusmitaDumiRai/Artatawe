package co.uk.artatawe.artwork;

/**
 * Artwork class creates and stores data for an artwork object. Painting and Sculpture are based on this class
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

    /**
     * empty constructor
     */
    public Artwork() {
    }

    /**
     * constructor that creates an artwork object
     * @param typeOfArtwork the type of an artwork, can be either a painting or a sculpture
     * @param title         title of the artwork
     * @param description   description of the artwork
     * @param photo         photo of the artwork
     * @param nameOfCreator name of the person who made the artwork
     * @param reservedPrice reserved price of the artwork
     * @param dateEntered   date of when the artwork has been put to auction
     * @param bidsAllowed   amount of the bids allowed for the artwork
     * @param width         width of the artwork
     * @param height        height of the artwork
     */
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
     * method that adds a new artwork
     */
    public void addNewArtwork() {
    }

    /**
     * method that gets the title of an artwork
     * @return the title of an artwork
     */
    public String getTitle() {
        return title;
    }

    /**
     * method that sets title of an artwork
     * @param title title of an artwork
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * method that gets description of an artwork
     * @return the description of an artwork
     */
    public String getDescription() {
        return description;
    }

    /**
     * method that sets the description of an artwork
     * @param description description of an artwork
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * method that gets photo of an artwork
     * @return a photo of the artwork
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * method that sets the photo of an artwork
     * @param photo a photo of the artwork
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * method that gets the name of the creator of the artwork
     * @return the name of the person who has made the artwork
     */
    public String getNameOfCreator() {
        return nameOfCreator;
    }

    /**
     * method that sets the name of the creator
     * @param nameOfCreator the name of the person who created the artwork
     */
    public void setNameOfCreator(String nameOfCreator) {
        this.nameOfCreator = nameOfCreator;
    }

    /**
     * method that gets the reserved price for an artwork
     * @return the reserved price of an artwork
     */
    public double getReservedPrice() {
        return reservedPrice;
    }

    /**
     * method that sets the reserved price of an artwork
     * @param reservedPrice the reserved price for an artwork
     */
    public void setReservedPrice(double reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    /**
     * method that gets the date of when the artwork has been put for auction
     * @return the date of when the artwork has been entered for auction
     */
    public String getDateEntered() {
        return dateEntered;
    }

    /**
     * method that sets the date of when an artwork has been set for auction
     * @param dateEntered the date when an artwork has been put for an auction
     */
    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    /**
     * method that gets the amount of bids allowed for an artwork
     * @return the amount of bids allowed on an artwork
     */
    public int getBidsAllowed() {
        return bidsAllowed;
    }

    /**
     * method that sets the amount of bids allowed on an artwork
     * @param bidsAllowed the amount of bids allowed on an artwork
     */
    public void setBidsAllowed(int bidsAllowed) {
        this.bidsAllowed = bidsAllowed;
    }

    /**
     * method that gets the ID of an artwork
     * @return the ID of an artwork
     */
    public int getArtworkID() {
        return artworkID;
    }

    /**
     * method that sets the ID of an artwork
     * @param artworkID the ID of an artwork
     */
    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }

    /**
     * method that gets the previous ID of the artwork
     * @return the previous ID of the artwork
     */
    public static int getPreviousArtworkID() {
        return previousArtworkID;
    }

    /**
     * method that sets the previous ID of an artwork
     * @param previousArtworkID the previous ID of an artwork
     */
    public static void setPreviousArtworkID(int previousArtworkID) {
        Artwork.previousArtworkID = previousArtworkID;
    }

    /**
     * method that gets type of the artwork
     * @return the type of the artwork
     */
    public String getTypeOfArtwork() {
        return typeOfArtwork;
    }

    /**
     * method that sets the type of the artwork
     * @param typeOfArtwork the type of an artwork
     */
    public void setTypeOfArtwork(String typeOfArtwork) {
        this.typeOfArtwork = typeOfArtwork;
    }

    /**
     * method that gets the width of the artwork
     * @return the width of the artwork
     */
    public double getWidth() {
        return width;
    }

    /**
     * method that sets the width of the artwork
     * @param width the width of the artwork
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * method that gets the height of the artwork
     * @return the heighr of the artwork
     */
    public double getHeight() {
        return height;
    }

    /**
     * method that sets the height of the artwork
     * @param height the height of the artwork
     */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Artwork: " +
                "title='" + title +
                ", description='" + description +
                ", photo='" + photo +
                ", nameOfCreator='" + nameOfCreator +
                ", reservedPrice=" + reservedPrice +
                ", dateEntered='" + dateEntered +
                ", bidsAllowed=" + bidsAllowed +
                ", artworkID=" + artworkID +
                ", typeOfArtwork='" + typeOfArtwork +
                ", width=" + width +
                ", height=" + height;
    }
}
