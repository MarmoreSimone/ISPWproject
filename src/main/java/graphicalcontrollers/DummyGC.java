package graphicalcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.DAOfactory;
import model.beverage.Beverage;
import model.cafeteria.Cafeteria;

public class DummyGC {


    public void insertCafe(){
        String orario1 = "08:00 - 16:00";

        Cafeteria caf1 = new Cafeteria("bar di ingegneria","via del cambridge","Tor vergata","12345677","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
        caf1.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,"closed");
        caf1.setBeverages(new Beverage("Americano","real american coffee", 2, 123, 60,"/images/americano.jpg"));
        caf1.setBeverages(new Beverage("Cappuccino","best cappuccio in town", 1.5, 123, 60,"/images/cappuccino.jpg"));
        caf1.setBeverages(new Beverage("Hot chocolate","siuuum", 3, 123, 60,"/images/cioccolatacalda.jpg"));
        caf1.setBeverages(new Beverage("Creamy latte","robaccia starbucks", 1, 123, 60,"/images/creamyLatte.jpg"));
        caf1.setBeverages(new Beverage("Espresso","robaccia starbucks", 1, 123, 60,"/images/espresso.jpg"));
        caf1.setBeverages(new Beverage("Frappuccino","robaccia starbucks", 5, 123, 60,"/images/frappuccino.jpg"));
        caf1.setBeverages(new Beverage("Iced coffee","robaccia starbucks", 2, 123, 60,"/images/icedcoffee.jpg"));
        caf1.setBeverages(new Beverage("Irish coffee","robaccia starbucks", 3.5, 123, 60,"/images/irishcoffee.jpg"));
        caf1.setBeverages(new Beverage("Mocha coffee","robaccia starbucks", 2, 123, 60,"/images/mocha.jpg"));

        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf1);

        String orario2 = " 12:00 - 16:00";
        Cafeteria caf2 = new Cafeteria("bar di medicina","via del signore","roma","2312412","Una caffetteria moderna che unisce tradizione e innovazione. Specializzata in miscele uniche di caffè, con un ambiente elegante e comodi posti a sedere. Perfetto per lavorare, studiare o godersi un momento di relax.","/images/cafe2.jpg");
        caf2.setOpeningHours(orario2 ,orario2,orario2,orario2,orario2,"closed","closed");
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf2);

        String orario3 = " 06:00 - 20:00";
        Cafeteria caf3 = new Cafeteria("bar di lettere","via del cammino","ciampino","5685868","Caffetteria di quartiere che ti fa sentire a casa, con un’atmosfera calorosa e informale. Serviamo caffè di alta qualità, tè aromatizzati e snack deliziosi, accompagnati da un sorriso e un servizio rapido.","/images/cafe3.jpg");
        caf3.setOpeningHours("closed",orario3,"closed",orario3,orario3,orario3,orario3);
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf3);

    }





}
