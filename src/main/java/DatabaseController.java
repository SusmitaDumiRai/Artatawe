import java.sql.*;

/**
 * Created by Susi on 21/11/2017.
 */
public class DatabaseController {

    public DatabaseController() {

    }

    public Connection getCon() {
        try {
            return DriverManager.getConnection("jdbc:ucanaccess://CinemaPalooza.mdb");
        } catch (SQLException ex) {
            System.out.println("Error occured.");
        }

        return new Connection();
    }

    public void setUpConnection() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://CinemaPalooza.mdb");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT Customer_Forename FROM Customer");
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