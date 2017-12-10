package co.uk.artatawe.database;


import co.uk.artatawe.main.FavouriteUsers;
import co.uk.artatawe.main.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Handles communication to favourite user table in database.
 * Allows creation, deletion and updates to be made to favourite user table.
 *
 * @author 908928 - Susmita
 * @version 1.0
 */
public class FavouriteUserDatabaseManager extends DatabaseManager {

    /**
     * Empty constructor.
     */
    public FavouriteUserDatabaseManager() {

    }

    /**
     * Creates table in database if it does not exist.
     */
    public void createFavouriteUserTable() {
        String sqlCreateFavouriteUserTable = "CREATE TABLE IF NOT EXISTS favouriteUser(" +
                " username1 text not null,\n" +
                " username2 text not null, " +
                "primary key (username1, username2)," +
                "foreign key (username1) references user (username)," +
                "foreign key (username2) references user (username));";

        executeStatement(sqlCreateFavouriteUserTable);
    }

    /**
     * Gets array list of favourite users.
     *
     * @param sqlSelect sql statement to be executed.
     * @return array list of favourite users.
     */
    public ArrayList<FavouriteUsers> getFavouriteUsers(String sqlSelect) {
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
        ArrayList<FavouriteUsers> favouriteUsersArrayList = new ArrayList<>();

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {

                User user1 = userDatabaseManager.getUser(resultSet.getString("username1")); //get user 1 info.
                User user2 = userDatabaseManager.getUser(resultSet.getString("username2")); //get user 2 info.
                favouriteUsersArrayList.add(new FavouriteUsers(user1, user2));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return favouriteUsersArrayList;

    }
}
