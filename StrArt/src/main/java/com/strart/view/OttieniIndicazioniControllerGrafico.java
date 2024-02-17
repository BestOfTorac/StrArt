package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.model.domain.ResponseOpenStreetMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private Label welcomeText;

    @FXML
    private TextField textField;

    @FXML
    private MapView mapView;

    private static final int ZOOM_DEFAULT = 14;
    private static final Coordinate coordKarlsruheHarbour = new Coordinate(41.9028, 12.4964);

    public OttieniIndicazioniControllerGrafico() {

    }

    @FXML
    protected void onHelloButtonClick(){
        initMapAndControls();

        System.out.println("indirizzo = "+textField.getText());
        callApi(textField.getText());
    }

    public void initMapAndControls() {

        // watch the MapView's initialized property to finish initialization
        /*
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterMapIsInitialized();
            }
        });
         */

        mapView.setZoom(ZOOM_DEFAULT);
        mapView.setCenter(coordKarlsruheHarbour);

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

            System.out.println("lat "+lat);
            System.out.println("lon "+lon);

            Coordinate roma = new Coordinate(Double.valueOf(lat), Double.valueOf(lon));

            //mapView.setZoom(9);
            mapView.setCenter(roma);

            // Ora puoi utilizzare l'oggetto responseObject come desideri
            System.out.println(responseObject.toString());
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }


}