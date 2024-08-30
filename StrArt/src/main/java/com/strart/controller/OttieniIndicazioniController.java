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

    //metodo per la ricerca degli eventi in base all'indirizzo inserito
    public BeanEventi cercaEventi(IndirizzoBean indirizzoB) throws DAOException, SQLException, IOException {
        BeanEventi eventiB;
        Coordinate cordinate;

        //recupero delle cordinate dall'API di OpenStreetMap
        IndirizzoBeanAPI indirizzoBeanAPI = new IndirizzoBeanAPI(indirizzoB.getIndirizzo());
        ApiControllerGrafico api = new ApiControllerGrafico();
        CoordinateBean coordinateB = api.coordinateIndirizzo(indirizzoBeanAPI);

        cordinate = new Coordinate(coordinateB.getIndirizzo(),coordinateB.getLongitudine(), coordinateB.getLatitudine(), coordinateB.getType());

        //ricerca degli eventi con le coordinate dell'indirizzo
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


    //metodo per il recupero di tutte le caratteristiche dell'evento selezionato dall'utente
    public BeanEvento ottieniEvento(BeanEvento propieta){

        BeanEvento beanEvento = null;
        for(Evento evento: listEvento.getListaEvento()) {
            if(propieta.getNomeArtista().equals(evento.getNomeArtista()) && propieta.getData().equals(evento.getData()) && propieta.getOrarioInizio().equals(evento.getOrarioInizio())){
                beanEvento = new BeanEvento(evento.getNomeArtista(), evento.getDescrizione(), evento.getImmagine(), evento.getData(), evento.getOrarioInizio(), evento.getOrarioFine(), evento.getTipo());
            }
        }

        return beanEvento;
    }

    //metodo per la partecipazione ad un evento
    public void partecipaEvento(BeanEvento eventoBean)throws DAOException, SQLException{

        FactoryEvento factoryEvento= new FactoryEvento();
        Evento evento= factoryEvento.createEventoMusicale();
        evento.setNomeArtista(eventoBean.getNomeArtista());
        evento.setData(eventoBean.getData());
        evento.setOrarioInizio(eventoBean.getOrarioInizio());

        //chiamata al facade per effettuare la partecipazione all'evento
        facadeOttieniIndicazioni.partecipaEvento(evento);

    }

    //metodo per il recupero delle indicazioni, non implementato ma fatta la predisposizione
    public void indicazioniEvento(BeanEvento beanEvento) throws  IOException {

        ApiControllerGrafico api = new ApiControllerGrafico();
        CoordinateBean coordinateBeanEvento = new CoordinateBean(beanEvento.getLongitudine(), beanEvento.getLatitudine());
        CoordinateBean coordinateBeanMiaPosizione = new CoordinateBean("12.5492675", "41.9578954");


        api.routesEvento(coordinateBeanEvento, coordinateBeanMiaPosizione);


    }



}
