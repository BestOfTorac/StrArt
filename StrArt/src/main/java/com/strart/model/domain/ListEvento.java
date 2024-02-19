package com.strart.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListEvento {
    List<Evento> listaEvento = new ArrayList<>();

    public void addEvento(Evento evento) {
        this.listaEvento.add(evento);
    }

    public List<Evento> getListaEvento(){
        return listaEvento;
    }


}
