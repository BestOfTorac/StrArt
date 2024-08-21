package com.strart.model.domain;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public abstract class Evento implements Serializable {

    String nomeArtista;
    String descrizione;
    String latitudine;
    String longitudine;
    Date data;
    Time orarioInizio;
    Time orarioFine;
    Blob immagine;

    String stato;

    // Setter per nomeArtista
    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    // Setter per descrizione
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Setter per latitudine
    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    // Setter per longitudine
    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    // Setter per data
    public void setData(Date data) {
        this.data = data;
    }

    // Setter per orarioInizio
    public void setOrarioInizio(Time orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    // Setter per orarioFine
    public void setOrarioFine(Time orarioFine) {
        this.orarioFine = orarioFine;
    }

    // Setter per immagine
    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }

    // Setter per stato
    public void setStato(String stato) {
        this.stato = stato;
    }

    // Getter per nomeArtista
    public String getNomeArtista() {
        return nomeArtista;
    }

    // Getter per descrizione
    public String getDescrizione() {
        return descrizione;
    }

    // Getter per latitudine
    public String getLatitudine() {
        return latitudine;
    }

    // Getter per longitudine
    public String getLongitudine() {
        return longitudine;
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

    // Getter per immagine
    public Blob getImmagine() {
        return immagine;
    }

    // Getter per stato
    public String getStato() {
        return stato;
    }

    public abstract String visualizzaEvento();

    public  abstract void aggiungiCaratteristiche(String car);

    public abstract String getTipo();


}
