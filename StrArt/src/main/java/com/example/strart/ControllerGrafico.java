package com.example.strart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerGrafico {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(){
        welcomeText.setText("Tua madre ");
    }
}