package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.UserAccess;
import exception.NoCafeteriasFoundException;
import exception.NoUserFoundException;
import graphicalcontrollers.home.HomeClientCLI;
import graphicalcontrollers.homebarista.HomeBaristaCLI;
import model.DAOfactory;
import model.item.Beverage;
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


            Cafeteria caf1 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del cambridge","Tor vergata","12345677","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
            caf1.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,closed);
            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf1);

            try {

                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Americano", "real american coffee", 2, 123, 60, "/images/americano.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Cappuccino", "best cappuccio in town", 1.5, 123, 60, "/images/cappuccino.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Hot chocolate", "siuuum", 3, 123, 60, "/images/cioccolatacalda.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Creamy latte", "astuccio", 1, 123, 60, "/images/creamyLatte.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Espresso", "w il siiff", 1, 123, 60, "/images/espresso.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Frappuccino", "tottallu", 5, 123, 60, "/images/frappuccino.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Iced coffee", "aggiusta", 2, 123, 60, "/images/icedcoffee.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Irish coffee", "scrivi bene", 3.5, 123, 60, "/images/irishcoffee.jpg"), caf1.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Mocha coffee", "non ti dimenticare", 2, 123, 60, "/images/mocha.jpg"), caf1.getName());

            } catch (NoCafeteriasFoundException e) {
                e.showException();
            }



            Cafeteria caf2 = new Cafeteria("CafèDaRoccà","via casa mia","La rocca","063458741","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
            caf2.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,closed);

            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf2);

            try {

                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("cafuuuuu", "siuuum", 3, 123, 60, "/images/americano.jpg"), caf2.getName());
                DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("loooooool", "best cappuccio in town", 1.5, 123, 60, "/images/mocha.jpg"), caf2.getName());

            }catch (NoCafeteriasFoundException e) {
                e.showException();
            }

            System.out.println("aggiunti");
        }

}
