package model.user;


import java.util.List;

public abstract class UserDAO {

    public User createNewUser(){
        return new User();
    }

    public abstract void saveUser(User user);

    public abstract List<User> getAllUser();
}
