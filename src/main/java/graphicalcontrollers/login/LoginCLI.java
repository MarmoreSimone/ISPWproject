package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.LoginController;
import exception.NoUserFoundException;
import exception.SystemErrorException;
import graphicalcontrollers.home.HomeClientCLI;
import graphicalcontrollers.homebarista.HomeBaristaCLI;
import viewcli.LoginViewCli;
import viewcli.UtilsCli;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginCLI extends UtilsCli {

    LoginViewCli view;
    LoginController controllerAppl;

    public void launch(){
        view = new LoginViewCli();
        controllerAppl = new LoginController();

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
            user = controllerAppl.login(cred);

        }catch (NoUserFoundException | SystemErrorException e){
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
        CredentialsBean cred = view.drawGetCredentials();
        try {
            controllerAppl.register(new UserBean(cred.getUsername(), cred.getPassword(),"client"));
        } catch (SystemErrorException e) {
            e.showException();
        }

    }


}
