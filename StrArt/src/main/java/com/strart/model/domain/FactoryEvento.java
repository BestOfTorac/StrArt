package com.strart.model.domain;


public class FactoryEvento {

    //creazione a run time dell'evento in base al parametro passato
    public Evento createEvento(String type) {
        switch (type)
        {
            case "musicale" : return new EventoMusicale();
            case "teatrale": return new EventoTeatrale();
            default: return new EventoComico();

        }

    }

    public Evento createEventoMusicale(){
        return new EventoMusicale();
    }

    public Evento createEventoTeatrale(){
        return new EventoTeatrale();
    }

    public Evento createEventoComico(){
        return new EventoComico();
    }


}
