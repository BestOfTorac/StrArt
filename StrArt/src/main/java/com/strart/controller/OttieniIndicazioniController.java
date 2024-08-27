package com.strart.controller;

import com.strart.exception.DAOException;
import com.strart.model.bean.*;
import com.strart.model.domain.*;
import com.strart.view.ApiControllerGrafico;

import java.io.IOException;
import java.sql.SQLException;


public class OttieniIndicazioniController {

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
            BeanEvento beanEvento= new BeanEvento(evento.getNomeArtista(), evento.getDescrizione(), evento.getImmagine(), evento.getData(), evento.getOrarioInizio(), evento.getStato(), evento.getOrarioFine());
            beanEvento.setTipoEvento(evento.getTipo());
            beanEvento.setLatitudine(evento.getLatitudine());
            beanEvento.setLongitudine(evento.getLongitudine());

            eventiB.addEvento(beanEvento);
        }



        return eventiB;
    }

    public BeanEvento ottieniEvento(BeanEvento propieta){

        BeanEvento beanEvento = null;

        for(Evento evento: listEvento.getListaEvento()) {
            if(propieta.getNomeArtista().equals(evento.getNomeArtista()) && propieta.getData().equals(evento.getData()) && propieta.getOrarioInizio().equals(evento.getOrarioInizio())){
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

    public void indicazioniEvento(String lat, String lon) throws  IOException {

        ApiControllerGrafico api = new ApiControllerGrafico();
        api.routesEvento("12.5492675","41.9578954", lat, lon);



    }



}
