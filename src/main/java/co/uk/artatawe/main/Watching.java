package co.uk.artatawe.main;

/**
 * Class that handles who is watching what auction.
 *
 * @author 908928
 */
public class Watching {

    private int auctionID; //ID of auction being watched.
    private String username; //user watching auction.

    /**
     * Constructor.
     * @param auctionID ID of auction being watched.
     * @param username user watching auction.
     */
    public Watching(int auctionID, String username) {
        this.auctionID = auctionID;
        this.username = username;
    }

    /**
     * Gets auction ID.
     * @return auction id.
     */
    public int getAuctionID() {
        return auctionID;
    }

    /**
     * Sets auction id.
     * @param auctionID auction id.
     */
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    /**
     * Gets username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets username.
     * @param username username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
