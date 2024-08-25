module com.strart {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.sothawo.mapjfx;
    requires com.google.gson;
    requires javafx.swing;
    requires java.sql.rowset;

    exports com.strart.model.domain.api; // Esporta il pacchetto al mondo esterno
    opens com.strart.model.domain.api to javafx.fxml, com.google.gson;
    opens com.strart to javafx.fxml, com.google.gson;
    exports com.strart;
    exports com.strart.view;
    opens com.strart.view to com.google.gson, javafx.fxml;
    opens com.strart.model.dao to com.google.gson, javafx.fxml;
    exports com.strart.model.domain;
    opens com.strart.model.domain to com.google.gson, javafx.fxml;

}