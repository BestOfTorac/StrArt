package com.strart.model.bean;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class BeanEvento {

    String nomeArtista;
    String descrizione;
    Blob immagine;

    String indirizzo;

    Date data;
    Time orarioInizio;
    Time orarioFine;

    String tipoEvento;

    public BeanEvento(String nomeArtista, String descrizione, Blob immagine,  Date data, Time orarioInizio, Time orarioFine, String tipoEvento){
        //TODO: fare i controlli dei dati (deescrizione almeno 10 caratteri, data maggiore uguale a quella di oggi, orario fine dopo orario inizio, ...)
        this.nomeArtista=nomeArtista;
        this.descrizione=descrizione;
        this.immagine=immagine;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.tipoEvento=tipoEvento;

    }

    public BeanEvento( String descrizione, Blob immagine, String indirizzo, Date data, Time orarioInizio, Time orarioFine, String tipoEvento){
        //TODO: fare i controlli dei dati (deescrizione almeno 10 caratteri, data maggiore uguale a quella di oggi, orario fine dopo orario inizio, ...)
        this.descrizione=descrizione;
        this.immagine=immagine;
        this.indirizzo=indirizzo;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.tipoEvento=tipoEvento;

    }


    // Getter per il nome dell'artista
    public String getNomeArtista() {
        return nomeArtista;
    }

    // Getter per descrizione
    public String getDescrizione() {
        return descrizione;
    }

    // Getter per immagine
    public Blob getImmagine() {
        return immagine;
    }

    // Getter per indirizzo
    public String getIndirizzo() {
        return indirizzo;
    }

    // Getter per data
    public Date getData() {
        return data;
    }

    // Getter per orarioInizio
    public Time getOrarioInizio() {
        return orarioInizio;
    }

    // Getter per orarioFine
    public Time getOrarioFine() {
        return orarioFine;
    }

    // Getter per tipoEvento
    public String getTipoEvento() {
        return tipoEvento;
    }


}
