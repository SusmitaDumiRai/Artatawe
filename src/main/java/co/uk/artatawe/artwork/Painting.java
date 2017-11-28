package co.uk.artatawe.artwork;

/**
 * Painting class inherits from Artwork class, creates painting object
 */
public class Painting extends Artwork {

    /**
     * constructor that creates an painting object
     * @param typeOfArtwork the type of an artwork, in this class it's a painting
     * @param title         title of the painting
     * @param description   description of the painting
     * @param photo         photo of the painting
     * @param nameOfCreator name of the person who made the painting
     * @param reservedPrice reserved price of the painting
     * @param dateEntered   date of when the painting has been put to auction
     * @param bidsAllowed   amount of the bids allowed for the painting
     * @param width         width of the painting
     * @param height        height of the painting
     */
    public Painting(String typeOfArtwork, String title, String description, String photo, String nameOfCreator,
                    double reservedPrice, String dateEntered, int bidsAllowed, double width, double height) {
        super(typeOfArtwork, title, description, photo, nameOfCreator, reservedPrice, dateEntered, bidsAllowed, width, height);

    }

}
