package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.LoginController;
import exception.NoUserFoundException;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.home.HomeGUI;
import graphicalcontrollers.homebarista.HomeBaristaGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.DAOfactory;
import model.menuitem.MenuItem;
import model.cafeteria.Cafeteria;
import model.user.Barista;
import model.user.User;
import utils.SwitchPage;

import java.util.List;


public class LoginGUI extends GraphicalController {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @Override
    public void launch(){
        SwitchPage.getSwitchPageInstance().changePage("/view/login.fxml");
    }

    public void login() {

        UserBean user;
        try {

            CredentialsBean cred = new CredentialsBean(username.getText(), password.getText());
            LoginController contrAppl = new LoginController();
            user = contrAppl.login(cred);

        }catch(NoUserFoundException e) {
            e.showException();
            return;
        }

        if(user.getRole().equals("barista")) {
            new HomeBaristaGUI().launch();
        }
        else {
            new HomeGUI().launch();
        }

    }

    public void registerNewUser(){

        LoginController contrAppl = new LoginController();

        String clie = "client";
        String bari = "barista";

        contrAppl.register(new UserBean("sim","1",clie));
        contrAppl.register(new UserBean("sim2","11",clie));

        contrAppl.register(new UserBean("pal","2",bari));
        contrAppl.register(new UserBean("pal2","22",bari));


        String beverage = "beverage";
        String topping = "toppings";

        Cafeteria caf1 = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del cambridge","Tor vergata","12345677","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
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








            Cafeteria caf2 = new Cafeteria("CafèDaRoccà","via casa mia","La rocca","063458741","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/cafe2.jpg");

            DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf2);

            DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("cafuuuuu", "siuuum", 3, 123, 60, "/images/americano.jpg",beverage), caf2);
            DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(new MenuItem("loooooool", "best cappuccio in town", 1.5, 123, 60, "/images/mocha.jpg",beverage), caf2);


        List<User> users = DAOfactory.getDAOfactory().createUserDAO().getAllUserCredentials();

        for(int i=0;i<users.size();i++){

            if(users.get(i).getUsername().equals("pal")){
                Barista barista = (Barista) users.get(i);
                barista.setCafeteria(caf1);
                DAOfactory.getDAOfactory().createUserDAO().changeBaristaCafeteria(barista,caf1);
            }
            else if(users.get(i).getUsername().equals("pal2")){
                Barista barista = (Barista) users.get(i);
                barista.setCafeteria(caf2);
                DAOfactory.getDAOfactory().createUserDAO().changeBaristaCafeteria(barista,caf2);
            }
        }


        System.out.println("aggiunti");



    }

}
