package com.strart.model.bean;

import javafx.fxml.LoadException;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;

public class BeanEvento {

    private String nomeArtista;
    private String descrizione;
    private Blob immagine;
    private String latitudine;
    private String longitudine;
    private String indirizzo;

    private Date data;
    private Time orarioInizio;
    private Time orarioFine;

    private String stato;
    private String tipoEvento;

    private String persistenza;

    public BeanEvento(String nomeArtista, String descrizione, Blob immagine,  Date data, Time orarioInizio, String stato,Time orarioFine){

        this.nomeArtista=nomeArtista;
        this.descrizione=descrizione;
        this.immagine=immagine;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.stato=stato;

    }

    public BeanEvento(String nomeArtista, String descrizione, Blob immagine,  Date data, Time orarioInizio, Time orarioFine, String tipoEvento){

        this.nomeArtista=nomeArtista;
        this.descrizione=descrizione;
        this.immagine=immagine;
        this.data=data;
        this.orarioInizio=orarioInizio;
        this.orarioFine=orarioFine;
        this.tipoEvento=tipoEvento;

    }

    public BeanEvento( String descrizione, Blob immagine, String indirizzo, Date data, Time orarioInizio, Time orarioFine, String tipoEvento)throws LoadException {


        if(descrizione.length() < 15){
            throw new IllegalArgumentException("La lunghezza della descrizionee deve essere maggiore di 15 caratteri");
        }

        LocalDate currentDate = LocalDate.now();
        java.util.Date currentDateAsDate =  java.util.Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
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

    public BeanEvento(String nomeArtista, Date data, Time orarioInizio){

        this.nomeArtista=nomeArtista;
        this.data=data;
        this.orarioInizio=orarioInizio;

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

    // Getter per la persistenza
    public String getPersistenza() {
        return persistenza;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento=tipoEvento;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine=latitudine;
    }
    public void setLongitudine(String longitudine) {
        this.longitudine=longitudine;
    }

    public void setPersistenza(String persistenza) {
        this.persistenza=persistenza;
    }


}
