package graphicalcontrollers.home;

import graphicalcontrollers.login.LoginCLI;
import graphicalcontrollers.myorders.MyOrdersCLI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeClientCLI extends viewcli.UtilsCli{

    public void launch(){

        System.out.println("====MENU====");
        List<String> options = new ArrayList<>(Arrays.asList("place order","my orders", "gift coupon","profile", "mytest","logout"));
        showChoices(options);
        int choice = getUserChoice(options);

        switch (choice) {
            case 0:
                new SearchCafeteriaCLI().launch();
                break;
            case 1:
                new MyOrdersCLI().launch();
                break;
            case 2:
                System.out.println("Hai scelto l'opzione 3.");
                break;
            case 3:
                System.out.println("Hai scelto l'opzione 4.");
                break;
            case 4:
                System.out.println("Hai scelto l'opzione 5.");
                break;
            case 5:
                new LoginCLI().launch();
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }

        System.out.println(); // Linea vuota per separare il menu

    }



}
