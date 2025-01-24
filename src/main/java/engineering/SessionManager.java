package engineering;

import model.user.Barista;
import model.user.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionManager {

    private static SessionManager instance = null;

    private Client userClient;
    private Barista userBarista;

    private int key;

    //hasmap che contiene tutte le coppie (key,istanza) di sessioni relative al caso d'uso place order
    HashMap<String, PlaceOrderSession> placeOrderSessions = new HashMap<>();

    //hashmap che contiene le coppie (username,List<key>) per tenere traccia di tutte le sessioni associati agli utenti
    HashMap<String, List<String>> userSessions = new HashMap<>();

    protected SessionManager() {
        key = 1;
    }

    private String getNewKey(){
        String id = String.valueOf(key);
        key++;
        return id;
    }

    public String newPlaceOrderSession() {

        PlaceOrderSession session = new PlaceOrderSession();
        String myKey = getNewKey();

        //hash map (utente,List<key>)
        userSessions.get(getCurrentUserUsername()).add(myKey);

        //hash map (key,istanza)
        placeOrderSessions.put(myKey, session);
        return myKey;

    }

    //nel caso si aggiungessero altri tipi di sessione, le 2 op. getSess e delSess si possono rendere generali in quanto
    //il parametro passato, una stringa, è univoca per tutti i tipi di sessione, quindi basta "ciclare" tra le varie hashmap
    //finchè non si trova quella da aggiungere/eliminare
    public PlaceOrderSession getPlaceOrderSession(String key) {
        return placeOrderSessions.get(key);
    }

    public void delPlaceOrderSession(String key) {
        placeOrderSessions.remove(key);
    }

    //usato quando mi sposto con il menu e termino prima della fine il caso d'uso
    //in questo caso non posso avere casi d'uso lasciati a metà quindi chiudo direttamente tutte le eventuali sessioni aperte dall'utente
    public void closeAllUserSessions() {

        //prendo tutte le sessioni associate a un utente
        List<String> sessions = userSessions.get(getCurrentUserUsername());

        if(!sessions.isEmpty()) {
            //fare attenzione nel caso si aggiungessero casi d'uso che possono essere lasciati a metà e quindi posso avere contemporaneamente più sessioni per lo stesso caso d'uso
            placeOrderSessions.remove(sessions.getFirst());
        }

        userSessions.put(getCurrentUserUsername(), new ArrayList<>());
    }

    public void setUserBarista(Barista barista){
        this.userBarista = barista;
        if (!userSessions.containsKey(barista.getUsername())) {
            userSessions.put(barista.getUsername(), new ArrayList<>());
        }
        this.userClient = null;
    }

    public void setUserClient(Client client){
        this.userClient = client;
        if (!userSessions.containsKey(client.getUsername())){
            userSessions.put(client.getUsername(), new ArrayList<>());
        }
        this.userBarista = null;
    }

    public Barista getUserBaristaLogged() {
       return this.userBarista;
    }

    public Client getUserClientLogged(){
        return this.userClient;
    }

    private String getCurrentUserUsername(){
        if(this.userBarista != null) return this.userBarista.getUsername();
        else return this.userClient.getUsername();
    }

    public String getCurrentUserUserRole(){
        if(this.userBarista != null) return this.userBarista.getRole();
        else return this.userClient.getRole();
    }

    public static synchronized SessionManager getInstance() {
        if (SessionManager.instance == null)
            SessionManager.instance = new SessionManager();
        return instance;
    }

}
