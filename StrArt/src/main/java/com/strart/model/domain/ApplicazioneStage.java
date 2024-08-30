package com.strart.model.domain;

import javafx.stage.Stage;

//Classe con attributo con ambito di classe per mantenere lo 'stage', utile per l'inizializzazione di una schermata grafica
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
