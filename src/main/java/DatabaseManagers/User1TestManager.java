package DatabaseManagers;

import java.sql.*;

/**
 * This is a dummy test class for database.
 * Inserts into dummy table - USER1 in database.
 * Also updates table.
 *
 * @author Susmita Rai
 * @version 1.0
 */
public class User1TestManager {
    private String databaseLocation = "jdbc:ucanaccess://Artatawe.accdb";

    public User1TestManager() {


    }

    public void insertIntoUser1() {
        try {
            Connection connection = DriverManager.getConnection(databaseLocation);
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO user1 values('username', 'firstname', 'surname', '12345678900', 'address', 'SA12DN', 'lastlogin', 'profileimage')");
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateIntoUser1() {
        try {
            Connection connection = DriverManager.getConnection(databaseLocation);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE user1 set firstname = 'notfirstname' where username = 'username'");
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
