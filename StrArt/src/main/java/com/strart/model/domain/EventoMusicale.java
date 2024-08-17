package com.strart.model.domain;

public class EventoMusicale extends Evento {

    String caratteristiche;

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
