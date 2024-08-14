package com.strart.model.domain;

public class EventoTeatrale extends Evento {

    String caratteristiche;

    @Override
    public  String visualizzaEvento(){
        return "Evento Teatrale: "+caratteristiche;
    }

    @Override
    public  void aggiungiCaratteristiche(String car){
       caratteristiche=car;
    }

}
