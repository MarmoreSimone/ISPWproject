package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.UserAccess;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.home.HomeGUI;
import graphicalcontrollers.homebarista.HomeBaristaGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.DAOfactory;
import model.beverage.Beverage;
import model.cafeteria.Cafeteria;
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

        CredentialsBean cred = new CredentialsBean(username.getText(),password.getText());
        UserAccess contrAppl = new UserAccess();
        UserBean user = contrAppl.login(cred);

        if(user.getRole().equals("barista")) {
            new HomeBaristaGUI().launch();
        }
        else {
            new HomeGUI().launch();
        }

    }

    public void registerNewUser(){
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


        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Americano","real american coffee", 2, 123, 60,"/images/americano.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Cappuccino","best cappuccio in town", 1.5, 123, 60,"/images/cappuccino.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Hot chocolate","siuuum", 3, 123, 60,"/images/cioccolatacalda.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Creamy latte","astuccio", 1, 123, 60,"/images/creamyLatte.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Espresso","w il siiff", 1, 123, 60,"/images/espresso.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Frappuccino","tottallu", 5, 123, 60,"/images/frappuccino.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Iced coffee","aggiusta", 2, 123, 60,"/images/icedcoffee.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Irish coffee","scrivi bene", 3.5, 123, 60,"/images/irishcoffee.jpg"), caf1.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("Mocha coffee","non ti dimenticare", 2, 123, 60,"/images/mocha.jpg"), caf1.getName());




        Cafeteria caf2 = new Cafeteria("CafèDaRoccà","via casa mia","La rocca","063458741","Un angolo accogliente nel cuore della città, ideale per una pausa rilassante o una chiacchierata tra amici. Offre una selezione di caffè artigianali, dolci fatti in casa e opzioni per ogni gusto.","/images/baring.jpg");
        caf2.setOpeningHours(orario1,orario1,orario1,orario1,orario1,orario1,closed);

        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(caf2);


        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("cafuuuuu","siuuum", 3, 123, 60,"/images/americano.jpg"), caf2.getName());
        DAOfactory.getDAOfactory().createBeverageDAO().saveBev(new Beverage("loooooool","best cappuccio in town", 1.5, 123, 60,"/images/mocha.jpg"), caf2.getName());




        System.out.println("aggiunti");
    }

}
