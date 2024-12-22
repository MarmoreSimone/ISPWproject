module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens starter to javafx.fxml;
    exports starter;

    opens graphicalcontrollers.Gui to javafx.fxml;
    exports graphicalcontrollers.Gui;

    opens bean to javafx.fxml;
    exports bean;

    /*
    opens graphicalcontrollers.Cli to javafx.fxml;
    exports graphicalcontrollers.Cli;
     */

}