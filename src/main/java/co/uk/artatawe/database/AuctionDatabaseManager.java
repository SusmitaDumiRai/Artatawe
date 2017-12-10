package co.uk.artatawe.database;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.main.Auction;
import co.uk.artatawe.main.Bid;
import co.uk.artatawe.main.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Handles communication to auction table in database.
 * Allows creation, deletion and updates to be made to auction table.
 *
 * @author 908928 - Susmita
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
                "highestbid real not null," + //originally the reserve price.
                "foreign key (auctionID) references artwork (artworkID)," +
                "foreign key (seller) references user (username)," +
                "foreign key (winningBid) references bid (bidid));";

        executeStatement(sqlCreateAuctionTable);
    }


    /**
     * Displays all auction info.
     *
     * @return array list of auctions.
     */
    public ArrayList<Auction> getAllAuctions(String sqlSelect) {

        ArrayList<Auction> auctionArrayList = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();
                UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

                User seller = userDatabaseManager.getUser(resultSet.getString("seller")); //Get seller of auction.

                String sqlSelectAuction = "SELECT * FROM artwork where artworkid = " + resultSet.getInt("auctionid") + ";"; //Get artwork related to auction.

                Artwork artwork = new ArtworkDatabaseManager().getArtwork(sqlSelectAuction);

                //Ongoing auction.
                if (resultSet.getInt("auctioncomp") == 0) {
                    //No bids placed yet.
                    if (artwork.getBidsAllowed() == resultSet.getInt("numofbidsleft")) {
                        auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, seller,
                                resultSet.getInt("highestbid")));
                    } else {
                        auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, seller,
                                bidDatabaseManager.getMaxBid(resultSet.getInt("auctionid"))));
                    }


                } else { //Completed auction
                    String getWinningBid = "select * from bid where bidid = " + resultSet.getInt("winningbid");

                    auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), true, artwork, seller,
                            bidDatabaseManager.getMaxBid(resultSet.getInt("auctionid")), bidDatabaseManager.getBid(getWinningBid).getBuyer()));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return auctionArrayList;
    }

    /**
     * Gets one auction back.
     *
     * @param sqlSelect sql statement that returns one auction.
     * @return one auction.
     */
    public Auction getAuction(String sqlSelect) {

        Auction auction = new Auction();
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {

                BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();

                //Gets seller of auction.
                User user = new UserDatabaseManager().getUser(resultSet.getString("seller"));

                //Gets artwork related to auction.
                String sqlSelectAuction = "SELECT * FROM artwork where artworkid = " + resultSet.getInt("auctionid") + ";";

                Artwork artwork = new ArtworkDatabaseManager().getArtwork(sqlSelectAuction);

                //Checks if auction is completed.
                if (resultSet.getInt("auctioncomp") == 0) {
                    if (artwork.getBidsAllowed() == resultSet.getInt("numofbidsleft")) { //no bids placed yet.
                        auction = new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, user, resultSet.getInt("highestbid"));
                    } else {
                        auction = new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, user, bidDatabaseManager.getMaxBid(resultSet.getInt("auctionid")));
                    }
                } else {
                    auction = new Auction(resultSet.getInt("numOfBidsLeft"), true, artwork, user, bidDatabaseManager.getMaxBid(resultSet.getInt("auctionid")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return auction;
    }

}
