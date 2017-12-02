package co.uk.artatawe.sample;

/**
 * Creates bid object.
 * Utilizes bidFileManager to store bid obj in txt file.
 */

public class Bid {

    //TODO add bid id variable
    //TODO add auction id variable

    private User buyer;
    private double bidAmount;
    private String dateAndTime;


    /**
     * Constructor for bid.
     * TODO needs more info here for the params
     * @param buyer
     * @param bidAmount
     * @param dateAndTime
     */
    public Bid(User buyer, double bidAmount, String dateAndTime) {
        this.buyer = buyer;
        this.bidAmount = bidAmount;
        this.dateAndTime = dateAndTime;

    }

    /**
     * Gets the user that placed the bid.
     * @return buyer
     */
    public User getBuyer() {
        return buyer;
    }

    /**
     * Gets the amount that the user bid on the auction.
     * @return bidAmount
     */
    public double getBidAmount() {
        return bidAmount;
    }

    /**
     * Gets the date and time that the bid was placed.
     * @return dateAndTime
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    //add method to give bid obj to bidFileManager
    // add a toString method


}

