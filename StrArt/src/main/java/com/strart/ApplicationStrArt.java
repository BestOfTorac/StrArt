package com.strart;

import com.strart.model.domain.ApplicazioneStage;
import com.strart.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStrArt extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ApplicazioneStage.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStrArt.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Utils.getSceneW(), Utils.getSceneH());
        scene.getRoot().requestFocus();
        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}