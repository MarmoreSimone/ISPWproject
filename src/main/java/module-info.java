module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens starter to javafx.fxml;
    exports starter;
    opens graphicalcontrollers to javafx.fxml;
    exports graphicalcontrollers;

}