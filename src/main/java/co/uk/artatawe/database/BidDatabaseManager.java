package co.uk.artatawe.database;

import co.uk.artatawe.main.Auction;
import co.uk.artatawe.main.Bid;
import co.uk.artatawe.main.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                "UNIQUE (bidamount, auctionid)," + //an amount is unique for certain auction.
                "foreign key (auctionid) references auction (auctionid)" +
                "foreign key (buyer) references  user (username));";

        executeStatement(sqlCreateBidTable);
    }

    /**
     * Gets all bids.
     */
    public ArrayList<Bid> getAllBids(String sqlSelect) {

        ArrayList<Bid> bidArrayList = new ArrayList<>();

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {

                User user = new UserDatabaseManager().getUser(resultSet.getString("buyer"));
                String sqlGetAuction = "SELECT * FROM AUCTION WHERE AUCTIONID = " + resultSet.getInt("auctionid");
                Auction auction = new AuctionDatabaseManager().getAuction(sqlGetAuction);
                bidArrayList.add(new Bid(user, resultSet.getDouble("bidamount"), resultSet.getString("dateandtime"), auction));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bidArrayList;

    }

    /**
     * Get bid from sql select statement.
     *
     * @param sqlSelectBid sql statement to be executed.
     * @return
     */

    public Bid getBid(String sqlSelectBid) {

        Bid bid = new Bid();


        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelectBid);
            while (resultSet.next()) {
                User user = new UserDatabaseManager().getUser(resultSet.getString("buyer"));

                String sqlSelect = "SELECT * FROM AUCTION WHERE AUCTIONID = " + resultSet.getInt("auctionid");
                Auction auction = new AuctionDatabaseManager().getAuction(sqlSelect);
                bid = new Bid(resultSet.getInt("bidid"), user, resultSet.getDouble("bidamount"), resultSet.getString("dateandtime"), auction);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bid;
    }


    public double getMaxBid(int auctionID) {
        String sqlSelect = "SELECT max(bidAmount) as maxBid from bid where auctionid = " + auctionID + ";";

        double maxBid = 0;

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                maxBid = resultSet.getDouble(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return maxBid;
    }

}
