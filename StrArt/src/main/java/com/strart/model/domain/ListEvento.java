package com.strart.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListEvento {
    List<Evento> listEvento = new ArrayList<>();

    public void addGiacenza(Evento evento) {
        this.listEvento.add(evento);
    }


}
