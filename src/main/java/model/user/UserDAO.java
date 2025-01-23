package model.user;


import exception.SystemErrorException;
import model.cafeteria.Cafeteria;

import java.util.List;

public abstract class UserDAO {

    public Client createNewUserClient(String name, String password, String role){
        return new Client(name,password,role);
    }

    public Barista createNewUserBarista(String name, String password, String role){
        return new Barista(name, password,role);
    }

    public abstract void saveBarista(Barista user) throws SystemErrorException;

    public abstract void saveClient(Client user) throws SystemErrorException;

    public abstract List<User> getAllUserCredentials() throws SystemErrorException;

    public abstract void changeBaristaCafeteria(Barista barista, Cafeteria cafeteria) throws SystemErrorException;
}
