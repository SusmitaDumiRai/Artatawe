package co.uk.artatawe.database;


/**
 * Handles communication to favourite user table in database.
 * Allows creation, deletion and updates to be made to favourite user table.
 *
 * @author 908928.
 * @version 1.0
 */
public class FavouriteUserDatabaseManager extends DatabaseManager {
    /**
     * Empty constructor.
     */
    public FavouriteUserDatabaseManager() {

    }

    /**
     * Creates table in database if it does not exist.
     */
    public void createFavouriteUserTable() {
        String sqlCreateFavouriteUserTable = "CREATE TABLE IF NOT EXISTS favouriteUser(" +
                " username1 text not null,\n" +
                " username2 text not null, " +
                "primary key (username1, username2)," +
                "foreign key (username1) references user (username)," +
                "foreign key (username2) references user (username));";

        executeStatement(sqlCreateFavouriteUserTable);
    }
}
