package viewCLI;

import java.io.IOException;
import java.util.List;

public class SearchCafeteriaViewCli extends UtilsCli{

    public String drawGetSearchType(List<String> choices){

        System.out.println("search by:");
        showChoices(choices);
        return getUserChoice(choices);

    }
}
