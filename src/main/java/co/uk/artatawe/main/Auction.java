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
 //   private ArrayList<Bid> bidsOnArtwork;


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
    public Auction(int numOfBidsLeft, boolean auctionComp, Artwork artwork, User sellerUsername, User winner, double highestBid) {
        this.numOfBidsLeft = numOfBidsLeft;
        this.auctionComp = auctionComp;
        this.artwork = artwork;
        this.sellerUsername = sellerUsername;
        this.winner = winner;
        this.highestBid = highestBid;
    }

    /**
     * Method to decline the number of bids left until number of bids is zero
     */
    public void decBid() {
        if (numOfBidsLeft < artwork.getBidsAllowed() && numOfBidsLeft != 0) {
            numOfBidsLeft--;
        } else {
            setAuctionComp();
        }
    }

    /**TODO i think this is not needed anymore.
     * Method to add a new bid to the auction on an artwork
     * @param newBid - a new bid which is placed
     */
    /*
    public void addBid(Bid newBid) {
    	if (this.bidsOnArtwork.size() == 0 && newBid.getBidAmount() > artwork.getReservedPrice()) {
    		this.bidsOnArtwork.add(newBid);
    		this.decBid();
    	}
    	else if (newBid.getBidAmount() > this.highestBid.getBidAmount() && 
    			this.bidsOnArtwork.size() <= this.numOfBidsLeft) {
    				this.highestBid = newBid;
    				this.bidsOnArtwork.add(newBid);
    				this.decBid();
    	} else {
    	    System.out.println("There was an error placing the following bid:" + newBid.toString());
    	}
    }
    */

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
     * Method to get the highest current bid on the artwork
     * @return highestBid - highest bid placed
     */
     /*
    public Bid getHighestBid() {
        return highestBid;
    }

    */
    /**
     * Method to get the information of all bids placed on an artwork
     * @return bidsOnArtwork - ArrayList of bids
     */
    /*
    public ArrayList<Bid> getBidsOnArtwork() {
        return bidsOnArtwork;
    }

*/
    /**
     * Method to set the auction to completed
     */
    public void setAuctionComp() {
        auctionComp = false;
        if (numOfBidsLeft == 0) {
            auctionComp = true;
        } else {
            auctionComp = false;
        }
    }

    /**
     * Method to get the status of the auction
     * @return auctionComp - boolean to state whether the auction has completed
     *  or not
     */
    public Boolean getAuctionComp() {
        return auctionComp;
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
