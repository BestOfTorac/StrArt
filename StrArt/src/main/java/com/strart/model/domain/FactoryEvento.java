package com.strart.model.domain;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class FactoryEvento {


    public Evento createEvento(String type) {
        switch (type)
        {
            case "Musicale": return new EventoMusicale();
            case "Teatrale": return new EventoTeatrale();
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
