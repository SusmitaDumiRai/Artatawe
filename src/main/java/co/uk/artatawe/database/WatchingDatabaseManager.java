package co.uk.artatawe.database;

import co.uk.artatawe.main.Watching;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Handles communication to watching table in database.
 * Allows creation, deletion and updates to be made to watching table.
 *
 * @author 908928
 * @author Plamena Tseneva
 * @version 1.0
 */
public class WatchingDatabaseManager extends DatabaseManager {

    /**
     * Empty constructor.
     */
    public WatchingDatabaseManager() {
    }

    /**
     * Creates table in database if it does not exist.
     */
    public void createWatchingTable() {
        String sqlCreateWatchingTable = "CREATE TABLE IF NOT EXISTS Watching( " +
                "auctionID INTEGER  not null,\n" +
                "username text not null," +
                "primary key (auctionid, username)," +
                "foreign key (auctionid) references auction (auctionid)," +
                "foreign key (username) references user (username));";
        executeStatement(sqlCreateWatchingTable);
    }

    public ArrayList<Watching> getAllWatching(String sqlSelect) {

        ArrayList<Watching> watchingArrayList = new ArrayList<>();

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                watchingArrayList.add(new Watching(resultSet.getInt("auctionid"), resultSet.getString("username")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return watchingArrayList;
    }


}
