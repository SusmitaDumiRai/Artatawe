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
    public ArrayList<Auction> getAllAuctions(String sqlSelect) {

        ArrayList<Auction> auctionArrayList = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
/*
                System.out.println(resultSet.getInt("auctionid") + "\t" +
                        resultSet.getString("seller") + "\t" +
                        resultSet.getString("winningbid") + "\t" +
                        resultSet.getInt("auctioncomp") + "\t" +
                        resultSet.getDouble("highestbid") + "\t" +
                        resultSet.getInt("numOfBidsLeft"));
                        //TODO WINNER.
                        */

                /**
                 *
                 * @param numOfBidsLeft
                 * @param auctionComp
                 * @param artwork
                 * @param sellerUsername
                 * @param winner
                 * @param highestBid*/


                UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
                BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();
                User user = userDatabaseManager.getUser(resultSet.getString("seller"));

                ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();

                String sqlSelectAuction = "SELECT * FROM artwork where artworkid = " + resultSet.getInt("auctionid") + "';";

                Artwork artwork = artworkDatabaseManager.getArtwork(sqlSelectAuction);

                if (resultSet.getInt("auctioncomp") == 0) { //ongoing auction.
                    if (resultSet.getInt("numofbidsleft") != artwork.getBidsAllowed()) { //no bids placed yet, aka reserved price.

                        auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, user, resultSet.getDouble("highestbid")));
                    } else {
                        //Get the highest bid.

                        auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, user, bidDatabaseManager.getMaxBid(resultSet.getInt("auctionid"))));

                    }

                } else { //completed auction
                    // auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), true, artwork, user, bidDatabaseManager.getMaxBid(resultSet.getInt("auctionid"))));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return auctionArrayList;
    }

    public Auction getAuction(String sqlSelect) {

        Auction auction = new Auction();
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
                User user = userDatabaseManager.getUser(resultSet.getString("seller"));
                System.out.println(user.toString());


                ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();

                String sqlSelectAuction = "SELECT * FROM artwork where artworkid = " + resultSet.getInt("auctionid") + ";";

                Artwork artwork = artworkDatabaseManager.getArtwork(sqlSelectAuction);

                if (resultSet.getInt("auctioncomp") == 0) {
                    auction = (new Auction(resultSet.getInt("numOfBidsLeft"), false, artwork, user, resultSet.getDouble("highestbid")));
                } else {
                    //     auctionArrayList.add(new Auction(resultSet.getInt("numOfBidsLeft"), true, artwork, user, , resultSet.getDouble("highestbid"))); TODO. UPDATE ONCE BID DATABASE MANAGER IS SORTED :sob:
                }
            }
        } catch (SQLException ex) {

        }

        return auction;
    }

























    /*

    public  ArrayList<Artwork> getOngoingAuctions() {
        String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid;";
        ArrayList<Artwork> onGoignArtworks = new ArrayList<>();

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                if (resultSet.getString("typeofartwork").equals("painting")) { //add painting.
                    onGoignArtworks.add(new Painting(resultSet.getInt("artworkid"), resultSet.getString("typeofartwork"), resultSet.getString("title"), resultSet.getString("description"),
                            resultSet.getString("photo"), resultSet.getString("nameofcreator"), resultSet.getDouble("reservedprice"),
                            resultSet.getString("dateentered"), resultSet.getInt("bidsallowed"), resultSet.getDouble("width"),
                            resultSet.getDouble("height")));
                } else { //add sculpture.
                    onGoignArtworks.add(new Sculpture(resultSet.getInt("artworkid"), resultSet.getString("typeofartwork"), resultSet.getString("title"), resultSet.getString("description"),
                            resultSet.getString("photo"), resultSet.getString("nameofcreator"), resultSet.getDouble("reservedprice"),
                            resultSet.getString("dateentered"), resultSet.getInt("bidsallowed"), resultSet.getString("mainmaterial"),
                            resultSet.getString("extraphotos"),
                            resultSet.getDouble("width"), resultSet.getDouble("height"), resultSet.getDouble("depth")));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return onGoignArtworks;
    }
    */

}
