package com.strart.model.bean;

import javafx.fxml.LoadException;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;

public class BeanEvento {

    String nomeArtista;
    String descrizione;
    Blob immagine;
    String latitudine;
    String longitudine;
    String indirizzo;

    Date data;
    Time orarioInizio;
    Time orarioFine;

    String stato;
    String tipoEvento;

    public BeanEvento(String nomeArtista, String descrizione, Blob immagine,  Date data, Time orarioInizio, Time orarioFine, String stato, String tipoEvento, String latitudine, String longitudine){
        //TODO: fare i controlli dei dati (deescrizione almeno 10 caratteri, data maggiore uguale a quella di oggi, orario fine dopo orario inizio, ...)
        this.nomeArtista=nomeArtista;
        this.descrizione=descrizione;
        this.immagine=immagine;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.stato=stato;
        this.tipoEvento=tipoEvento;
        this.latitudine=latitudine;
        this.longitudine=longitudine;

    }

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

    public BeanEvento( String descrizione, Blob immagine, String indirizzo, Date data, Time orarioInizio, Time orarioFine, String tipoEvento)throws LoadException {
        //TODO: fare i controlli dei dati (deescrizione almeno 10 caratteri, data maggiore uguale a quella di oggi, orario fine dopo orario inizio, ...)

        if(descrizione.length() < 15){
            throw new IllegalArgumentException("La lunghezza della descrizionee deve essere maggiore di 15 caratteri");
        }

        LocalDate currentDate = LocalDate.now();
        java.util.Date currentDateAsDate =  Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(data.before(currentDateAsDate)){
            throw new IllegalArgumentException("La data inserita deve essere successiva alla data odierna");
        }

        if(orarioInizio.after(orarioFine) || orarioInizio.equals(orarioFine)){
            throw new IllegalArgumentException("Gli orari inseriti non sono consistenti");
        }



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

    // Getter per lo stato
    public String getStato() {
        return stato;
    }

    // Getter per tipoEvento
    public String getTipoEvento() {
        return tipoEvento;
    }

    // Getter per la latitudine
    public String getLatitudine() {
        return latitudine;
    }

    // Getter per la longitudine
    public String getLongitudine() {
        return longitudine;
    }


}
