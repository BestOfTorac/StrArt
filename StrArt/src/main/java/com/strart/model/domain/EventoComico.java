package com.strart.model.domain;

import java.util.List;

public class EventoComico extends Evento {

    private String caratteristiche;
    private String tipologiaUmorismo;
    private Boolean interazionePubblico;
    private List<String> temiTrattati;
    private String fasciaEtaConsigliata;

    @Override
    public  String visualizzaEvento(){
        return "Evento comico: "+caratteristiche;
    }

    @Override
    public  void aggiungiCaratteristiche(String car){
       caratteristiche=car;
    }

    @Override
    public String getTipo(){
        return "comico";
    }

}
