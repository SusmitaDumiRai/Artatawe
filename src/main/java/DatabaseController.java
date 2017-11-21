import java.sql.*;

/**
 * Created by Susi on 21/11/2017.
 */
public class DatabaseController {

    private String connectionURL = "jdbc:ucanaccess://CinemaPalooza.mdb"; //location of the database.

    public String getConnectionURL() {
        return connectionURL;
    }

    public DatabaseController() {

    }




    public void setUpConnection() {
        try {
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Customer");
            try {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            } catch (Exception ex) {
                System.out.println("canot.");
            }
        } catch (Exception ex) {

        }
    }
}
