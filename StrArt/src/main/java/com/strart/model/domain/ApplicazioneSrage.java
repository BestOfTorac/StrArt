package com.strart.model.domain;

import javafx.stage.Stage;


public class ApplicazioneSrage {

    private static Stage stage;

    public ApplicazioneSrage(Stage stage){
        ApplicazioneSrage.stage =stage;
    }

    public static Stage getStage(){
        return stage;
    }

}
