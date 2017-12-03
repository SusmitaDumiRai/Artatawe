package co.uk.artatawe.database;

import java.sql.*;

/**
 * Class that handles creation of database.
 *
 * @author 908928.
 * @version 1.0
 */
public abstract class DatabaseManager {
    private String fileName = "co/uk/artatawe/sample/artatawe.db"; //database file name.
    private String connURL = "jdbc:sqlite:" + fileName;

    /**
     * Allows connection to be made to the database.
     * If database does not exist, creates database.
     * @return connection to database.
     */
    public Connection connect() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connURL);

        } catch (SQLException ex) {
            System.out.println("Error has occured: " + ex.getMessage());
        }

        return connection;
    }

    /**
     * Inserts new row into a table in the database.
     * @param sqlInsert insert SQL statement.
     */
    public void insertIntoTable(String sqlInsert) {

        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.executeUpdate(); //execute SQL statement.
            System.out.println("Successfully added record to database.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Executes SQL statements.
     * @param sqlStatement statement to be executed.
     */
    public void executeStatement(String sqlStatement) {

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            statement.execute(sqlStatement);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
