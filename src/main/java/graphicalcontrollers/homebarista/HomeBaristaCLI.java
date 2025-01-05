package graphicalcontrollers.homebarista;

import graphicalcontrollers.login.LoginCLI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeBaristaCLI extends viewcli.UtilsCli{

    public void launch(){

        System.out.println("====MENU====");
        List<String> options = new ArrayList<>(Arrays.asList("process orders","manage orders", "manage cafeteria","logout"));
        showChoices(options);
        int choice = getUserChoice(options);

        switch (choice) {
            case 0:
                new SearchCafeteriaCLI().launch();
                break;
            case 1:
                System.out.println("Hai scelto l'opzione 2.");
                break;
            case 2:
                System.out.println("Hai scelto l'opzione 3.");
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
