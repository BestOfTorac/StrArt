package com.strart.model.domain;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class Evento {

    String idEvento;
    String descrizione;
    String indirizzo;
    Date data;
    Time orarioInizio;
    Time orarioFine;
    Blob immagine;

    public Evento(String idEvento, String descrizione, String indirizzo, Date data, Time orarioInizio, Time orarioFine, Blob immagine){
        this.idEvento=idEvento;
        this.descrizione=descrizione;
        this.indirizzo=indirizzo;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.immagine=immagine;
    }


}
