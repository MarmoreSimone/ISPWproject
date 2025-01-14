package graphicalcontrollers;

import bean.BeverageBean;

import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;



public abstract class GraphicalController {

    public void launch(){

    }

    public void initialize2(){

    }


    public void setControllerApplPlaceOrder(PlaceOrderController controller){

    }

    public void setContrOrderBuilder(OrderBuilderGUI controller){

    }

    public void setBeverage(BeverageBean bev){

    }

    public void setCafeteria(SearchCafeteriaBean caf){

    }

    public void setData(Object obj, GraphicalController parent){

    }

    public void setSession(String session){

    }

}

