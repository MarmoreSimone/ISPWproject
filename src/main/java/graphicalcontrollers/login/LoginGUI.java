package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.LoginController;
import exception.NoUserFoundException;
import exception.SystemErrorException;
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

    @FXML
    private TextField passReg;

    @FXML
    private TextField userReg;

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

        }catch(NoUserFoundException | SystemErrorException e) {
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

    public void register(){

        LoginController contrAppl = new LoginController();
        try {
            contrAppl.register(new UserBean(userReg.getText(),passReg.getText(),"client"));
        } catch (SystemErrorException e) {
            e.showException();
        }

        SwitchPage.getSwitchPageInstance().changePage("/view/login.fxml");
    }

    public void goToRegister(){
        SwitchPage.getSwitchPageInstance().changePage("/view/register.fxml");
    }

    public void goBack(){
        new LoginGUI().launch();
    }

}
