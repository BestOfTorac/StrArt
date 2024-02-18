package com.strart;

import com.strart.model.domain.ApplicazioneSrage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStrArt extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ApplicazioneSrage.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStrArt.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 414, 795);
        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}