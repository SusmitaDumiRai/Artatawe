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
        String sqlSelect = "SELECT username," +
                "firstname," +
                "surname," +
                "phonenumber," +
                "address," +
                "postcode," +
                "lastlogin," +
                "profileimage," +
                "favouriteuser FROM user;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                /*
                System.out.println(resultSet.getString("username") + "\t" +
                        resultSet.getString("firstname") + "\t" +
                        resultSet.getString("surname") + "\t" +
                        resultSet.getString("phonenumber") + "\t" +
                        resultSet.getString("address") + "\t" +
                        resultSet.getString("postcode") + "\t" +
                        resultSet.getString("lastlogin") + "\t" +
                        resultSet.getString("profileimage") + "\t");*/

                //currently does not support favourite users and profile image.
                userArrayList.add(new User(resultSet.getString("username"), resultSet.getString("firstname"), resultSet.getString("surname"),
                        resultSet.getString("phonenumber"), resultSet.getString("address"), resultSet.getString("postcode")));


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return userArrayList;
    }

    public User getUser(String username) {
        String selectUser = "SELECT * FROM user where username = " + username;


    }

}
