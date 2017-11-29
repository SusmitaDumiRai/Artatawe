package co.uk.artatawe.database;

/**
 * Handles communication to bid table in database.
 * Allows creation, deletion and updates to be made to bid table.
 *
 * @author 908928.
 * @version 1.0
 */
public class BidDatabaseManager extends DatabaseManager {

    /**
     * Creates bid table if not exist.
     */
    public void createBidTable() {
        String sqlCreateBidTable = "CREATE TABLE IF NOT EXISTS bid( " +
                "bidID INTEGER PRIMARY KEY not null,\n" +
                "auctionID integer NOT NULL" +
                "buyer text not null," +
                " bidAmount real not null," +
                " dateAndTime text not null," +
                "foreign key (auctionid) references auction (auctionid));";

        executeStatement(sqlCreateBidTable);


    }
}
