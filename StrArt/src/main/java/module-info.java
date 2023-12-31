module com.example.strart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.strart to javafx.fxml;
    exports com.example.strart;
}