package co.uk.artatawe.database;

import co.uk.artatawe.main.User;
import co.uk.artatawe.profileImage.ProfileImage;
import co.uk.artatawe.profileImage.SavedProfileImage;

import java.sql.*;
import java.util.ArrayList;

/**
 * Handles communication to user table in database.
 * Allows creation, deletion and updates to be made to user table.
 *
 * @author 908928 - Susmita
 * @version 1.0
 */
public class UserDatabaseManager extends DatabaseManager {

    /**
     * Empty constructor.
     */
    public UserDatabaseManager() {
    }


    /**
     * Creates table in database if it does not exist.
     */
    public void createUserTable() {
        String sqlCreateUserTable = "CREATE TABLE IF NOT EXISTS User(" +
                " username text PRIMARY KEY not null,\n" +
                " firstname text not null,\n" +
                " surname text not null,\n" +
                " phonenumber text not null,\n" +
                " address text not null,\n" + //first line of address - number + street name.
                " postcode text not null,\n" +
                " lastlogin text not null,\n" + //date time of last login.
                " profileimage text not null, UNIQUE(username));";

        executeStatement(sqlCreateUserTable);
    }


    /**
     * Returns all users in the table.
     *
     * @param sqlSelect sql statement to be executed.
     * @return array list of all users.
     */
    public ArrayList<User> getAllUsers(String sqlSelect) {
        ArrayList<User> userArrayList = new ArrayList<>();

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {

                userArrayList.add(new User(resultSet.getString("username"), resultSet.getString("firstname"),
                        resultSet.getString("surname"), resultSet.getString("phonenumber"),
                        resultSet.getString("address"), resultSet.getString("postcode"),
                        resultSet.getString("lastlogin"), new SavedProfileImage(resultSet.getString("profileImage"))));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return userArrayList;
    }

    /**
     * Returns information of certain user.
     *
     * @param username user to search.
     * @return user info.
     */
    public User getUser(String username) {
        User user = new User();

        String selectUser = "SELECT * FROM user where username = '" + username + "'";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectUser);
            while (resultSet.next()) {

                user = new User(resultSet.getString("username"), resultSet.getString("firstName"), resultSet.getString("surname"),
                        resultSet.getString("phonenumber"), resultSet.getString("address"), resultSet.getString("postcode"),
                        resultSet.getString("lastlogin"), new SavedProfileImage(resultSet.getString("profileImage")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;

    }

    /**
     * Gets array list of usernames.
     *
     * @return array list of usernames.
     */
    public ArrayList<String> getAllUsernames() {

        String sqlSelect = "SELECT * FROM USER;";
        ArrayList<String> usernameArrayList = new ArrayList<>();

        for (User user : getAllUsers(sqlSelect)) {
            usernameArrayList.add(user.getUserName());
        }

        return usernameArrayList;

    }

    /**
     * Updates a users profile image in the user table of the system database.
     *
     * @param user     The user to be updated.
     * @param filepath The file path of the new profile image.
     */
    public void updateProfileImage(User user, String filepath) {
        String sql = "Update USER "
                + "Set profileImage = '" + filepath + "' "
                + "Where username = '" + user.getUserName() + "';";


        updateStatement(sql);

    }


}
