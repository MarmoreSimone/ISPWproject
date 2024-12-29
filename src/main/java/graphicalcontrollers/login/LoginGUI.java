package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.UserAccess;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.home.HomeGUI;
import graphicalcontrollers.homebarista.HomeBaristaGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utils.SwitchPage;


public class LoginGUI extends GraphicalController {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

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
        contrAppl.register(new UserBean("pal","2","barista"));

        System.out.println("aggiunti");
    }

}
