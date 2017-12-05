package co.uk.artatawe.database;

import co.uk.artatawe.sample.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

/**
 * Handles communication to user table in database.
 * Allows creation, deletion and updates to be made to user table.
 *
 * @author 908928.
 * @version 1.0
 */
public class UserDatabaseManager extends  DatabaseManager {

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
     */

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM user;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {

                userArrayList.add(new User(resultSet.getString("username"), resultSet.getString("firstname"), resultSet.getString("surname"),
                        resultSet.getString("phonenumber"), resultSet.getString("address"), resultSet.getString("postcode"),
                        resultSet.getString("lastlogin"), resultSet.getString("profileimage")));


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return userArrayList;
    }

    /**
     * Returns information of certain user.
     * @param username user to search.
     * @return user info.
     */
    public User getUser(String username) {

        /*
        String userName, String firstName, String surname,
              String phoneNumber, String address, String postcode
         */
        User user = new User();

        String selectUser = "SELECT * FROM user where username = '" + username + "'";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectUser);
            while (resultSet.next()) {

                user = new User(resultSet.getString("username"), resultSet.getString("firstName"), resultSet.getString("surname"),
                        resultSet.getString("phonenumber"), resultSet.getString("address"), resultSet.getString("postcode"),
                        resultSet.getString("lastlogin"), resultSet.getString("profileImage"));
                /*
                System.out.println(resultSet.getString("username") + "\t" +
                        resultSet.getString("firstname") + "\t" +
                        resultSet.getString("surname") + "\t" +
                        resultSet.getString("phonenumber") + "\t" +
                        resultSet.getString("address") + "\t" +
                        resultSet.getString("postcode") + "\t" +
                        resultSet.getString("lastlogin") + "\t" +
                        resultSet.getString("profileimage") + "\t");
                        */

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(user.toString());
        return user;

    }

    /**
     * Gets array list of usernames.
     * @return array list of usernames.
     */
    public ArrayList<String> getAllUsernames() {

        ArrayList<String> usernameArrayList = new ArrayList<>();

        for (User user :  getAllUsers()) {
            usernameArrayList.add(user.getUserName().toLowerCase());
        }

        return usernameArrayList;

    }



    /*
    public void testUser() {
        String sqlSelect = "SELECT username," +
                "firstname," +
                "surname," +
                "phonenumber," +
                "address," +
                "postcode," +
                "lastlogin," +
                "profileimage FROM user;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {

                System.out.println(resultSet.getString("username") + "\t" +
                        resultSet.getString("firstname") + "\t" +
                        resultSet.getString("surname") + "\t" +
                        resultSet.getString("phonenumber") + "\t" +
                        resultSet.getString("address") + "\t" +
                        resultSet.getString("postcode") + "\t" +
                        resultSet.getString("lastlogin") + "\t" +
                        resultSet.getString("profileimage") + "\t");


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */

}
