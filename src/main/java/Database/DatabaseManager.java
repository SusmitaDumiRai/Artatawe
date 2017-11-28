package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that handles creation of database.
 *
 * @author 908928
 * @version 1.0
 */
public abstract class DatabaseManager {
    private String fileName = "artatawe.db"; //database file name.
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
}
