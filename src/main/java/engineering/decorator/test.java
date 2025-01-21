package engineering.decorator;


import model.DAOfactory;
import model.MenuItem.MenuItem;
import model.cafeteria.Cafeteria;

public class test {

    public static void main(String[] args){

        String orario1 = "08:00 - 16:00";
        String closed = "closed";
        String beverage = "beverage";
        String topping = "toppings";

        DAOfactory.setDAOfactory(1);

        Cafeteria caf1 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del cambridge","Tor vergata","12345677","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
        caf1.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,closed);
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf1);

        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Americano", "real american coffee", 2, 123, 60, "/images/americano.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Cappuccino", "best cappuccio in town", 1.5, 123, 60, "/images/cappuccino.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Hot chocolate", "siuuum", 3, 123, 60, "/images/cioccolatacalda.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Creamy latte", "astuccio", 1, 123, 60, "/images/creamyLatte.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Espresso", "w il siiff", 1, 123, 60, "/images/espresso.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Frappuccino", "tottallu", 5, 123, 60, "/images/frappuccino.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Iced coffee", "aggiusta", 2, 123, 60, "/images/icedcoffee.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Irish coffee", "scrivi bene", 3.5, 123, 60, "/images/irishcoffee.jpg",beverage), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Mocha coffee", "non ti dimenticare", 2, 123, 60, "/images/mocha.jpg",beverage), caf1);

        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Smarties", null, 0.5, 50, 0, null,topping), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("panna", null, 0.4, 40, 0, null,topping), caf1);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("zuccherini", null, 0.1, 30, 0, null,topping), caf1);

        MenuItemComponent bev = new MenuItem("Americano", "real american coffee", 2, 123, 40, "/images/americano.jpg","beverage");
        RunToBurn run = new RunToBurn(bev);
        run.setWeight(70.0);
        CaffeineProcessingTime caff = new CaffeineProcessingTime(run);
        caff.setWeight(60.0);
        Toppings topp = new Toppings(caff,caf1);
        topp.applyTopping("zuccherini");

        Toppings topp2 = new Toppings(topp,caf1);
        topp2.applyTopping("Smarties");

        CoffeeShots shots = new CoffeeShots(topp2);
        shots.setShots(2);

        System.out.println(shots.getName());

    }
}
