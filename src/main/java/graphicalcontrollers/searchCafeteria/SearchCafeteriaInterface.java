package graphicalcontrollers.searchCafeteria;

import bean.SearchCafeteriaBean;

import java.util.List;

public interface SearchCafeteriaInterface {

    public List<SearchCafeteriaBean> getCafeterias();
    public void searchCafe();
    public void showSelectedCafeteria(SearchCafeteriaBean cafe);

}
