package utils;

import bean.UserBean;
import controller.LoginController;
import exception.SystemErrorException;
import model.DAOfactory;
import model.DAOfactoryDB;
import model.DAOfactoryFS;
import model.cafeteria.Cafeteria;
import model.menuitem.MenuItem;
import model.user.Barista;
import model.user.User;

import java.util.List;

public class Dummy {

        private Dummy(){

        }

        public static void dummyRegistration(){

        if((DAOfactory.getDAOfactory() instanceof DAOfactoryFS || DAOfactory.getDAOfactory() instanceof DAOfactoryDB)) return;

        LoginController contrAppl = new LoginController();
        String clie = "client";
        String bari = "barista";
        String beverage = "beverage";
        String topping = "toppings";
        Cafeteria caf1 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del politecnico, 1","Roma","067259759","The Bar of Engineering at Tor Vergata University offers a variety of drinks, snacks, and quick meals. It's a casual spot where students and staff can relax between classes, grab a coffee, or have a bite to eat","/images/baring.jpg");
        Cafeteria caf2 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("Central Café","Via della Moscova","Milano","067257899","Central Café offers a wide range of drinks, pastries, and light bites. It’s a cozy place where customers can unwind, enjoy a coffee, and grab a quick snack. Perfect for a break during the day","/images/cafe2.jpg");
        Cafeteria caf3 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("Bean & Brew Café","Via San Gregorio Armeno","Napoli","067259744","Bean & Brew Café serves freshly brewed coffee, pastries, and light snacks. It’s a welcoming spot to relax, catch up with friends, or enjoy a quiet moment with your favorite drink","/images/cafe3.jpg");

        try {

            contrAppl.register(new UserBean("cliente1","1",clie));
            contrAppl.register(new UserBean("barista1","2",bari));
            contrAppl.register(new UserBean("barista2","2",bari));

            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf1);
            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf2);
            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf3);


            if(!(DAOfactory.getDAOfactory() instanceof DAOfactoryFS)) {
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Americano", "Strong black coffee diluted with hot water", 2, 30, 95, "/images/americano.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Cappuccino", "Espresso with steamed milk and foam", 1.8, 100, 75, "/images/cappuccino.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Hot chocolate", "Warm drink made with chocolate and milk", 3, 250, 0, "/images/cioccolatacalda.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Creamy latte", "Smooth latte with a creamy texture", 2.8, 140, 80, "/images/creamyLatte.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Espresso", "Strong and concentrated coffee shot", 1, 5, 63, "/images/espresso.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Frappuccino", "Iced coffee blended with milk and syrup", 4.5, 300, 75, "/images/frappuccino.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Iced coffee", "Cold brewed coffee, refreshing for summer", 3, 50, 95, "/images/icedcoffee.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Irish coffee", "Coffee with irish whiskey and cream", 5, 250, 95, "/images/irishcoffee.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Mocha coffee", "Espresso with chocolate and steamed milk", 4.5, 200, 95, "/images/mocha.jpg", beverage), caf1);

                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("smarties", null, 0.5, 40, 0, null, topping), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("whipped cream", null, 0.4, 50, 0, null, topping), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("sugar sprinkles", null, 0.3, 20, 0, null, topping), caf1);


                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("americanoSpecial", "americano special", 3, 123, 60, "/images/americano.jpg", beverage), caf2);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("mochaSpecial", "mocha special", 1.5, 123, 60, "/images/mocha.jpg", beverage), caf2);

            }


            List<User> users = DAOfactory.getDAOfactory().createUserDAO().getAllUserCredentials();


            for(int i=0;i<users.size();i++){

                if(users.get(i).getUsername().equals("barista1")){
                    Barista barista = (Barista) users.get(i);
                    DAOfactory.getDAOfactory().createUserDAO().changeBaristaCafeteria(barista,caf1);
                }
                else if(users.get(i).getUsername().equals("barista2")){
                    Barista barista = (Barista) users.get(i);
                    DAOfactory.getDAOfactory().createUserDAO().changeBaristaCafeteria(barista,caf2);
                }
            }



        } catch (SystemErrorException e) {
            e.showException();
        }

        }
}
