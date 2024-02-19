package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.controller.OttieniIndicazioniController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.IndirizzoBean;
import com.strart.model.domain.ApplicazioneSrage;
import com.strart.model.domain.Evento;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class CreaEventoControllerGrafico {


    @FXML
    private TextField textField;

    @FXML
    private MapView mapView;

    @FXML
    private ImageView imageView;

    private String indirizzo;
    private Coordinate coordinate;

    public void setFields(String indirizzo, Coordinate coordinate) {
        this.indirizzo = indirizzo;
        this.coordinate = coordinate;
        System.out.println("indirizzo: " + this.indirizzo);
        System.out.println("coordinate: " + this.coordinate.toString());

        textField.setText(this.indirizzo);
    }


    @FXML
    protected void onHelloButtonClick(){

        /*
        IndirizzoBean indirizzoB= new IndirizzoBean(textField.getText());
        OttieniIndicazioniController indicazioni= new OttieniIndicazioniController();
        BeanEventi eventiB;

        try{
            eventiB=indicazioni.cercaEventi(indirizzoB);
        }catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }

        for(Evento evento: eventiB.getListEvento().getListaEvento()){
            System.out.println(evento.getNomeArtista());
        }
        initMapAndControls(eventiB.getCordinate().getLatitudine(), eventiB.getCordinate().getLongitudine(), eventiB.getCordinate().getType());
        */

    }

    @FXML
    private void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un'immagine");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            } catch (Exception e) {
                //e.printStackTrace();
                showErrorAlert("Errore durante il caricamento dell'immagine.");
            }
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /*
    public void creaEvento() throws IOException {

        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneSrage.getStage();
        Scene scene;

        String fxmlFile;

        fxmlFile = "/com/strart/creaEvento.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        final CreaEventoControllerGrafico controller = fxmlLoader.getController();
        controller.initMapAndControls("41.9028","12.4964","city");
        scene = new Scene(rootNode, 414, 795);

        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();


    }
    */



}