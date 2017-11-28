package co.uk.artatawe.database;

import java.sql.*;

/**
 * Handles communication to user table in database.
 * Allows creation, deletion and updates to be made to user table.
 *
 * @author 908928.
 */
public class UserDatabaseManager extends  DatabaseManager {
    private String fileName = "artatawe.db"; //database file name.
    private String url = "jdbc:sqlite:" + fileName; //connection url to database.


    /**
     * Empty constructor.
     */
    public UserDatabaseManager() {
    }



    /**
     * Creates table in database if it does not exist.
     */
    public void createUserTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS User(" +
                " username text PRIMARY KEY not null,\n" +
                " firstname text not null,\n" +
                " surname text not null,\n" +
                " phonenumber text not null,\n" +
                " address text not null,\n" +
                " postcode text not null,\n" +
                " lastlogin text not null,\n" +
                " profileimage text not null);";

    }

    /**
     * Add some predefined users.
     */


    //TODO: ADD MORE USERS.

    /**
     * Inserts new user into
     */

    public void insertIntoTable(String sqlInsertUser) {

        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
            preparedStatement.executeUpdate(); //execute SQL statement.
            System.out.println("Successfully added record to database.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }



    }

    public void updateTable() {

    }


    public void selectAll() {
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


}
