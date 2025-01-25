package graphicalcontrollers.homebarista;

import graphicalcontrollers.login.LoginCLI;
import graphicalcontrollers.processorders.ProcessOrdersCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeBaristaCLI extends viewcli.UtilsCli{

    public void launch(){

        System.out.println("====MENU====");
        List<String> options = new ArrayList<>(Arrays.asList("process orders","manage orders(NOT AVAILABLE)", "manage cafeteria(NOT AVAILABLE)","logout"));
        showChoices(options);
        int choice = getUserChoice(options);

        switch (choice) {
            case 0, 2, 1:
                new ProcessOrdersCLI().launch();
                break;

            case 3:
                new LoginCLI().launch();
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }

        System.out.println(); // Linea vuota per separare il menu

    }
}
