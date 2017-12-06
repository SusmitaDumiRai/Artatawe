package co.uk.artatawe.main;


/**
 * Creates bid object.
 */

public class Bid {

    private User buyer;
    private double bidAmount;
    private String dateAndTime;
    private int bidID;
    private int auctionID;


    /**
     * Constructor for bid.
     * @param buyer The user that placed the bid.
     * @param bidAmount The amount that the user bid.
     * @param dateAndTime The date and time that the bid was placed.
     * @param auctionID The ID of the auction that the bid is made on.
     */
    public Bid(User buyer, double bidAmount, String dateAndTime, int auctionID) {
        this.buyer = buyer;
        this.bidAmount = bidAmount;
        this.dateAndTime = dateAndTime;
        this.auctionID = auctionID;

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
    public int getBidID() {
        return bidID;
    }

    /**
     * Gets the ID of the auction the bid is made on.
     * @return auctionID
     */
    public int getAuctionID() {
        return auctionID;
    }

    @Override
    public String toString() {
        return "Bid: " +
                "User='" + buyer.getUserName() +
                ",bidID='" + bidID +
                ",amount bid='" + bidAmount +
                ",date and time='" + dateAndTime;
    }

}

