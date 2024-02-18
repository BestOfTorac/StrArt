package com.strart.model.domain;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class Evento {

    String NomeArtista;
    String descrizione;
    String latitudine;
    String longitudine;
    Date data;
    Time orarioInizio;
    Time orarioFine;
    Blob immagine;

    public Evento(String NomeArtista, String descrizione,String latitudine, String longitudine, Date data, Time orarioInizio, Time orarioFine, Blob immagine){
        this.NomeArtista=NomeArtista;
        this.descrizione=descrizione;
        this.latitudine=latitudine;
        this.longitudine=longitudine;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.immagine=immagine;
    }


}
