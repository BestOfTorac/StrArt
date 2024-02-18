package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.controller.OttieniIndicazioniController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.IndirizzoBean;
import com.strart.model.domain.ResponseOpenStreetMap;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;


public class OttieniIndicazioniControllerGrafico {


    @FXML
    private TextField textField;

    @FXML
    private MapView mapView;

    //private static final int ZOOM_DEFAULT = 14;
    //private static final Coordinate coordKarlsruheHarbour = new Coordinate(41.9028, 12.4964);



    @FXML
    protected void onHelloButtonClick(){

        System.out.println("indirizzo = "+textField.getText());
        IndirizzoBean indirizzoB= new IndirizzoBean(textField.getText());
        OttieniIndicazioniController indicazioni= new OttieniIndicazioniController();
        BeanEventi eventiB;

        try{
            eventiB=indicazioni.cercaEventi(indirizzoB);
        }catch (DAOException e){
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

    private void afterMapIsInitialized() {
        //logger.trace("map intialized");
        //logger.debug("setting center and enabling controls...");
        //mapView.setZoom(ZOOM_DEFAULT);
        //mapView.setCenter(coordKarlsruheHarbour);
        System.out.println("afterMapIsInitialized ");
    }

    public void callApi(String indirizzo) {
        try {
            String indirizzoEcoded = encodeValue(indirizzo);
            // URL a cui eseguire la richiesta GET
            URL url = new URL("https://nominatim.openstreetmap.org/search?format=geocodejson&q="+indirizzoEcoded);

            // Apertura della connessione
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Lettura della risposta
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();

            // Utilizzo di Gson per deserializzare la risposta JSON in un oggetto Java
            Gson gson = new Gson();
            ResponseOpenStreetMap responseObject = gson.fromJson(response.toString(), ResponseOpenStreetMap.class);

            String lon = responseObject.getOpenStreetMapFeatures()[0].getOpenStreetMapGeometry().getcoordinates()[0];
            String lat = responseObject.getOpenStreetMapFeatures()[0].getOpenStreetMapGeometry().getcoordinates()[1];
            String tipo=responseObject.getOpenStreetMapFeatures()[0].getOpenStreetMapProperties().getGeocoding().getType();

            System.out.println("lat "+lat);
            System.out.println("lon "+lon);
            System.out.println("tipo "+tipo);

            Coordinate roma = new Coordinate(Double.valueOf(lat), Double.valueOf(lon));

            //mapView.setZoom(9);
            mapView.setCenter(roma);

            // Ora puoi utilizzare l'oggetto responseObject come desideri
            System.out.println(responseObject.toString());
        } catch (Exception e) {
            System.out.println("Errore "+e);;
        }
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }


}