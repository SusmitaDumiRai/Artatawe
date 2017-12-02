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
     * Creates auction table if not exist.
     */
    public void createAuctionTable() {
        String sqlCreateAuctionTable = "CREATE TABLE IF NOT EXISTS bid( " +
                "auctionID INTEGER PRIMARY KEY not null,\n" +
                "artworkID integer NOT NULL," +
                "bidID integer," +
                "seller text not null," +
                " winner text" +
                " numOfBidsLeft integer not null," +
                "auctioncomp int not null," + //sqlite does not support boolean, but instead 0 and 1.
                "highestbid real not null," +
                "foreign key (artworkID) references artwork (artworkID)," +
                "foreign key (winner) references user (username)," +
                "foreign key (buyer) references user (username)," +
                "foreign key (bid) references bid (bidid));";

        executeStatement(sqlCreateAuctionTable);


    }
}
