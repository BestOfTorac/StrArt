package com.strart.view;

import com.sothawo.mapjfx.*;
import com.strart.controller.GestisciEventiiController;
import com.strart.exception.DAOException;
import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.BeanEvento;
import com.strart.model.domain.ApplicazioneStage;
import com.strart.model.domain.Evento;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
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

    private GestisciEventiiController gestEventi;

    private String indirizzo;
    private Coordinate coordinate;

    private static final String NAMEAPP = "StrArt";

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
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;

        fxmlFile = "/com/strart/creaEvento.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, 414, 795);


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


        // Convert0 l'immagine JavaFX in BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);



        byte[] compressedImageData;
        try{

            compressedImageData = compressImage(bufferedImage, 0.8f);
        }catch ( IOException e){
            throw new IllegalArgumentException(e);
        }



        Blob blob;
        try{
            // Crea un BLOB dall'array di byte
            blob= new SerialBlob(compressedImageData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Data di creazioneeeeGrafica: " + dataEvento.getValue());
        BeanEvento beanEvento= new BeanEvento(textArea.getText(), blob, textField.getText(), Date.valueOf(dataEvento.getValue()), timeIn, timeOut, selectionComboBox.getValue().toString());

        if(gestEventi == null) {
            gestEventi = new GestisciEventiiController();
        }

        try{
            gestEventi.creaEvento(beanEvento);
        }catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }


        try{
            this.goBack();
        }catch (IOException e){
            throw new IllegalArgumentException(e);
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
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;

        fxmlFile = "/com/strart/artistiview.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        final OttieniIndicazioniControllerGrafico controller = fxmlLoader.getController();
        controller.initMapAndControls("41.9028","12.4964","city");
        scene = new Scene(rootNode, 414, 795);


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

        fxmlFile = "/com/strart/GestisciEventi.fxml";
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, 414, 795);


        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void visualizzaEventi()throws IOException{

        if(gestEventi == null) {
            gestEventi = new GestisciEventiiController();
        }
        BeanEventi beanEventi;
        try{
            beanEventi=gestEventi.visualizzaEventi();
        }catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }

        for (Evento evento: beanEventi.getListEvento().getListaEvento()) {
            System.out.println("Descrizione: "+evento.getDescrizione()+"  Data: "+evento.getData());
        }


    }




    public byte[] compressImage(BufferedImage image, float quality) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = writers.next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality); // 1 is max, 0 is min
        writer.setOutput(ios);
        writer.write(null, new IIOImage(image, null, null), param);
        writer.dispose();
        return baos.toByteArray();
    }

}