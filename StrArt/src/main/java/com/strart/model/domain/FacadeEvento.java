package com.strart.model.domain;

import com.strart.exception.DAOException;
import com.strart.model.dao.CreaEventoDAO;
import com.strart.model.dao.CreaEventoFileSystem;
import com.strart.model.dao.VisualizzaEventiDAO;
import com.strart.model.dao.VisualizzaEventiFileSystem;

import java.sql.SQLException;

public class FacadeEvento {




    public void creaEvento(Evento evento)throws DAOException, SQLException {

        //Recupera il username dal profilo
        String usernmae= Profile.getUsername();

        //Istanzia il dao di creazione di un evento passandogli l'evento
        new CreaEventoDAO().execute(usernmae, evento);

        //Questa è l'mplementazione per il salvataggio degli eventi nel filesystem
        //new CreaEventoFileSystem().salvaEventoSuFile(evento,"EventiUtenti/"+Profile.getUsername()+"Eventi.dat");

        //aggiorna il numero di eventi nel profilo
        Profile.summaEvento(1);


    }

    public ListEvento visualizzaEventi()throws DAOException, SQLException {


        //Recupera il username dal profilo
        String usernmae= Profile.getUsername();

        //Istanzia il dao di visualizzazione dei miei eventi
        ListEvento eventi=new VisualizzaEventiDAO().execute(usernmae);



        return new VisualizzaEventiFileSystem().recuperaEventiDaFile(eventi,"EventiUtenti/"+Profile.getUsername()+"Eventi.dat");
    }





}
