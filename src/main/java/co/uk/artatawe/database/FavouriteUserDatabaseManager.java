package co.uk.artatawe.database;


/**
 * Created by Susi on 04/12/2017.
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
