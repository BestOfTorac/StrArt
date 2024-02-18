package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.controller.OttieniIndicazioniController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.IndirizzoBean;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.SQLException;


public class OttieniIndicazioniControllerGrafico {


    @FXML
    private TextField textField;

    @FXML
    private MapView mapView;


    @FXML
    protected void onHelloButtonClick(){


        IndirizzoBean indirizzoB= new IndirizzoBean(textField.getText());
        OttieniIndicazioniController indicazioni= new OttieniIndicazioniController();
        BeanEventi eventiB;

        try{
            eventiB=indicazioni.cercaEventi(indirizzoB);
        }catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }

        initMapAndControls(eventiB.getCordinate().getLatitudine(), eventiB.getCordinate().getLongitudine(), eventiB.getCordinate().getType());



        //callApi(textField.getText());
    }

    public void initMapAndControls(String lat, String lon, String type) {


        Coordinate coordinate = new Coordinate(Double.valueOf(lat), Double.valueOf(lon));
        switch (type){
            case "street"->mapView.setZoom(30);
            case "city"->mapView.setZoom(14);
            default->mapView.setZoom(16);

        }
        mapView.setCenter(coordinate);
        mapView.setMapType(MapType.OSM);
        mapView.initialize(Configuration.builder()
                .projection(Projection.WEB_MERCATOR)
                .showZoomControls(false)
                .build());
    }


}