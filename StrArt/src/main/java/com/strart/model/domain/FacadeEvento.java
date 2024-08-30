package com.strart.model.domain;

import com.strart.exception.DAOException;
import com.strart.model.dao.*;
import java.sql.SQLException;

public class FacadeEvento {




    public void creaEvento(Evento evento, String persistenza)throws DAOException, SQLException {

        //Recupero del username dal profilo
        String username= Profile.getUsername();

        if(persistenza.equals("Database")) {
            //Istanziazione del dao di creazione di un evento passandogli l'evento
            new CreaEventoDAO().creaEventoOnDB(username, evento);
        }else{
            //Implementazione per il salvataggio degli eventi nel filesystem
            new CreaEventoFileSystem().salvaEventoSuFile(evento,"StrArt/EventiUtenti/"+Profile.getUsername()+"Eventi.dat");
        }
        //aggiornamento del numero di eventi nel profilo
        Profile.summaEvento(1);


    }

    public ListEvento visualizzaEventi()throws DAOException, SQLException {


        //Recupero del username dal profilo
        String usernmae= Profile.getUsername();

        //Istanziazione del dao di visualizzazione dei miei eventi
        ListEvento eventi=new VisualizzaEventiDAO().visualizzaEventoToDb(usernmae);

        return new VisualizzaEventiFileSystem().recuperaEventiDaFile(eventi, "StrArt/EventiUtenti/"+Profile.getUsername()+"Eventi.dat");
    }

    public void eliminaEvento(Evento evento, String persistenza)throws DAOException, SQLException {

        //Recupero del username dal profilo
        String username= Profile.getUsername();
        if(persistenza.equals("Database")){
            //Eliminazione evento dal db
            new EliminaEventoDAO().eliminaEventoOnDB(username, evento);

            //aggiornamento del numero di eventi nel profilo
            Profile.sottraiEvento(1);
        }



    }





}
