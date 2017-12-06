package co.uk.artatawe.database;

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
                /*
                System.out.println(resultSet.getString("bidid") + "\t" +
                        resultSet.getString("buyer") + "\t" +
                        resultSet.getString("bidamount") + "\t" +
                        resultSet.getString("dateandtime"));
                        */
                UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
                User user = userDatabaseManager.getUser(resultSet.getString("buyer"));
                bidArrayList.add(new Bid(user, resultSet.getDouble("bidamount"), resultSet.getString("dateandtime"), resultSet.getInt("auctionid")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return  bidArrayList;

    }

    /**
     * Get bid from sql select statement.
     * @param sqlSelectBid
     * @return
     */
    public Bid getBid(String sqlSelectBid) {

        Bid bid = new Bid();

/*
               this.buyer = buyer;
        this.bidAmount = bidAmount;
        this.dateAndTime = dateAndTime;
        this.auctionID = auctionID;
        */

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelectBid);
            while (resultSet.next()) {
                UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
                User user = userDatabaseManager.getUser(resultSet.getString("buyer"));
                bid = new Bid(user, resultSet.getDouble("bidamount"), resultSet.getString("dateandtime"), resultSet.getInt("auctionid"));

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
