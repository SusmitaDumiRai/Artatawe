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
     * Empty constructor.
     */
    public BidDatabaseManager() {

    }

    /**
     * Creates bid table if not exist.
     */
    public void createBidTable() {
        String sqlCreateBidTable = "CREATE TABLE IF NOT EXISTS bid( " +
                "bidID INTEGER PRIMARY KEY not null,\n" +
                "buyer text not null," + //username of buyer.
                " bidAmount real not null," +
                " dateAndTime text not null" + //date and time of bid made.
                ");";

        executeStatement(sqlCreateBidTable);
    }


}
