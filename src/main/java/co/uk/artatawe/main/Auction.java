package co.uk.artatawe.main;


import co.uk.artatawe.artwork.Artwork;
import java.util.*;


/**
 * The Auction class creates an auction and stores information about an auction.
 * This includes data such as a user selling and buying an artwork, an artwork
 * related to a auction and bids on an artwork.
 * @author Robyn Smillie - 916927
 */
public class Auction {

    private int numOfBidsLeft;
    private boolean auctionComp;
    private Artwork artwork;
    private User sellerUsername;
    private User winner;
    private double highestBid;

    public Auction() {

    }

    /**
     *
     * @param numOfBidsLeft
     * @param auctionComp
     * @param artwork
     * @param sellerUsername
     * @param highestBid
     */
    public Auction(int numOfBidsLeft, boolean auctionComp, Artwork artwork, User sellerUsername, double highestBid) {
        this.numOfBidsLeft = numOfBidsLeft;
        this.auctionComp = auctionComp;
        this.artwork = artwork;
        this.sellerUsername = sellerUsername;
        this.highestBid = highestBid;
    }




    /**
     *
     * @param numOfBidsLeft
     * @param auctionComp
     * @param artwork
     * @param sellerUsername
     * @param winner
     * @param highestBid
     */
    public Auction(int numOfBidsLeft, boolean auctionComp, Artwork artwork, User sellerUsername, double highestBid, User winner) {
        this.numOfBidsLeft = numOfBidsLeft;
        this.auctionComp = auctionComp;
        this.artwork = artwork;
        this.sellerUsername = sellerUsername;
        this.winner = winner;
        this.highestBid = highestBid;
    }


    /**
     * Method to get an artwork
     * @return artwork - artwork object
     */
    public Artwork getArtwork() {
        return artwork;
    }

    /**
     * Method to get the number of bids left on an artwork at auction
     * @return numOfBidsLeft - bids left on an item
     */
    public int getNumOfBidsLeft() {
        return numOfBidsLeft;
    }

    /**
     * Method to get the seller of an artwork at auction
     * @return sellerUsername - username of the seller from User class
     */
    public User getSeller() {
        return sellerUsername;
    }

    /**
     * Method to set the winner.
     * @param winner winner of this auction.
     */
    public void setWinner(User winner) {
        this.winner = winner;
    }

    /**
     * Method to get the winning bids username
     * @return winner - winners username
     */
    public User getWinner() {
        return winner;
    }



    /**
     * Method to get the status of the auction
     * @return auctionComp - boolean to state whether the auction has completed
     *  or not
     */
    public Boolean getAuctionComp() {
        return auctionComp;
    }


    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    /**
     * Method to output all relevant information about an auction
     */
    public String toString() {
        String output = "Auction details \nArtwork ID: "
                + getArtwork().getArtworkID() + "\nArtwork Name: "
                + this.getArtwork().getTitle() + "\nSeller Username: "
                + this.sellerUsername.toString() + "/nNumber of Bids Left: "
              //  + numOfBidsLeft + "/nHighestBid: " + this.highestBid.toString()
                + "/nHighest Bidder: ";
             //   + this.highestBid.getBuyer().getUserName();
        return output;
    }

}
