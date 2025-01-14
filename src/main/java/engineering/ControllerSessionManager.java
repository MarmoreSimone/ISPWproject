package engineering;

import utils.UserLogged;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControllerSessionManager {

    private static ControllerSessionManager instance = null;
    private int key;
    HashMap<String, PlaceOrderSession> placeOrderSessions = new HashMap<>();

    //la uso se dovessi realizzare un caso d'uso che possa essere interrotto a metà per poi essere ripreso(faccio logout e poi rientro), così da mantenere il riferimento
    //alla sessione di uno specifico utente, normalmente invece all'inizio del caso d'uso creo la sessione prendo la chiave e la passo fino alla fine del caso d'uso
    HashMap<String, List<String>> userSessions = new HashMap<>();


    protected ControllerSessionManager() {
        key = 1;
        userSessions.putIfAbsent(UserLogged.getInstance().getUser().getUsername(), new ArrayList<>());
    }

    private String getNewKey(){
        String id = String.valueOf(key);
        key++;
        return id;
    }


    public String newPlaceOrderSession() {
        PlaceOrderSession session = new PlaceOrderSession();
        String myKey = getNewKey();
        userSessions.get(UserLogged.getInstance().getUser().getUsername()).add(myKey);
        placeOrderSessions.put(myKey, session);
        return myKey;
    }


    public PlaceOrderSession getPlaceOrderSession(String key) {
        return placeOrderSessions.get(key);
    }


    public void delPlaceOrderSession(String key) {
        placeOrderSessions.remove(key);

    }

    //usato quando mi sposto con il menu e termino prima della fine il caso d'uso
    public void closeAllUserSessions() {

        //prendo tutte le sessioni associate ad un utente
        List<String> sessions = userSessions.get(UserLogged.getInstance().getUser().getUsername());

        if(!sessions.isEmpty()) {

            //fare attenzione nel caso si aggiungessero casi d'uso che possono essere lasciati a metà
            placeOrderSessions.remove(sessions.getFirst());
        }

        userSessions.put(UserLogged.getInstance().getUser().getUsername(), new ArrayList<>());
    }


    public static synchronized  ControllerSessionManager getInstance() {
        if (ControllerSessionManager.instance == null)
            ControllerSessionManager.instance = new ControllerSessionManager();
        return instance;
    }

}
