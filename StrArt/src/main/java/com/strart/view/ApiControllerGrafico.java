package com.strart.view;

import com.google.gson.Gson;
import com.strart.model.bean.CoordinateBean;
import com.strart.model.bean.IndirizzoBeanAPI;
import com.strart.model.domain.ResponseOpenStreetMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class ApiControllerGrafico {


    public CoordinateBean coordinateIndirizzo(IndirizzoBeanAPI indirizzoAPI) throws IOException {

        CoordinateBean coordinateBean = null;
        String indirizzo=indirizzoAPI.getIndirizzo();

        String indirizzoEcoded = encodeValue(indirizzo);
        // URL a cui eseguire la richiesta GET
        URL url = new URL("https://nominatim.openstreetmap.org/search?format=geocodejson&q=" + indirizzoEcoded);

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

        if (responseObject.getOpenStreetMapFeatures() == null || responseObject.getOpenStreetMapFeatures().length == 0) {
            throw new IllegalArgumentException("L'indirizzo inserito non è valido");
        }

        String lon = responseObject.getOpenStreetMapFeatures()[0].getOpenStreetMapGeometry().getcoordinates()[0];
        String lat = responseObject.getOpenStreetMapFeatures()[0].getOpenStreetMapGeometry().getcoordinates()[1];
        String tipo=responseObject.getOpenStreetMapFeatures()[0].getOpenStreetMapProperties().getGeocoding().getType();

        return new CoordinateBean(indirizzo,lon,lat, tipo);
    }


    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

}