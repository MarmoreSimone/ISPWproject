package model.user;


import model.cafeteria.Cafeteria;

import java.util.List;

public abstract class UserDAO {

    public Client createNewUserClient(String name, String password, String role){
        return new Client(name,password,role);
    }

    public Barista createNewUserBarista(String name, String password, String role){
        return new Barista(name, password,role);
    }

    public abstract void saveBarista(Barista user);

    public abstract void saveClient(Client user);

    public abstract List<User> getAllUserCredentials();

    public abstract void changeBaristaCafeteria(Barista barista, Cafeteria cafeteria);
}
