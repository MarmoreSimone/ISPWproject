package model.user;


import model.cafeteria.Cafeteria;

import java.util.ArrayList;
import java.util.List;

public class UserDAOdemo extends UserDAO {

    private static UserDAOdemo instance = null;

    private static List<Client> clients = new ArrayList<Client>();
    private static List<Barista> baristas = new ArrayList<Barista>();


    public static synchronized UserDAOdemo getInstance() {
        if(UserDAOdemo.instance == null)
            UserDAOdemo.instance = new UserDAOdemo();
        return UserDAOdemo.instance;
    }


    protected UserDAOdemo() {

    }

    public void saveBarista(Barista user){
        baristas.add(user);
    }

    public void saveClient(Client user){
        clients.add(user);
    }

    //la lista di elementi tornata è tutta di tipo User ma ad ogni elemento è associata una istanza di tipo Client/Barista
    //cosi poi da essere tranquillamente ricastate al rispettivo tipo senza perdere niente
    public List<User> getAllUserCredentials() {

        List<User> users = new ArrayList<>();

        users.addAll(clients);
        users.addAll(baristas);

        return users;

    }

    public void changeBaristaCafeteria(Barista barista, Cafeteria cafeteria){

        barista.setCafeteria(cafeteria);

    }


}