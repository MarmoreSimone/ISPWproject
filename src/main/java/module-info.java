module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens starter to javafx.fxml;
    exports starter;
}