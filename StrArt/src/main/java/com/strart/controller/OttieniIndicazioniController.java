package com.strart.controller;

import com.strart.exception.DAOException;
import com.strart.model.bean.*;
import com.strart.model.dao.CercaEventiDAO;
import com.strart.model.domain.ListEvento;
import com.strart.view.ApiControllerGrafico;

import java.sql.SQLException;

public class OttieniIndicazioniController {

    String indirizzo;

    public BeanEventi cercaEventi(IndirizzoBean indirizzoB) throws DAOException, SQLException {
        BeanEventi eventiB;
        Coordinate cordinate;
        ListEvento listEvento;
        IndirizzoBeanAPI indirizzoBeanAPI= new IndirizzoBeanAPI(indirizzoB.getIndirizzo());
        ApiControllerGrafico api= new ApiControllerGrafico();
        CoordinateBean coordinateB =api.coordinateIndirizzo(indirizzoBeanAPI);

        cordinate= new Coordinate(coordinateB.getIndirizzo(),coordinateB.getLongitudine(), coordinateB.getLatitudine(), coordinateB.getType());
        listEvento=new CercaEventiDAO().execute(cordinate);
        eventiB= new BeanEventi(listEvento,cordinate);
        return eventiB;
    }



}
