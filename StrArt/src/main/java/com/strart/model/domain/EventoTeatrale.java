package com.strart.model.domain;

public class EventoTeatrale extends Evento {

    private String caratteristiche;
    private String genere;
    private Boolean attoUnico;
    private int numeroAtti;
    private String compagniaTeatrale;

    @Override
    public  String visualizzaEvento(){
        return "Evento Teatrale: "+caratteristiche;
    }

    @Override
    public  void aggiungiCaratteristiche(String car){
       caratteristiche=car;
    }

    @Override
    public String getTipo(){
        return "teatrale";
    }
}
