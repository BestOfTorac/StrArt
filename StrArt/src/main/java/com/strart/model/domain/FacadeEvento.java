package com.strart.model.domain;

import com.strart.exception.DAOException;
import com.strart.model.dao.CreaEventoDAO;
import com.strart.model.dao.VisualizzaEventiDAO;

import java.sql.SQLException;

public class FacadeEvento {




    public void creaEvento(Evento evento)throws DAOException, SQLException {

        //Recupera il username dal profilo
        String usernmae= Profile.username;

        //Istanzia il dao di creazione di un evento passandogli l'evento
        new CreaEventoDAO().execute(usernmae, evento);

        //aggiorna il numero di eventi nel profilo
        Profile.summaEvento(1);


    }

    public ListEvento visualizzaEventi()throws DAOException, SQLException {


        //Recupera il username dal profilo
        String usernmae= Profile.username;

        //Istanzia il dao di visualizzazione dei miei eventi
        ListEvento listEvento=new VisualizzaEventiDAO().execute(usernmae);

        return listEvento;
    }





}
