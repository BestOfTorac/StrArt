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

    //medoto di creazione di un evento
    public void creaEvento(BeanEvento beanEvento) throws DAOException, SQLException, IOException {

        //verifica esistenza indirizzo e recupero delle coordinate
        IndirizzoBeanAPI indirizzoBeanAPI= new IndirizzoBeanAPI(beanEvento.getIndirizzo());
        ApiControllerGrafico api= new ApiControllerGrafico();
        CoordinateBean coordinateB = api.coordinateIndirizzo(indirizzoBeanAPI);

        //recupero della località della persistenza
        String persistenza= beanEvento.getPersistenza();

        //istanziazione dell'evento
        FactoryEvento factoryEvento= new FactoryEvento();
        Evento evento= factoryEvento.createEvento(beanEvento.getTipoEvento());
        evento.setDescrizione(beanEvento.getDescrizione());
        evento.setLatitudine(coordinateB.getLatitudine());
        evento.setLongitudine(coordinateB.getLongitudine());
        evento.setData(beanEvento.getData());
        evento.setOrarioInizio(beanEvento.getOrarioInizio());
        evento.setOrarioFine(beanEvento.getOrarioFine());
        evento.setImmagine(beanEvento.getImmagine());

        //chiamata al facade per effettuare l'operazione di creazione dell'evento
        facadeEvento.creaEvento(evento, persistenza);

        //l'evento creato lo aggiungo solo se la lista non è vuota
        if(listEvento!=null){
            listEvento.addEvento(evento);
        }

    }


    //metodo per la visualizzazione degli eventi creati dall'artista
    public BeanEventi visualizzaEventi()throws DAOException, SQLException {

        BeanEventi beanEventi;

        //se la lista è vuota richiedo al facade di recuperare gli eventi
        if(listEvento==null) {
            listEvento = facadeEvento.visualizzaEventi();
        }


        beanEventi = new BeanEventi();

        //per ogni evento della lista recuperata verrà istanziato un beanEvento
        for(Evento evento: listEvento.getListaEvento()) {
            BeanEvento beanEvento= new BeanEvento(evento.getNomeArtista(), evento.getDescrizione(), evento.getImmagine(), evento.getData(), evento.getOrarioInizio(),evento.getStato(), evento.getOrarioFine());
            beanEvento.setTipoEvento(evento.getTipo());
            beanEvento.setLatitudine(evento.getLatitudine());
            beanEvento.setLongitudine(evento.getLongitudine());
            beanEventi.addEvento(beanEvento);
        }


        return beanEventi;
    }

    //metodo per l'eliminazione di un evento
    public void eliminaEvento(BeanEvento beanEvento)throws DAOException, SQLException{

        String persistenza= beanEvento.getPersistenza();
        FactoryEvento factoryEvento= new FactoryEvento();
        Evento evento= factoryEvento.createEvento(beanEvento.getTipoEvento());
        evento.setData(beanEvento.getData());
        evento.setOrarioInizio(beanEvento.getOrarioInizio());

        //chiamata al facade per effettuare l'operazione di eliminazione dell'evento
        facadeEvento.eliminaEvento(evento, persistenza);

    }


}
