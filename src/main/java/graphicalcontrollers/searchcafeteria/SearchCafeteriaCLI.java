package graphicalcontrollers.searchcafeteria;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import controller.SearchCafeteria;
import graphicalcontrollers.GraphicalController;
import viewcli.SearchCafeteriaViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchCafeteriaCLI extends GraphicalController implements SearchCafeteriaInterface {

    private SearchCafeteriaViewCli view;
    private SearchCafeteria controllerAppl;

    @Override
    public void launch(){
        view = new SearchCafeteriaViewCli();
        controllerAppl = new SearchCafeteria();
        String choice = "1";

        while(choice != "2") {
            searchCafe();

            System.out.println("");
            view.showChoices(new ArrayList<>(Arrays.asList("try agin","continue order")));
            String input = view.getUserChoice(new ArrayList<>(Arrays.asList("try agin","continue order")));

            if(input.equals("2")) {
                choice.equals("2");
            }

        }

    }

    public List<SearchCafeteriaBean> getCafeterias(){

        List<String> searchTypes = new ArrayList<>(Arrays.asList("name","address or city","get all"));
        String type = view.drawGetSearchType(searchTypes);
        List<SearchCafeteriaBean> cafeterias = new ArrayList<>();
        SearchCafeteriaBean bean;

        switch (type){
            case "1":
                System.out.println("insert name: ");
                bean = new SearchCafeteriaBean(view.getString(),null);
                cafeterias.add(controllerAppl.searchCafeterias(bean).getFirst());
                break;

            case "3":
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
            showSelectedCafeteria(cafeterias.getFirst());
            return;
        }

        List<String> cafes = new ArrayList<>();
        for (SearchCafeteriaBean cafeteria : cafeterias) {
            cafes.add(cafeteria.getName());
        }

        System.out.println("\ncafeterias found: ");
        view.showChoices(cafes);

        System.out.println("\nselect a cafeteria: ");
        showSelectedCafeteria(cafeterias.get(Integer.parseInt(view.getUserChoice(cafes)) -1));
    }

    public void showSelectedCafeteria(SearchCafeteriaBean cafe){
        CafeteriaBean bean = controllerAppl.loadSelectedCafeteria(cafe);
        List<String> items = new ArrayList<>(Arrays.asList(bean.getName(),bean.getCity(),bean.getAddress(),bean.getNumber(),bean.getDescription()));
        System.out.println("\nSELECTED CAFETERIA: ");
        view.showList(items);
    }


}
