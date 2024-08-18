package com.strart.model.domain;

import javafx.stage.Stage;


public class ApplicazioneStage {

    private static Stage stage;

    private ApplicazioneStage(){

    }
    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage stage){
        ApplicazioneStage.stage =stage;
    }

}
