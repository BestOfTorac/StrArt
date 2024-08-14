package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.controller.GestisciEventiiController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEvento;
import com.strart.model.domain.ApplicazioneSrage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;


public class GestisciEventiControllerGrafico {

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField;

    @FXML
    private DatePicker dataEvento;
    @FXML
    private Spinner hourSpinnerIn;
    @FXML
    private Spinner minuteSpinnerIn;
    @FXML
    private Spinner hourSpinnerOut;
    @FXML
    private Spinner minuteSpinnerOut;
    @FXML
    private ComboBox selectionComboBox;

    @FXML
    private MapView mapView;


    private String indirizzo;
    private Coordinate coordinate;

    public void setFields(String indirizzo/*, Coordinate coordinate*/) {
        this.indirizzo = indirizzo;
        //this.coordinate = coordinate;
        System.out.println("indirizzo: " + this.indirizzo);
        //System.out.println("coordinate: " + this.coordinate.toString());

        textField.setText(this.indirizzo);
    }

    @FXML
    protected void viewCreaEvento() throws IOException{
        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneSrage.getStage();
        Scene scene;

        String fxmlFile;

        fxmlFile = "/com/strart/creaEvento.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, 414, 795);


        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void creaEvento(){

        int hoursIn = (int) hourSpinnerIn.getValue();
        int minutesIn = (int) minuteSpinnerIn.getValue();
        int hoursOut = (int) hourSpinnerOut.getValue();
        int minutesOut = (int) minuteSpinnerOut.getValue();

        // Creazione oggetti LocalTime
        LocalTime localTimeIn = LocalTime.of(hoursIn, minutesIn);
        LocalTime localTimeOut = LocalTime.of(hoursOut, minutesOut);

        // Converzione LocalTime in Time
        Time timeIn = Time.valueOf(localTimeIn);
        Time timeOut = Time.valueOf(localTimeOut);

        BeanEvento beanEvento= new BeanEvento(textArea.getText(), (Blob) imageView.getImage(), textField.getText(), (Date) dataEvento.getUserData(), timeIn, timeOut, selectionComboBox.getValue().toString());
        GestisciEventiiController gestEventi= new GestisciEventiiController();

        try{
            gestEventi.creaEvento(beanEvento);
        }catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }



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

    @FXML
    protected void goHome() throws IOException{
        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneSrage.getStage();
        Scene scene;

        String fxmlFile;

        fxmlFile = "/com/strart/artistiview.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        final OttieniIndicazioniControllerGrafico controller = fxmlLoader.getController();
        controller.initMapAndControls("41.9028","12.4964","city");
        scene = new Scene(rootNode, 414, 795);


        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void goBack() throws IOException{
        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneSrage.getStage();
        Scene scene;

        String fxmlFile;

        fxmlFile = "/com/strart/GestisciEventi.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, 414, 795);


        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void visualizzaEventi()throws IOException{

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