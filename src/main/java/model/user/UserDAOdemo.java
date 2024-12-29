package model.user;


import java.util.ArrayList;
import java.util.List;

public class UserDAOdemo extends UserDAO {

    private static UserDAOdemo instance = null;
    private static List<User> users = new ArrayList<User>();

    public static synchronized UserDAOdemo getInstance() {
        if(UserDAOdemo.instance == null)
            UserDAOdemo.instance = new UserDAOdemo();
        return UserDAOdemo.instance;
    }


    protected UserDAOdemo() {

    }

    public void saveUser(User order) {
        users.add(order);
    }

    public List<User> getAllUser() {
        return users;
    }



}