package com.strart.controller;

import com.strart.exception.DAOException;
import com.strart.model.bean.*;
import com.strart.model.dao.CercaEventiDAO;
import com.strart.model.dao.PartecipaEventoDAO;
import com.strart.model.domain.Credentials;
import com.strart.model.domain.Evento;
import com.strart.model.domain.FactoryEvento;
import com.strart.model.domain.ListEvento;
import com.strart.view.ApiControllerGrafico;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class OttieniIndicazioniController {

    String indirizzo;
    ListEvento listEvento;

    public BeanEventi cercaEventi(IndirizzoBean indirizzoB) throws DAOException, SQLException, IOException {
        BeanEventi eventiB;
        Coordinate cordinate;

        IndirizzoBeanAPI indirizzoBeanAPI = new IndirizzoBeanAPI(indirizzoB.getIndirizzo());
        ApiControllerGrafico api = new ApiControllerGrafico();
        CoordinateBean coordinateB = api.coordinateIndirizzo(indirizzoBeanAPI);

        cordinate = new Coordinate(coordinateB.getIndirizzo(),coordinateB.getLongitudine(), coordinateB.getLatitudine(), coordinateB.getType());
        listEvento = new CercaEventiDAO().execute(cordinate);
        eventiB = new BeanEventi(listEvento,cordinate);
        return eventiB;
    }

    public BeanEvento ottieniEvento(String nomeDArte, Date data, Time oraInizio){

        BeanEvento beanEvento = null;

        for(Evento evento: listEvento.getListaEvento()) {
            if(nomeDArte.equals(evento.getNomeArtista()) && data.equals(evento.getData()) && oraInizio.equals(evento.getOrarioInizio())){
                beanEvento = new BeanEvento(evento.getNomeArtista(), evento.getDescrizione(), evento.getImmagine(), evento.getData(), evento.getOrarioInizio(), evento.getOrarioFine(), evento.getTipo());
            }
        }

        return beanEvento;
    }

    public void partecipaEvento(BeanEvento eventoBean)throws DAOException, SQLException{

        FactoryEvento factoryEvento= new FactoryEvento();
        Evento evento= factoryEvento.createEventoMusicale();
        evento.setNomeArtista(eventoBean.getNomeArtista());
        evento.setData(eventoBean.getData());
        evento.setOrarioInizio(eventoBean.getOrarioInizio());

        //TODO il facade si preoccuperà di recuperare il credential
        new PartecipaEventoDAO().execute(evento, Credentials.getUsername());



    }



}
