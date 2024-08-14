package com.strart.view;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.strart.controller.OttieniIndicazioniController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.IndirizzoBean;
import com.strart.model.domain.ApplicazioneSrage;
import com.strart.model.domain.Evento;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;


public class OttieniIndicazioniControllerGrafico {


    @FXML
    private TextField textField;

    @FXML
    private MapView mapView;

    private Marker markerClick;


    private boolean setMarker = false;

    public OttieniIndicazioniControllerGrafico() {
        // no position for click marker yet
        markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(false);

        MapLabel labelClick = new MapLabel("click!", 10, -10).setVisible(false).setCssClass("orange-label");

        markerClick.attachLabel(labelClick);
    }


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
        for(Evento evento: eventiB.getListEvento().getListaEvento()){
            System.out.println(evento.getNomeArtista());
            Coordinate cord = new Coordinate(Double.valueOf(eventiB.getCordinate().getLatitudine()), Double.valueOf(eventiB.getCordinate().getLongitudine()));
            Marker marker = Marker.createProvided(Marker.Provided.RED).setPosition(cord).setVisible(true);


            marker.visibleProperty();
            mapView.addMarker(marker);
            System.out.println("Messo il marker"+ marker);

        }
        //markerRosso.visibleProperty();

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
            case "street"->mapView.setZoom(18);
            case "city"->mapView.setZoom(14);
            default->mapView.setZoom(16);

        }
        mapView.setCenter(coordinate);
        mapView.setMapType(MapType.OSM);
        mapView.initialize(Configuration.builder()
                .projection(Projection.WEB_MERCATOR)
                .showZoomControls(false)
                .build());

        // add an event handler for singleclicks, set the click marker to the new position when it's visible
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
    }

    public void gestisciEventi() throws IOException {

        //this.setMarker = true;

        //if (markerClick != null && markerClick.getPosition() != null) {
            FXMLLoader fxmlLoader;
            Stage stage = ApplicazioneSrage.getStage();
            Scene scene;

            String fxmlFile;

            fxmlFile = "/com/strart/GestisciEventi.fxml";
            fxmlLoader = new FXMLLoader();
            Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
            //final GestisciEventiControllerGrafico controller = fxmlLoader.getController();
            //controller.setFields(textField.getText() /*,markerClick.getPosition()*/);
            scene = new Scene(rootNode, 414, 795);


            stage.setTitle("StrArt");
            stage.setScene(scene);
            stage.show();

        //}
    }


}