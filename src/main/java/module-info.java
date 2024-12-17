module com.example.ispwproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ispwproject to javafx.fxml;
    exports com.example.ispwproject;
}