package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by srai4 on 24/11/2017.
 */
public class SQLiteTest {
    private String fileName = "artatawe.db";

    public void createDatabase() {
        String url = "jdbc:sqlite:C:\\Users\\srai4\\Documents\\University\\CS-230\\" + fileName;

        try {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("New database has been created.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
