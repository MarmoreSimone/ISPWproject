module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens starter to javafx.fxml;
    exports starter;

    opens graphicalcontrollers.searchcafeteria to javafx.fxml;
    exports graphicalcontrollers.searchcafeteria;

    opens graphicalcontrollers.orderbuilder to javafx.fxml;
    exports graphicalcontrollers.orderbuilder;

    opens controller to javafx.fxml;
    exports controller;

    opens bean to javafx.fxml;
    exports bean;
    exports graphicalcontrollers;
    opens graphicalcontrollers to javafx.fxml;



}