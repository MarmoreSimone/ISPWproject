package viewcli;

import java.util.List;

public class SearchCafeteriaViewCli extends UtilsCli{

    public int drawGetSearchType(List<String> choices){

        System.out.println("search by:");
        showChoices(choices);
        return getUserChoice(choices);

    }

    public void drawSelectedCafeteria(List<String> cafe){

        System.out.println("\nSELECTED CAFETERIA: ");
        showList(cafe);
        System.out.println("");

    }

    public void drawTitle(){
        System.out.println("====SELECT CAFETERIA====");
    }



}
