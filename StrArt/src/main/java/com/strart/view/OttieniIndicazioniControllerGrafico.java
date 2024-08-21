package com.strart.view;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;
import com.strart.controller.OttieniIndicazioniController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.BeanEvento;
import com.strart.model.bean.IndirizzoBean;
import com.strart.model.domain.ApplicazioneStage;
import com.strart.model.domain.Credentials;
import com.strart.model.domain.Evento;
import com.strart.utils.Utils;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class OttieniIndicazioniControllerGrafico {
    @FXML
    private TextField textField;
    @FXML
    private MapView mapView;
    private Marker markerClick;
    private boolean setMarker = false;
    private static HashMap<Marker, HashMap<String, String>> markers = new HashMap<>();

    private OttieniIndicazioniController indicazioni = null;

    private static String NOME_ARTISTA_PROPERTY = "nomeArtista";
    private static String DATA_PROPERTY = "data";
    private static String ORARIO_INIZIO_PROPERTY = "orarioInizio";

    public OttieniIndicazioniControllerGrafico() {
        // no position for click marker yet
        markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(false);
        MapLabel labelClick = new MapLabel("click!", 10, -10)
                .setVisible(false)
                .setCssClass("orange-label");
        markerClick.attachLabel(labelClick);
    }

    @FXML
    protected void ottieniIndicazioni() {
        removeMarkers();
        indicazioni = new OttieniIndicazioniController();
        IndirizzoBean indirizzoB = new IndirizzoBean(textField.getText());
        BeanEventi eventiB = null;

        try {
            eventiB = indicazioni.cercaEventi(indirizzoB);
        } catch (IllegalArgumentException e){
            Utils.showErrorPopup(e.getMessage());
        } catch (Exception e) {
            Utils.showErrorPopup("Errore improvviso, riprova");
            //throw new IllegalArgumentException(e);
        }

        // condizione di controllo per evitare NPE
        if (eventiB == null) return;

        for (BeanEvento evento: eventiB.getListEvento()){
            Coordinate cord = new Coordinate(
                    Double.valueOf(evento.getLatitudine()),
                    Double.valueOf(evento.getLongitudine()));

            Marker marker;
            if(evento.getStato().equals("iniziato")) {
                marker= Marker.createProvided(Marker.Provided.RED)
                        .setPosition(cord)
                        .setVisible(true);
            }else{
                marker = Marker.createProvided(Marker.Provided.BLUE)
                        .setPosition(cord)
                        .setVisible(true);
            }

            // creo una mappa dei valori importanti per il marker
            HashMap<String, String> properties = new HashMap();
            properties.put(NOME_ARTISTA_PROPERTY, evento.getNomeArtista());
            properties.put(DATA_PROPERTY, evento.getData().toString());
            properties.put(ORARIO_INIZIO_PROPERTY, evento.getOrarioInizio().toString());

            //mapView.addMarker(marker);
            addMarker(mapView, marker, properties);
        }

        refreshMapAndControls(
                eventiB.getLatitudine(),
                eventiB.getLongitudine(),
                eventiB.getType());

        //Coordinate cord = new Coordinate(Double.valueOf(eventiB.getCordinate().getLatitudine()), Double.valueOf(eventiB.getCordinate().getLongitudine()));
        //Marker marker = Marker.createProvided(Marker.Provided.BLUE).setPosition(cord).setVisible(true);
        //mapView.addMarker(marker);

    }

    private void animateClickMarker(Coordinate oldPosition, Coordinate newPosition) {
        // animate the marker to the new position
        final Transition transition = new Transition() {
            private final Double oldPositionLongitude = oldPosition.getLongitude();
            private final Double oldPositionLatitude = oldPosition.getLatitude();
            private final double deltaLatitude = newPosition.getLatitude() - oldPositionLatitude;
            private final double deltaLongitude = newPosition.getLongitude() - oldPositionLongitude;

            {
                setCycleDuration(Duration.seconds(1.0));
                setOnFinished(evt -> markerClick.setPosition(newPosition));
            }

            @Override
            protected void interpolate(double v) {
                final double latitude = oldPosition.getLatitude() + v * deltaLatitude;
                final double longitude = oldPosition.getLongitude() + v * deltaLongitude;
                markerClick.setPosition(new Coordinate(latitude, longitude));
            }
        };
        transition.play();
    }

    public void initMapAndControls(String lat, String lon, String type) {

        Coordinate coordinate = new Coordinate(Double.valueOf(lat), Double.valueOf(lon));

        switch (type){
            case "street" -> mapView.setZoom(18);
            case "city" -> mapView.setZoom(14);
            default -> mapView.setZoom(16);
        }

        mapView.setCenter(coordinate);
        mapView.setMapType(MapType.OSM);
        mapView.initialize(Configuration.builder()
                .projection(Projection.WEB_MERCATOR)
                .showZoomControls(true)
                .build());

        mapView.addEventHandler(MapViewEvent.MAP_POINTER_MOVED, event -> updateMarkers(mapView));

        // Aggiungi un EventHandler per il click sul Marker
        mapView.addEventHandler(MarkerEvent.MARKER_CLICKED, event -> {
            if (markers.get(event.getMarker()) != null) {
                HashMap<String, String> markerProperty = markers.get(event.getMarker());

                BeanEvento eventoBean = indicazioni.ottieniEvento(
                        markerProperty.get(NOME_ARTISTA_PROPERTY),
                        Date.valueOf(markerProperty.get(DATA_PROPERTY)),
                        Time.valueOf(markerProperty.get(ORARIO_INIZIO_PROPERTY)));

                EventHandler partecipaHandler = (partecipaEvent) -> {
                    try {
                        indicazioni.partecipaEvento(eventoBean);

                        Utils.showNotify("Complimenti, la tua partecipazione è stata registrata");
                    } catch (DAOException | SQLException e) {
                        Utils.showErrorPopup("Stai già partecipando a questo evento");
                    }
                };

                EventHandler indicazioniHandler = (indicazioniEvent) -> {
                        Utils.showErrorPopup("Metodo non ancora implementato");
                };

                Utils.showEventDetailPopup(eventoBean, partecipaHandler, indicazioniHandler);
            } else {
                Utils.showErrorPopup("Errore nell'ottenere informazioni dell'evento");
            }
        });

        // add an event handler for singleclicks, set the click marker to the new position when it's visible
        /*
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            System.out.println("setMarker: " + setMarker);
            if (this.setMarker) {

                final Coordinate newPosition = event.getCoordinate().normalize();
                System.out.println("Event: map clicked at: " + newPosition);

                markerClick.setVisible(true);
                final Coordinate oldPosition = markerClick.getPosition();
                if (oldPosition != null) {
                    animateClickMarker(oldPosition, newPosition);
                } else {
                    markerClick.setPosition(newPosition);
                    // adding can only be done after coordinate is set
                    mapView.addMarker(markerClick);
                }
            }

        });
        */
    }

    public void refreshMapAndControls(String lat, String lon, String type) {

        Coordinate coordinate = new Coordinate(Double.valueOf(lat), Double.valueOf(lon));

        switch (type){
            case "street" -> mapView.setZoom(18);
            case "city" -> mapView.setZoom(14);
            default -> mapView.setZoom(16);
        }

        mapView.setCenter(coordinate);
        mapView.setMapType(MapType.OSM);
    }

    private static void updateMarkers(MapView mapView) {
        markers.keySet().forEach(marker -> mapView.addMarker(marker));
    }

    private static void addMarker(MapView mapView, Marker marker, HashMap properties) {
        mapView.addMarker(marker);
        //markers.add(marker);
        if (markers.get(marker) != null) {

            //markers2.get(marker)
        } else{
            markers.put(marker, properties);
        }
    }

    private void removeMarkers() {
        // Rimuovi tutti i marker dalla mappa
        markers.keySet().forEach(marker -> mapView.removeMarker(marker));

        // Pulisci la lista dei marker
        markers.clear();
    }

    public void gestisciEventi() throws IOException {


        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;

        if(Utils.getGrafica()==0) {
            fxmlFile = "/com/strart/GestisciEventi.fxml";
        }else{
            fxmlFile = "/com/strart/GestisciEventi2.fxml";
        }

        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        //final GestisciEventiControllerGrafico controller = fxmlLoader.getController();
        //controller.setFields(textField.getText() /*,markerClick.getPosition()*/);
        scene = new Scene(rootNode, Utils.SCENE_WIDTH, Utils.SCENE_HEIGTH);


        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();

    }
    public void cambiaGrafica()throws IOException{

        Utils.switchGrafica();

        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;
        if(Utils.getGrafica()==0){
            if(Credentials.getRole().getId() == 1) {
                fxmlFile = "/com/strart/artistiview.fxml";
            }else{
                fxmlFile = "/com/strart/utente-view.fxml";
            }

        }else{
            if(Credentials.getRole().getId() == 1) {
                fxmlFile = "/com/strart/artistiview2.fxml";
            }else{
                fxmlFile = "/com/strart/utente-view2.fxml";
            }
        }

        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        final OttieniIndicazioniControllerGrafico controller = fxmlLoader.getController();
        controller.initMapAndControls("41.9028","12.4964","city");
        scene = new Scene(rootNode, Utils.SCENE_WIDTH, Utils.SCENE_HEIGTH);


        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();



    }


}