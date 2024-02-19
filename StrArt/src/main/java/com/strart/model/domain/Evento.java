package com.strart.model.domain;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class Evento {

    String nomeArtista;
    String descrizione;
    String latitudine;
    String longitudine;
    Date data;
    Time orarioInizio;
    Time orarioFine;
    Blob immagine;

    String stato;

    public Evento(String nomeArtista, String descrizione,String latitudine, String longitudine, Date data, Time orarioInizio, Time orarioFine, Blob immagine, String stato){
        this.nomeArtista=nomeArtista;
        this.descrizione=descrizione;
        this.latitudine=latitudine;
        this.longitudine=longitudine;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.immagine=immagine;
        this.stato=stato;
    }

    public String getNomeArtista(){
        return nomeArtista;
    }

}
