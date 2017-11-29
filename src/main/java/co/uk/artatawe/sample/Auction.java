package co.uk.artatawe.sample;

import co.uk.artatawe.artwork.Artwork;
import java.util.*;

/**
 * TODO add comments. Fix with bid when possible.
 */
public class Auction {

    private int numOfBidsLeft;
    private boolean auctionComp;
    private Artwork artwork;
    private User sellerUsername;
    private User winner;
    private Artwork bidsAllowed;
    private Bid highestBid;
    private ArrayList<Bid> bidsOnArtwork;


    public Auction(User user, Artwork artwork) {
        this.sellerUsername = user;
        this.artwork = artwork;
        this.highestBid = null;
        this.bidsOnArtwork = new ArrayList<>();
    }

    public void decBid() {

        if (numOfBidsLeft < this.bidsAllowed && numOfBidsLeft != 0) {
            numOfBidsLeft--;
        } else {
            setAuctionComp(auctionComp);
        }
    }

    public Bid addBid(Bid newBid) {
        if (numofBidsLeft < bidsAllowed && numOfBidsLeft != 0) {
            if (Bid.getBidAmount() < highestBid.getBidAmount()) {
                this.highestBid = newBid;
                this.bidsOnArtwork.add(newBid);
                this.decBid();
            } else {
                System.out.println("Please enter a bid higher than the current highest bid price");
            }
        }
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public int getNumOfBidsLeft() {
        return numOfBidsLeft;
    }

    public User getSeller() {
        return sellerUsername;
    }

    public User getWinner() {
        return winner;
    }

    public Bid getHighestBid() {
        return highestBid;
    }

    public ArrayList<Bid> getBidsOnArtwork() {
        return bidsOnArtwork;
    }

    public void setAuctionComp() {
        auctionComp = false;
        if (numOfBidsLeft == 0) {
            auctionComp = true;
        } else {
            auctionComp = false;
        }
    }

    public Boolean getAuctionComp() {
        return auctionComp;
    }

    public String toString() {
        String output = "Auction details \nArtwork ID: " + getArtwork().getArtworkID() + "\nArtwork Name: "
                + this.getArtwork().getTitle() + "\nSeller Username: " + this.sellerUsername + "/nNumber of Bids Left: "
                + numOfBidsLeft + "/nHighestBid: " + this.highestBid.toString() + "/nHighest Bidder: " + this.highestBid.getBuyer().getUserName();
        return output;
        //SEE HOW JAMES TOSTRINGS HIS INFO TO SEE WHETHER I NEED TO PULL BID AMOUNT
    }

}
