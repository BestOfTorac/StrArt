package com.strart.model.domain;

import javafx.stage.Stage;


public class ApplicazioneSrage {

    private static Stage stage;

    private ApplicazioneSrage(){

    }
    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage stage){
        ApplicazioneSrage.stage =stage;
    }

}
