package com.strart.model.domain;

import com.strart.exception.DAOException;
import com.strart.model.dao.CercaEventiDAO;
import com.strart.model.dao.PartecipaEventoDAO;

import java.sql.SQLException;

public class FacadeOttieniIndicazioni {

    private Coordinate cordinate;
    private ListEvento listEvento;


    public ListEvento cercaEventi(Coordinate cordinate)throws DAOException, SQLException {



        if (this.cordinate == null || (!this.cordinate.getLatitudine().equals(cordinate.getLatitudine()) || !this.cordinate.getLongitudine().equals(cordinate.getLongitudine()))) {
            listEvento = new CercaEventiDAO().execute(cordinate);
        }
        this.cordinate=cordinate;

        return listEvento;


    }

    public void partecipaEvento(Evento evento)throws DAOException, SQLException {


        //Recupera il username dal profilo
        String username= Credentials.getUsername();

        //Istanzia il dao di partecipazione all'evento
        new PartecipaEventoDAO().partecipaEventoToDb(evento, username);
    }





}
