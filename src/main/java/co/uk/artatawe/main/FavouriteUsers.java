package co.uk.artatawe.main;

/**
 * Created by Plamena on 7.12.2017 г..TODO
 * @author 908928
 * @author Plamena Tseneva
 */
public class FavouriteUsers {
    private User user1;
    private User user2;

    public FavouriteUsers(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}