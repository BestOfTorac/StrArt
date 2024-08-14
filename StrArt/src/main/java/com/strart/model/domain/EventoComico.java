package com.strart.model.domain;

public class EventoComico extends Evento {

    String caratteristiche;

    @Override
    public  String visualizzaEvento(){
        return "Evento Comico: "+caratteristiche;
    }

    @Override
    public  void aggiungiCaratteristiche(String car){
       caratteristiche=car;
    }

}
