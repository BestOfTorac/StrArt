package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.controller.GestisciEventiController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.BeanEvento;
import com.strart.model.domain.ApplicazioneStage;
import com.strart.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.sql.rowset.serial.SerialBlob;


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

    private GestisciEventiController gestEventi;

    private String indirizzo;
    private Coordinate coordinate;

    private static final String NAMEAPP = "StrArt";


    @FXML
    protected void viewCreaEvento() throws IOException{
        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;
        if(Utils.getGrafica()==0) {
            fxmlFile = "/com/strart/creaEvento.fxml";
        }else{
            fxmlFile = "/com/strart/creaEvento2.fxml";
        }
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, Utils.SCENEW, Utils.SCENEH);


        stage.setTitle(NAMEAPP);
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

        Blob blob = null;
        // Convert0 l'immagine JavaFX in BufferedImage
        if (imageView.getImage() != null) {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);

            byte[] compressedImageData;
            try {
                compressedImageData = compressImage(bufferedImage, 0.8f);
                // Crea un BLOB dall'array di byte
                blob = new SerialBlob(compressedImageData);
            } catch (SQLException | IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        if (textArea.getText().equals("")) {
            Utils.showErrorPopup("Mancato inserimento della descrizione dell'evento");
        } else if (textField.getText().equals("")) {
            Utils.showErrorPopup("Mancato inserimento dell'indirizzo dell'evento");
        }else if (dataEvento.getValue() == null) {
            Utils.showErrorPopup("Mancato inserimento della data dell'evento");
        }else if (selectionComboBox.getValue() == null) {
            Utils.showErrorPopup("Mancato scelta della tipologia di evento");
        }else  {

            System.out.println("\nData di creazioneeeeGrafica: " + dataEvento.getValue());

            BeanEvento beanEvento= null;
            try {
                beanEvento = new BeanEvento(textArea.getText(), blob, textField.getText(), Date.valueOf(dataEvento.getValue()), timeIn, timeOut, selectionComboBox.getValue().toString());


                if (gestEventi == null) {
                    gestEventi = new GestisciEventiController();
                }


                gestEventi.creaEvento(beanEvento);
                Utils.showNotify("Evento creato correttamente");
                this.goBack();



            } catch (IllegalArgumentException | LoadException e) {
                Utils.showErrorPopup(e.getMessage());
            } catch (Exception e) {
                Utils.showErrorPopup("Errore improvviso, riprova");
            }


        }
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
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;
        if(Utils.getGrafica()==0) {
            fxmlFile = "/com/strart/artistiview.fxml";
        }else{
            fxmlFile = "/com/strart/artistiview2.fxml";
        }
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        final OttieniIndicazioniControllerGrafico controller = fxmlLoader.getController();
        controller.initMapAndControls("41.9028","12.4964","city");
        scene = new Scene(rootNode, Utils.SCENEW, Utils.SCENEH);


        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void goBack() throws IOException{
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
        scene = new Scene(rootNode, Utils.SCENEW, Utils.SCENEH);


        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void visualizzaEventi(){

        if (gestEventi == null) {
            gestEventi = new GestisciEventiController();
        }

        BeanEventi beanEventi;
        try {
            beanEventi=gestEventi.visualizzaEventi();
        } catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }

        if(beanEventi.getListEvento().size()!=0) {
            for (BeanEvento evento : beanEventi.getListEvento()) {
                System.out.println("Descrizione: " + evento.getDescrizione() + "  Data: " + evento.getData()+" Tipologia: "+evento.getTipoEvento());
            }
        }else{
            Utils.showErrorPopup("Non hai nessun evento creato");
        }

    }

    public byte[] compressImage(BufferedImage image, float quality) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

}