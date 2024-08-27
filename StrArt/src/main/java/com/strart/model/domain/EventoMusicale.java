package com.strart.model.domain;

import java.util.List;

public class EventoMusicale extends Evento {

    private String caratteristiche;
    private String genereMusicale;
    private List<String> elencoCanzoni;
    private String strumentazione;

    @Override
    public  String visualizzaEvento(){
        return "Evento musicale: "+caratteristiche;
    }

    @Override
    public  void aggiungiCaratteristiche(String car){
       caratteristiche=car;
    }

    @Override
    public String getTipo(){
        return "musicale";
    }

}
