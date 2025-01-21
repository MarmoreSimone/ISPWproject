package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.UserAccess;
import exception.NoCafeteriasFoundException;
import exception.NoUserFoundException;
import graphicalcontrollers.home.HomeClientCLI;
import graphicalcontrollers.homebarista.HomeBaristaCLI;
import model.DAOfactory;
import model.MenuItem.MenuItem;
import model.cafeteria.Cafeteria;
import viewcli.LoginViewCli;
import viewcli.UtilsCli;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginCLI extends UtilsCli {

    LoginViewCli view;
    UserAccess userAccess;

    public void launch(){
        view = new LoginViewCli();
        userAccess = new UserAccess();

        while(true) {
            view.showChoices(new ArrayList<>(Arrays.asList("login", "register")));
            int choice = view.getUserChoice(new ArrayList<>(Arrays.asList("login", "register")));

            switch (choice) {
                case 0:
                    login();
                    break;

                case 1:
                    register();
                    break;

                default:
                    break;
            }

        }

    }


    public void login(){

        UserBean user = null;

        try {
            CredentialsBean cred = view.drawGetCredentials();
            user = userAccess.login(cred);

        }catch (NoUserFoundException e){
            e.showException();
            return;
        }

        if(user.getRole().equals("barista")) {
            new HomeBaristaCLI().launch();
        }
        else {
            new HomeClientCLI().launch();
        }

    }

    public void register(){
            UserAccess contrAppl = new UserAccess();

            contrAppl.register(new UserBean("sim","1","client"));
            contrAppl.register(new UserBean("sim2","11","client"));
            contrAppl.register(new UserBean("pal","2","bar di ingegneria"));
            contrAppl.register(new UserBean("pal2","22","CafèDaRoccà"));

            String orario1 = "08:00 - 16:00";
            String closed = "closed";
            String beverage = "beverage";
            String topping = "topping";

            Cafeteria caf1 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del cambridge","Tor vergata","12345677","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
            caf1.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,closed);
            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf1);

                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Americano", "real american coffee", 2, 123, 60, "/images/americano.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Cappuccino", "best cappuccio in town", 1.5, 123, 60, "/images/cappuccino.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Hot chocolate", "siuuum", 3, 123, 60, "/images/cioccolatacalda.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Creamy latte", "astuccio", 1, 123, 60, "/images/creamyLatte.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Espresso", "w il siiff", 1, 123, 60, "/images/espresso.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Frappuccino", "tottallu", 5, 123, 60, "/images/frappuccino.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Iced coffee", "aggiusta", 2, 123, 60, "/images/icedcoffee.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Irish coffee", "scrivi bene", 3.5, 123, 60, "/images/irishcoffee.jpg", beverage), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Mocha coffee", "non ti dimenticare", 2, 123, 60, "/images/mocha.jpg", beverage), caf1);

                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("Smarties", null, 0.5, 50, 0, null,topping), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("panna", null, 0.4, 40, 0, null,topping), caf1);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("zuccherini", null, 0.1, 30, 0, null,topping), caf1);

            Cafeteria caf2 = new Cafeteria("CafèDaRoccà","via casa mia","La rocca","063458741","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
            caf2.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,closed);

            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf2);

                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("cafuuuuu", "siuuum", 3, 123, 60, "/images/americano.jpg", beverage), caf2);
                DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("loooooool", "best cappuccio in town", 1.5, 123, 60, "/images/mocha.jpg", beverage), caf2);

            System.out.println("aggiunti");
        }

}
