package graphicalcontrollers.login;

import bean.CredentialsBean;
import bean.UserBean;
import controller.UserAccess;
import graphicalcontrollers.home.HomeGUI;
import graphicalcontrollers.homebarista.HomeBaristaGUI;
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

        view.showChoices(new ArrayList<>(Arrays.asList("login","register")));
        String input = view.getUserChoice(new ArrayList<>(Arrays.asList("login","register")));

        int choice = Integer.parseInt(input);

        switch(choice){
            case 1:
               login();
               break;

            default:
                break;

        }



    }


    public void login(){
        CredentialsBean cred = view.drawGetCredentials();
        UserBean user = userAccess.login(cred);

        if(user.getRole().equals("barista")) {
            new HomeBaristaGUI().launch();
        }
        else {
            new HomeGUI().launch();
        }

    }

}
