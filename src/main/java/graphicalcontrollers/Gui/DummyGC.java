package graphicalcontrollers.Gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.DAOfactory;
import model.cafeteria.Cafeteria;

public class DummyGC {

    @FXML
    private Button addCafe;

    public void insertCafe(){

        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(new Cafeteria("bar di ingegnieria","via del cambridge","Tor vergata","12345677","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto, con uno staff sempre sorridente e disponibile."));
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(new Cafeteria("bar di medicina","via del signore","roma","2312412","Una caffetteria moderna che unisce tradizione e innovazione. Specializzata in miscele uniche di caffè, con un ambiente elegante e comodi posti a sedere. Perfetto per lavorare, studiare o godersi un momento di relax."));
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(new Cafeteria("bar di lettere","via del cammino","ciampino","5685868","Caffetteria di quartiere che ti fa sentire a casa, con un’atmosfera calorosa e informale. Serviamo caffè di alta qualità, tè aromatizzati e snack deliziosi, accompagnati da un sorriso e un servizio rapido."));

    }





}