package com.strart.model.domain;

import java.sql.Date;

public class Profile{

    static String username;
    static String nome;
    static String cognome;
    static Date dataNAscita;
    static String nomedArte;

    static String descrizione;

    static float valutazione;
    static int numEventi;

    public Profile(String username, String nome, String cognome, Date dataNAscita, String nomedArte,String descrizione, float valutazione, int numEventi) {
        Profile.username = username;
        Profile.nome = nome;
        Profile.cognome = cognome;
        Profile.dataNAscita = dataNAscita;
        Profile.nomedArte = nomedArte;
        Profile.descrizione=descrizione;
        Profile.valutazione=valutazione;
        Profile.numEventi = numEventi;
    }

    public void summaEvento(int num){
        numEventi+=num;
    }

    public void sottraiEvento(int num){
        if(numEventi-num < 0){
            numEventi=0;
        }else{
            numEventi-=num;
        }

    }

}
