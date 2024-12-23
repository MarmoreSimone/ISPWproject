module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens starter to javafx.fxml;
    exports starter;

    opens graphicalcontrollers.searchCafeteria to javafx.fxml;
    exports graphicalcontrollers.searchCafeteria;

    opens bean to javafx.fxml;
    exports bean;
    exports graphicalcontrollers;
    opens graphicalcontrollers to javafx.fxml;

    /*
    opens graphicalcontrollers.Cli to javafx.fxml;
    exports graphicalcontrollers.Cli;
     */

}