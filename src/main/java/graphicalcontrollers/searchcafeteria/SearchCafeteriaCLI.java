package graphicalcontrollers.searchcafeteria;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.SearchCafeteriaController;
import graphicalcontrollers.orderbuilder.OrderBuilderCLI;
import viewcli.SearchCafeteriaViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchCafeteriaCLI  implements SearchCafeteriaInterface {

    private SearchCafeteriaViewCli view;
    private SearchCafeteriaController controllerAppl;


    public void launch(){

        view = new SearchCafeteriaViewCli();
        controllerAppl = new SearchCafeteriaController();

        view.drawTitle();
        searchCafe();

    }

    public List<SearchCafeteriaBean> getCafeterias(){

        List<String> searchTypes = new ArrayList<>(Arrays.asList("name","address or city","get all"));
        int type = view.drawGetSearchType(searchTypes);
        List<SearchCafeteriaBean> cafeterias = new ArrayList<>();
        SearchCafeteriaBean bean;

        switch (type){
            case 0:
                System.out.println("insert name: ");
                bean = new SearchCafeteriaBean(view.getString(),null);
                cafeterias.add(controllerAppl.searchCafeterias(bean).getFirst());
                break;

            case 2:
                bean = new SearchCafeteriaBean(null,null);
                cafeterias = controllerAppl.searchCafeterias(bean);
                break;
            default:
        }

        return cafeterias;

    }

    public void searchCafe(){
        List<SearchCafeteriaBean> cafeterias = getCafeterias();

        if(cafeterias.size() == 1){
            //utente ha scelto caffetteria specifica
            showSelectedCafeteria(cafeterias.getFirst());
            return;
        }

        //devo mostrare più caffetterie all'utente
        List<String> cafes = new ArrayList<>();
        for (SearchCafeteriaBean cafeteria : cafeterias) {
            cafes.add(cafeteria.getName());
        }

        System.out.println("cafeterias found: ");
        view.showChoices(cafes);

        System.out.println("select a cafeteria: ");
        showSelectedCafeteria(cafeterias.get(view.getUserChoice(cafes)));
    }

    public void showSelectedCafeteria(SearchCafeteriaBean cafe){
        CafeteriaBean bean = controllerAppl.getCafeBeanByName(cafe.getName());
        List<String> items = new ArrayList<>(Arrays.asList(bean.getName(),bean.getCity(),bean.getAddress(),bean.getNumber(),bean.getDescription()));

        view.drawSelectedCafeteria(items);

        List<String> list = new ArrayList<>(Arrays.asList("continue order","find new cafeteria"));
        view.showChoices(list);
        int input = view.getUserChoice(list);

        if(input == 0) {
           new OrderBuilderCLI().launch(cafe.getName());
        }
        else{
            launch();
        }

    }


}
