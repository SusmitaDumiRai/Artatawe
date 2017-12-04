package co.uk.artatawe.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String sqlCreateAuctionTable = "CREATE TABLE IF NOT EXISTS auction( " +
                "auctionID INTEGER PRIMARY KEY not null,\n" +
                "seller text not null," + //username of seller.
                "winningBid int," + //winning bid id.
                "numOfBidsLeft integer not null," +
                "auctioncomp int not null," + //sqlite does not support boolean, but instead 0 and 1.
                "highestbid real not null,"  + //originally the reserve price.
                "foreign key (auctionID) references artwork (artworkID)," +
                "foreign key (seller) references user (username)," +
                "foreign key (winningBid) references bid (bidid));";

        executeStatement(sqlCreateAuctionTable);
    }


    /**
     * Displays all auction info.
     */
    public void getAllAuctions() {
        String sqlSelect = "SELECT auctionid," +
                "seller," +
                "winningbid," +
                "numofbidsleft," +
                "auctioncomp," +
                "highestbid " +
                "FROM auction;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("auctionid") + "\t" +
                        resultSet.getString("seller") + "\t" +
                        resultSet.getString("winningbid") + "\t" +
                        resultSet.getInt("auctioncomp") + "\t" +
                        resultSet.getDouble("highestbid"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
