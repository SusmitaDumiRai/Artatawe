package co.uk.artatawe.main;

/**
 * Class that keeps track of who has favourited who.
 * User1 favourites user2.
 *
 * @author 908928
 * @author Plamena Tseneva
 */
public class FavouriteUsers {
    private User user1; //person who favourited.
    private User user2; //person being favourited.

    /**
     * Creates favourite users object.
     *
     * @param user1 person who favourited.
     * @param user2 person being favourited.
     */
    public FavouriteUsers(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Get person who favourited.
     *
     * @return user1
     */
    public User getUser1() {
        return user1;
    }


    /**
     * Get person being favourited.
     *
     * @return user2.
     */
    public User getUser2() {
        return user2;
    }

}