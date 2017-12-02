package co.uk.artatawe.sample;

import co.uk.artatawe.artwork.Artwork;

/**
 * Creates bid object.
 */

public class Bid {

    private User buyer;
    private double bidAmount;
    private String dateAndTime;
    private int bidID;


    /**
     * Constructor for bid.
     * @param buyer The User that placed the bid.
     * @param bidAmount The amount that the user bid.
     * @param dateAndTime The date and time that the bid was placed.
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

    /**
     * Gets the ID of the bid.
     * @return bidID
     */
    public int getBidID(){
        return bidID;
    }

    @Override
    public String toString(){
        return "Bid: " +
                "User='" + buyer +
                ",bidID='" + bidID +
                ",amount bid='" + bidAmount +
                ",date and time='" + dateAndTime +
                ",artwork bid on='" + artworkID;
    }

}

