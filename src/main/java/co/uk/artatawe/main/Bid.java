package co.uk.artatawe.main;


/**
 * Creates bid object.
 *
 * @author James Finlayson
 */

public class Bid {

    private User buyer;
    private double bidAmount;
    private String dateAndTime;
    private int bidID;
    private Auction auction;


    public Bid() {

    }

    /**
     * Constructor for bid.
     * @param buyer The user that placed the bid.
     * @param bidAmount The amount that the user bid.
     * @param dateAndTime The date and time that the bid was placed.
     * @param auction The ID of the auction that the bid is made on.
     */
    public Bid(User buyer, double bidAmount, String dateAndTime, Auction auction) {
        this.buyer = buyer;
        this.bidAmount = bidAmount;
        this.dateAndTime = dateAndTime;
        this.auction = auction;

    }

    /**
     * Constructor for bid.
     * @param bidID ID of bid.
     * @param buyer The user that placed the bid.
     * @param bidAmount The amount that the user bid.
     * @param dateAndTime The date and time that the bid was placed.
     * @param auction The ID of the auction that the bid is made on.
     */
    public Bid(int bidID, User buyer, double bidAmount, String dateAndTime, Auction auction) {
        this.buyer = buyer;
        this.bidAmount = bidAmount;
        this.dateAndTime = dateAndTime;
        this.bidID = bidID;
        this.auction = auction;
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
     * Gets the auction the bid is made on.
     * @return auction
     */
    public Auction getAuction() {
        return auction;
    }

    @Override
    public String toString() {
        return "Bid: " +
                "User='" + buyer.getUserName() +
                ",amount bid='" + bidAmount +
                ",date and time='" + dateAndTime;
    }

}

