package co.uk.artatawe.database;

/**
 * Handles communication to auction table in database.
 * Allows creation, deletion and updates to be made to auction table.
 *
 * @author 908928.
 * @version 1.0
 */
public class AuctionDatabaseManager extends DatabaseManager {

    /**
     * Empty constructor.
     */
    public AuctionDatabaseManager() {
    }

    /**
     * Creates auction table if not exist.
     */
    public void createAuctionTable() {
        String sqlCreateAuctionTable = "CREATE TABLE IF NOT EXISTS bid( " +
                "auctionID INTEGER PRIMARY KEY not null,\n" +
                "bidID integer," + //attributes have to be atomic.
                "seller text not null," + //username of seller.
                "winningBid int," + //winning bid id.
                "numOfBidsLeft integer not null," +
                "auctioncomp int not null," + //sqlite does not support boolean, but instead 0 and 1.
                "highestbid real not null,"  + //originally the reserve price.
                "foreign key (auctionID) references artwork (artworkID)," +
                "foreign key (buyer) references user (username)," +
                "foreign key (winningBid) references bid (bidid)" +
                "foreign key (bidID) references bid (bidid));";

        executeStatement(sqlCreateAuctionTable);
    }
}
