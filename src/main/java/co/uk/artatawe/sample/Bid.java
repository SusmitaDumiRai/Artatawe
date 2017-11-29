package co.uk.artatawe.sample;

/**
 * creates bid object
 * utilizes bidFileManager to store bid obj in txt file
 */

public class Bid {

    //should be type User but causes an error as there's no User class yet
    private User buyer;
    private double bidAmount;
    private String dateAndTime;


    /**
     * constructor for bid
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
     * gets the user that placed the bid
     * @return buyer
     */
    public User getBuyer() {
        return buyer;
    }

    /**
     * gets the amount that the user bid on the auction
     * @return bidAmount
     */
    public double getBidAmount() {
        return bidAmount;
    }

    /**
     * gets the date and time that the bid was placed
     * @return dateAndTime
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    //add method to give bid obj to bidFileManager



}

