package graphicalcontrollers.searchcafeteria;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import controller.SearchCafeteriaController;
import engineering.SessionManager;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import graphicalcontrollers.orderbuilder.OrderBuilderCLI;
import viewcli.SearchCafeteriaViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchCafeteriaCLI {

    private SearchCafeteriaViewCli view;
    private SearchCafeteriaController controllerAppl;


    public void launch(){

        view = new SearchCafeteriaViewCli();
        controllerAppl = new SearchCafeteriaController();

        view.drawTitle();
        searchCafe();

    }

    public List<CafeteriaBean> getCafeterias(){

        List<String> searchTypes = new ArrayList<>(Arrays.asList("name","address or city","get all"));
        int type = view.drawGetSearchType(searchTypes);
        List<CafeteriaBean> cafeterias = new ArrayList<>();
        SearchCafeteriaBean bean = new SearchCafeteriaBean();

        try {

            switch (type) {
                case 0:
                    System.out.println("insert name: ");
                    bean.setName(view.getString());
                    cafeterias.add(controllerAppl.getCafeteriaByName(bean));
                    break;

                case 1:
                    System.out.println("insert address: ");
                    bean.setAddress(view.getString());
                    cafeterias = controllerAppl.getCafeByAddress(bean);
                    break;


                case 2:
                    cafeterias = controllerAppl.getAllCafeterias();
                    break;

                default:
            }

        } catch (NoCafeteriasFoundException | SystemErrorException e){
            e.showException();
            searchCafe();
            return cafeterias;
        }

        return cafeterias;

    }

    public void searchCafe(){
        List<CafeteriaBean> cafeterias = getCafeterias();

        if(cafeterias.size() == 1){
            //utente ha scelto caffetteria specifica
            showSelectedCafeteria(cafeterias.getFirst());
            return;
        }

        //devo mostrare più caffetterie all'utente
        List<String> cafes = new ArrayList<>();
        for (CafeteriaBean cafeteria : cafeterias) {
            cafes.add(cafeteria.getName() + cafeteria.getDistance());
        }

        System.out.println("cafeterias found: ");
        view.showChoices(cafes);

        System.out.println("select a cafeteria: ");
        showSelectedCafeteria(cafeterias.get(view.getUserChoice(cafes)));
    }

    public void showSelectedCafeteria(CafeteriaBean bean){

        try{

            List<String> items = new ArrayList<>(Arrays.asList(bean.getName(),bean.getCity(),bean.getAddress(),bean.getNumber(),bean.getDescription()));

            view.drawSelectedCafeteria(items);

            List<String> list = new ArrayList<>(Arrays.asList("continue order","find new cafeteria"));
            view.showChoices(list);
            int input = view.getUserChoice(list);

            if(input == 0) {
                String session = SessionManager.getInstance().newPlaceOrderSession();
                PlaceOrderController contr = new PlaceOrderController(session);

                contr.setCafeteria(bean.getName());
                new OrderBuilderCLI().launch(session);
            }
            else{
                launch();
            }

        } catch (SystemErrorException e) {
            e.showException();
        }
    }


}