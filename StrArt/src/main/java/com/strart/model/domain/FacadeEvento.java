package com.strart.model.domain;

import com.strart.exception.DAOException;
import com.strart.model.dao.*;

import java.sql.SQLException;

public class FacadeEvento {




    public void creaEvento(Evento evento, String persistenza)throws DAOException, SQLException {

        //Recupera il username dal profilo
        String username= Profile.getUsername();

        if(persistenza.equals("Database")) {
            //Istanzia il dao di creazione di un evento passandogli l'evento
            new CreaEventoDAO().creaEventoOnDB(username, evento);
        }else{
            //Implementazione per il salvataggio degli eventi nel filesystem
            new CreaEventoFileSystem().salvaEventoSuFile(evento,"EventiUtenti/"+Profile.getUsername()+"Eventi.dat");
        }
        //aggiorna il numero di eventi nel profilo
        Profile.summaEvento(1);


    }

    public ListEvento visualizzaEventi()throws DAOException, SQLException {


        //Recupera il username dal profilo
        String usernmae= Profile.getUsername();

        //Istanzia il dao di visualizzazione dei miei eventi
        ListEvento eventi=new VisualizzaEventiDAO().visualizzaEventoToDb(usernmae);


        return new VisualizzaEventiFileSystem().recuperaEventiDaFile(eventi,"EventiUtenti/"+Profile.getUsername()+"Eventi.dat");
    }

    public void eliminaEvento(Evento evento, String persistenza)throws DAOException, SQLException {

        //Recupera il username dal profilo
        String username= Profile.getUsername();
        if(persistenza.equals("Database")){
            //Elimina evento dal db
            new EliminaEventoDAO().eliminaEventoOnDB(username, evento);

            //aggiorna il numero di eventi nel profilo
            Profile.sottraiEvento(1);
        }



    }





}
