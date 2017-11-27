package sample;
import java.util.*;
public class Auction {
	
	private int numOfBidsLeft;
	private boolean auctionComp;
	private Artwork artwork;
	private User sellerUsername;
	private User winner;
	private Artwork bidsAllowed;
	private Bid highestBid;
	private ArrayList<Bid> bidsOnArtwork;

	
	public Auction(User user, Artwork artwork){
		this.sellerUsername = user;
		this.artwork = artwork;
		this.highestBid = null;
		this.bidsOnArtwork = new ArrayList<>();
	}
	
	public void decBid(){
		if(numOfBidsLeft < this.bidsAllowed && numOfBidsLeft != 0) {
			numOfBidsLeft--;
		}
		else{
			setAuctionComp(auctionComp);
		}
	}
	
	public Bid addBid(Bid newBid){
		if(numofBidsLeft < bidsAllowed && numOfBidsLeft != 0){
			if(Bid.getBidAmount() < highestBid.getBidAmount()){
				this.highestBid = newBid;
				this.bidsOnArtwork.add(newBid);
				this.decBid();
			}
			else{
				System.out.println("Please enter a bid higher than the current highest bid price");
			}
		}
	}
	
	public Artwork getArtwork(){
		return artwork;
	}
	
	public int getNumOfBidsLeft(){
		return numOfBidsLeft;
	}
	
	public User getSeller(){
		return sellerUsername;
	}
	
	public User getWinner(){
		return winner;
	}
	
	public Bid getHighestBid(){
		return highestBid;
	}
	
	public Bid getBidsOnArtwork(){
		return bidsOnArtwork;
	}
	
	public void setAuctionComp(){
		this.auctionComp;
		auctionComp = false;
		if (numOfBidsLeft == 0){
			auctionComp = true;
		}
		else{
			auctionComp = false;
		}
	}
	
	public Boolean getAuctionComp(){
		return auctionComp;
	}
	
	public String toString(){
		String output = "Auction details \nArtwork ID: " + getArtwork().getArtworkID() + "\nArtwork Name: " 
				+ this.getArtwork().getArtworkTitle() + "\nSeller Username: " + this.sellerUsername.toString() + "/nNumber of Bids Left: " 
				+ this.numOfBidsLeft.toString() + "/nHighestBid: " + this.highestBid.toString() + "/nHighest Bidder: " + this.highestBid.getBuyer().getUsername();
		return output;
		//SEE HOW JAMES TOSTRINGS HIS INFO TO SEE WHETHER I NEED TO PULL BID AMOUNT
	}
		
}
