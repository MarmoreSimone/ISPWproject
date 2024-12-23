package viewcli;

import graphicalcontrollers.searchcafeteria.DummyCLI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenuCLI extends UtilsCli{

    public void start(){

            System.out.println("====MENU====");
            List<String> options = new ArrayList<>(Arrays.asList("place order","my orders", "gift coupon","profile","test","login"));
            showChoices(options);
            String choice = getUserChoice(options);

            switch (choice) {
                case "1":
                    new SearchCafeteriaCLI().launch();
                    break;
                case "2":
                    System.out.println("Hai scelto l'opzione 2.");
                    break;
                case "3":
                    System.out.println("Hai scelto l'opzione 3.");
                    break;
                case "4":
                    System.out.println("Hai scelto l'opzione 4.");
                    break;
                case "5":
                    new DummyCLI().launch();
                    break;
                case "6":
                    System.out.println("opzione 6");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

            System.out.println(); // Linea vuota per separare il menu

    }
}



