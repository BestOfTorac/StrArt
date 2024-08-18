package com.strart.model.domain;

import java.sql.Date;

public class Profile{

    private static String username;
    private static String nome;
    private static String cognome;
    private static Date dataNascita;
    private static String nomedArte;

    private static String descrizione;

    private static float valutazione;
    private static int numEventi;


    private Profile(){}


    public static void setUsername(String usernameV) {
        username = usernameV;
    }

    public static void setNome(String nomeV) {
        nome = nomeV;
    }

    public static void setCognome(String cognomeV) {
        cognome = cognomeV;
    }

    public static void setDataNascita(Date dataNascitaV) {
        dataNascita = dataNascitaV;
    }

    public static void setNomeDArte(String nomedArteV) {
        nomedArte = nomedArteV;
    }

    public static void setDescrizione(String descrizioneV) {
        descrizione = descrizioneV;
    }

    public static void setValutazione(float valutazioneV) {
        valutazione = valutazioneV;
    }

    public static void setNumEventi(int numEventiV) {
        numEventi = numEventiV;

    }



    public static String getUsername() {
        return username;
    }

    public static String getNome() {
        return nome;
    }

    public static String getCognome() {
        return cognome;
    }

    public static Date getDataNascita() {
        return dataNascita;
    }

    public static String getNomedArte() {
        return nomedArte;
    }

    public static String getDescrizione() {
        return descrizione;
    }

    public static float getValutazione() {
        return valutazione;
    }

    public static int getNumEventi() {
        return numEventi;
    }



    public static void summaEvento(int num){
        numEventi+=num;
    }

    public static void sottraiEvento(int num){
        if(numEventi-num < 0){
            numEventi=0;
        }else{
            numEventi-=num;
        }

    }


}
