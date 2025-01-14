package viewcli;

import engineering.ControllerSessionManager;
import graphicalcontrollers.home.HomeClientCLI;
import graphicalcontrollers.homebarista.HomeBaristaCLI;
import utils.UserLogged;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UtilsCli {

    public void showChoices(List<String> choices){

        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }

        System.out.println("");
    }

    //ritorna la scelta dell'utente come intero già allineato
    public int getUserChoice(List<String> choices){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int choice;
        do {
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            choice = Integer.parseInt(input);

            if(choice < 0 || choice > choices.size()){
                choice = -1;
                System.out.println("Please enter a valid choice");
            }

            if(choice == 0){
                ControllerSessionManager.getInstance().closeAllUserSessions();
                if(UserLogged.getInstance().getUser().getRole().equals("barista")) new HomeBaristaCLI().launch();
                new HomeClientCLI().launch();

            }

        }while(choice == -1);


        return choice-1;
    }

    public String getString(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showList(List<String> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
