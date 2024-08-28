package com.strart.controller;

import com.strart.exception.DAOException;
import com.strart.model.bean.*;
import com.strart.model.domain.Evento;
import com.strart.model.domain.FacadeEvento;
import com.strart.model.domain.FactoryEvento;
import com.strart.model.domain.ListEvento;
import com.strart.view.ApiControllerGrafico;

import java.io.IOException;
import java.sql.SQLException;

public class GestisciEventiController {

    FacadeEvento facadeEvento;

    ListEvento listEvento;

    public GestisciEventiController(){
        facadeEvento=new FacadeEvento();
    }
    public void creaEvento(BeanEvento beanEvento) throws DAOException, SQLException, IOException {

        //verifica esistenza indirizzo e recupero delle coordinate
        IndirizzoBeanAPI indirizzoBeanAPI= new IndirizzoBeanAPI(beanEvento.getIndirizzo());
        ApiControllerGrafico api= new ApiControllerGrafico();
        CoordinateBean coordinateB = api.coordinateIndirizzo(indirizzoBeanAPI);

        //recupero della località della persistenza
        String persistenza= beanEvento.getPersistenza();

        FactoryEvento factoryEvento= new FactoryEvento();
        Evento evento= factoryEvento.createEvento(beanEvento.getTipoEvento());
        evento.setDescrizione(beanEvento.getDescrizione());
        evento.setLatitudine(coordinateB.getLatitudine());
        evento.setLongitudine(coordinateB.getLongitudine());
        evento.setData(beanEvento.getData());
        evento.setOrarioInizio(beanEvento.getOrarioInizio());
        evento.setOrarioFine(beanEvento.getOrarioFine());
        evento.setImmagine(beanEvento.getImmagine());

        //chiamo il facade per effettuare l'operazione di creazione dell'evento
        facadeEvento.creaEvento(evento, persistenza);

        if(listEvento!=null){
            listEvento.addEvento(evento);
        }

    }

    public BeanEventi visualizzaEventi()throws DAOException, SQLException {

        BeanEventi beanEventi;
        if(listEvento==null) {
            listEvento = facadeEvento.visualizzaEventi();
        }


        beanEventi = new BeanEventi();

        for(Evento evento: listEvento.getListaEvento()) {
            BeanEvento beanEvento= new BeanEvento(evento.getNomeArtista(), evento.getDescrizione(), evento.getImmagine(), evento.getData(), evento.getOrarioInizio(),evento.getStato(), evento.getOrarioFine());
            beanEvento.setTipoEvento(evento.getTipo());
            beanEvento.setLatitudine(evento.getLatitudine());
            beanEvento.setLongitudine(evento.getLongitudine());
            beanEventi.addEvento(beanEvento);
        }


        return beanEventi;
    }

    public void eliminaEvento(BeanEvento beanEvento)throws DAOException, SQLException{

        String persistenza= beanEvento.getPersistenza();
        FactoryEvento factoryEvento= new FactoryEvento();
        Evento evento= factoryEvento.createEvento(beanEvento.getTipoEvento());
        evento.setData(beanEvento.getData());
        evento.setOrarioInizio(beanEvento.getOrarioInizio());

        //chiamo il facade per effettuare l'operazione di creazione dell'evento
        facadeEvento.eliminaEvento(evento, persistenza);

    }


}
