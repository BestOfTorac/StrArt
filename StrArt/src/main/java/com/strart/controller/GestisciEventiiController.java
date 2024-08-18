package com.strart.controller;

import com.strart.exception.DAOException;
import com.strart.model.bean.*;
import com.strart.model.domain.Evento;
import com.strart.model.domain.FacadeEvento;
import com.strart.model.domain.FactoryEvento;
import com.strart.model.domain.ListEvento;
import com.strart.view.ApiControllerGrafico;

import java.sql.SQLException;

public class GestisciEventiiController {

    String indirizzo;
    FacadeEvento facadeEvento;

    ListEvento listEvento;

    public GestisciEventiiController(){
        facadeEvento=new FacadeEvento();
    }
    public void creaEvento(BeanEvento beanEvento) throws DAOException, SQLException{

        //verifica esistenza indirizzo e recupero delle coordinate
        IndirizzoBeanAPI indirizzoBeanAPI= new IndirizzoBeanAPI(beanEvento.getIndirizzo());
        ApiControllerGrafico api= new ApiControllerGrafico();
        CoordinateBean coordinateB =api.coordinateIndirizzo(indirizzoBeanAPI);

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
        facadeEvento.creaEvento(evento);

        if(listEvento!=null){
            listEvento.addEvento(evento);
        }

    }

    public BeanEventi visualizzaEventi()throws DAOException, SQLException {

        BeanEventi beanEventi;
        if(listEvento==null) {
            listEvento = facadeEvento.visualizzaEventi();
        }

        beanEventi= new BeanEventi(listEvento);


        return beanEventi;
    }


}
