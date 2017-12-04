package co.uk.artatawe.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles communication to user table in database.
 * Allows creation, deletion and updates to be made to artwork table.
 *
 * @author 908928
 * @version 1.0
 */
public class ArtworkDatabaseManager extends DatabaseManager {

    /**
     * Empty constructor.
     */
    public ArtworkDatabaseManager() {
    }



    /**
     * Creates table in database if it does not exist.
     */
    public void createArtworkTable() {
        String sqlCreateArtworkTable = "CREATE TABLE IF NOT EXISTS Artwork( artworkID INTEGER PRIMARY KEY not null,\n" +
                "title text not null," +
                " description text," +
                " photo text not null," +
                " nameOfCreator text not null," +
                " reservedPrice real not null," +
                " dateEntered text not null," +
                " bidsAllowed INTEGER not null," +
                " typeOfArtwork text not null," +
                " width real not null," +
                " height real not null," +
                "depth real," +
                "mainMaterial text," + "extraPhotos text);";

        executeStatement(sqlCreateArtworkTable);

    }


    /**
     * Gets all artwork info.
     */

    public void getAllArtworks() {
        String sqlSelect = "SELECT artworkid," +
                "title," +
                "description," +
                "photo," +
                "nameOfCreator," +
                "reservedPrice," +
                "dateEntered," +
                "bidsAllowed," +
                "typeOfArtwork," +
                "width," +
                "height," +
                "depth," +
                "mainmaterial," +
                "extraphotos" +
                " FROM artwork;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("artworkid") + "\t" +
                        resultSet.getString("title") + "\t" +
                        resultSet.getString("description") + "\t" +
                        resultSet.getString("photo") + "\t" +
                        resultSet.getString("nameOfCreator") + "\t" +
                        resultSet.getDouble("reservedPrice") + "\t" +
                        resultSet.getString("dateEntered") + "\t" +
                        resultSet.getInt("bidsAllowed") + "\t" +
                        resultSet.getString("typeOfArtwork") + "\t" +
                        resultSet.getDouble("width") + "\t" +
                        resultSet.getDouble("height") + "\t" +
                        resultSet.getDouble("depth") + "\t" +
                        resultSet.getString("mainmaterial") + "\t" +
                        resultSet.getString("extraphotos") + "\t");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    /**
     * Get artwork ID for artwork.
     * @param title artwork to be searched.
     * @return artwork ID for artwork.
     */
    public int getArtworkID(String title) {
        String sqlSelectAuction = "SELECT artworkID where title = '" + title + "';";
        int artworkID = -1;
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlSelectAuction);
            while (resultSet.next()) {
                artworkID = resultSet.getInt("artworkID");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return artworkID;
    }


}
