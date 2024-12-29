package utils;

import model.user.User;

public class UserLogged {

    private static UserLogged instance;
    private User user;

    private UserLogged() {

    }

    public static synchronized UserLogged getInstance() {
        if (instance == null) {
            instance = new UserLogged();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}