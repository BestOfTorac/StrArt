package com.strart.model.domain;

import com.strart.exception.DAOException;
import com.strart.model.bean.Coordinate;
import com.strart.model.dao.CercaEventiDAO;
import com.strart.model.dao.CreaEventoDAO;
import com.strart.model.dao.PartecipaEventoDAO;
import com.strart.model.dao.VisualizzaEventiDAO;

import java.sql.SQLException;

public class FacadeOttieniIndicazioni {

    private Coordinate cordinate;
    private ListEvento listEvento;


    public ListEvento cercaEventi(Coordinate cordinate)throws DAOException, SQLException {



        if (this.cordinate == null) {
            listEvento= new CercaEventiDAO().execute(cordinate);

        } else if (!this.cordinate.getLatitudine().equals(cordinate.getLatitudine()) || !this.cordinate.getLongitudine().equals(cordinate.getLongitudine())) {
            listEvento= new CercaEventiDAO().execute(cordinate);
        }else {
            System.out.println("Sono uguali le coordinate\n\n");
        }

        this.cordinate=cordinate;

        return listEvento;


    }

    public void partecipaEvento(Evento evento)throws DAOException, SQLException {


        //Recupera il username dal profilo
        String usernmae= Credentials.getUsername();

        //Istanzia il dao di partecipazione all'evento
        new PartecipaEventoDAO().execute(evento, usernmae);
    }





}
