module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens starter to javafx.fxml;
    exports starter;

    opens graphicalcontrollers.searchcafeteria to javafx.fxml;
    exports graphicalcontrollers.searchcafeteria;

    opens graphicalcontrollers.orderbuilder to javafx.fxml;
    exports graphicalcontrollers.orderbuilder;

    opens graphicalcontrollers.cell to javafx.fxml;
    exports graphicalcontrollers.cell;

    opens controller to javafx.fxml;
    exports controller;

    opens bean to javafx.fxml;
    exports bean;

    exports graphicalcontrollers;
    opens graphicalcontrollers to javafx.fxml;

    exports model.MenuItem to javafx.fxml;
    opens model to javafx.fxml;

    opens images to javafx.fxml;

    exports graphicalcontrollers.customizebeverage to javafx.fxml;
    opens graphicalcontrollers.customizebeverage to javafx.fxml;

    exports graphicalcontrollers.finalizeorder to javafx.fxml;
    opens graphicalcontrollers.finalizeorder to javafx.fxml;

    exports graphicalcontrollers.ordersummary to javafx.fxml;
    opens graphicalcontrollers.ordersummary to javafx.fxml;

    exports graphicalcontrollers.home to javafx.fxml;
    opens graphicalcontrollers.home to javafx.fxml;

    exports graphicalcontrollers.myorders to javafx.fxml;
    opens graphicalcontrollers.myorders to javafx.fxml;

    exports graphicalcontrollers.processorders to javafx.fxml;
    opens graphicalcontrollers.processorders to javafx.fxml;

    exports graphicalcontrollers.login to javafx.fxml;
    opens graphicalcontrollers.login to javafx.fxml;

    exports graphicalcontrollers.homebarista to javafx.fxml;
    opens graphicalcontrollers.homebarista to javafx.fxml;

    exports graphicalcontrollers.popup to javafx.fxml;
    opens graphicalcontrollers.popup to javafx.fxml;

    }