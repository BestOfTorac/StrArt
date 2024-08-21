package com.strart.controller;

import com.strart.exception.DAOException;
import com.strart.model.bean.*;
import com.strart.model.domain.*;
import com.strart.view.ApiControllerGrafico;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;


public class OttieniIndicazioniController {

    String indirizzo;
    ListEvento listEvento;

    FacadeOttieniIndicazioni facadeOttieniIndicazioni;

    public OttieniIndicazioniController(){
        facadeOttieniIndicazioni= new FacadeOttieniIndicazioni();
    }

    public BeanEventi cercaEventi(IndirizzoBean indirizzoB) throws DAOException, SQLException, IOException {
        BeanEventi eventiB;
        Coordinate cordinate;

        IndirizzoBeanAPI indirizzoBeanAPI = new IndirizzoBeanAPI(indirizzoB.getIndirizzo());
        ApiControllerGrafico api = new ApiControllerGrafico();
        CoordinateBean coordinateB = api.coordinateIndirizzo(indirizzoBeanAPI);

        cordinate = new Coordinate(coordinateB.getIndirizzo(),coordinateB.getLongitudine(), coordinateB.getLatitudine(), coordinateB.getType());
        listEvento = facadeOttieniIndicazioni.cercaEventi(cordinate);


        eventiB = new BeanEventi(cordinate.getLongitudine(), cordinate.getLatitudine(), cordinate.getType());

        for(Evento evento: listEvento.getListaEvento()) {
            BeanEvento beanEvento= new BeanEvento(evento.getNomeArtista(), evento.getDescrizione(), evento.getImmagine(), evento.getData(), evento.getOrarioInizio(), evento.getOrarioFine(),evento.getStato(), evento.getTipo(), evento.getLatitudine(), evento.getLongitudine());
            eventiB.addEvento(beanEvento);
        }



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

        facadeOttieniIndicazioni.partecipaEvento(evento);

    }



}
