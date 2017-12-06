package co.uk.artatawe.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                "bidID INTEGER PRIMARY KEY not null," +
                "auctionID integer not null," +
                "buyer text not null," + //username of buyer.
                " bidAmount real not null," +
                " dateAndTime text not null," + //date and time of bid made.
                "foreign key (auctionid) references auction (auctionid)" +
                "foreign key (buyer) references  user (username));";

        executeStatement(sqlCreateBidTable);
    }

    /**
     * Displays all bids.
     */
    public void getAllBids(String sqlSelect) {

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("bidid") + "\t" +
                        resultSet.getString("buyer") + "\t" +
                        resultSet.getString("bidamount") + "\t" +
                        resultSet.getString("dateandtime"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
