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
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import javax.sql.rowset.serial.SerialBlob;


public class GestisciEventiControllerGrafico implements Initializable {

    @FXML
    private TextArea descrizioneEvento;
    @FXML
    private Text pageTitle;

    @FXML
    private ImageView immagineEvento;

    @FXML
    private TextField indirizzoEvento;

    @FXML
    private DatePicker dataEvento;
    @FXML
    private Spinner oraInizio;
    @FXML
    private Spinner minutiInizio;
    @FXML
    private Spinner oraFine;
    @FXML
    private Spinner minutiFine;
    @FXML
    private ComboBox tipologiaEvento;

    @FXML
    private ComboBox persistenzaEvento;

    @FXML
    private MapView mapView;

    private GestisciEventiController gestEventi;


    @FXML
    private VBox cardContainer;  // Contenitore per le card
    private static final String NAMEAPP = "StrArt";

    private static final String SETTING1 = "-fx-font-weight: bold; -fx-font-size: 12px;";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (pageTitle != null) {

            pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20)); // Font Verdana, Bold, 18px
        }

        if (cardContainer != null) {
            caricaEventi();
        }
    }


    @FXML
    public void viewCreaEvento() throws IOException{
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
        scene = new Scene(rootNode, Utils.getSceneW(), Utils.getSceneH());

        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void creaEvento(){

        int hoursIn = (int) oraInizio.getValue();
        int minutesIn = (int) minutiInizio.getValue();
        int hoursOut = (int) oraFine.getValue();
        int minutesOut = (int) minutiFine.getValue();

        // Creazione oggetti LocalTime
        LocalTime localTimeIn = LocalTime.of(hoursIn, minutesIn);
        LocalTime localTimeOut = LocalTime.of(hoursOut, minutesOut);

        // Converzione LocalTime in Time
        Time timeIn = Time.valueOf(localTimeIn);
        Time timeOut = Time.valueOf(localTimeOut);

        Blob blob = null;
        // Convert0 l'immagine JavaFX in BufferedImage
        if (immagineEvento.getImage() != null) {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(immagineEvento.getImage(), null);

            byte[] compressedImageData;
            try {
                compressedImageData = compressImage(bufferedImage, 0.8f);
                // Crea un BLOB dall'array di byte
                blob = new SerialBlob(compressedImageData);
            } catch (SQLException | IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        if (descrizioneEvento.getText().equals("")) {
            Utils.showErrorPopup("Mancato inserimento della descrizione dell'evento");
        } else if (indirizzoEvento.getText().equals("")) {
            Utils.showErrorPopup("Mancato inserimento dell'indirizzo dell'evento");
        } else if (dataEvento.getValue() == null) {
            Utils.showErrorPopup("Mancato inserimento della data dell'evento");
        } else if (tipologiaEvento.getValue() == null) {
            Utils.showErrorPopup("Mancato scelta della tipologia di evento");
        } else if (persistenzaEvento.getValue() == null) {
            Utils.showErrorPopup("Mancato scelta della persistenza dell' evento");
        } else {

            BeanEvento beanEvento= null;
            try {
                beanEvento = new BeanEvento(descrizioneEvento.getText(), blob, indirizzoEvento.getText(), Date.valueOf(dataEvento.getValue()), timeIn, timeOut, tipologiaEvento.getValue().toString());
                beanEvento.setPersistenza(persistenzaEvento.getValue().toString());

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
    public void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un'immagine");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                immagineEvento.setImage(image);
            } catch (Exception e) {

                showErrorAlert("Errore durante il caricamento dell'immagine.");
            }
        }
    }

    public void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void goHome() throws IOException{
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
        scene = new Scene(rootNode, Utils.getSceneW(), Utils.getSceneH());

        // Rimuovere il focus dal TextField
        scene.getRoot().requestFocus();

        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goBack() throws IOException{
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
        scene = new Scene(rootNode, Utils.getSceneW(), Utils.getSceneH());


        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void visualizzaEventi() throws IOException {

        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        String fxmlFile;
        if( Utils.getGrafica() == 0) {
            fxmlFile = "/com/strart/visualizzaMieiEventi.fxml";
        }else{
            fxmlFile = "/com/strart/visualizzaMieiEventi2.fxml";
        }
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, Utils.getSceneW(), Utils.getSceneH());

        stage.setTitle(NAMEAPP);
        stage.setScene(scene);
        stage.show();
    }

    public void caricaEventi() {

        if (gestEventi == null) {
            gestEventi = new GestisciEventiController();
        }

        BeanEventi beanEventi;
        try {
            beanEventi = gestEventi.visualizzaEventi();
        } catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }

        if (!beanEventi.getListEvento().isEmpty()) {
            for (BeanEvento evento : beanEventi.getListEvento()) {
                BorderPane card = createCard(evento);
                cardContainer.getChildren().add(card);
            }
        } else {
            Utils.showErrorPopup("Non hai nessun evento creato");
        }
    }

    // Metodo per creare una card
    private BorderPane createCard(BeanEvento eventoBean) {
        BorderPane card = new BorderPane();

        Label titleLabel = new Label(eventoBean.getNomeArtista());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label descriptionLabel = new Label(eventoBean.getDescrizione());
        descriptionLabel.setStyle("-fx-font-size: 12px;");

        HBox img = new HBox();
        // Converti il Blob in un InputStream

        if(eventoBean.getImmagine() != null) {
            try {
                InputStream inputStream = eventoBean.getImmagine().getBinaryStream();
                Image image = new Image(inputStream);

                ImageView imageView = new ImageView(image);

                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);

                img = new HBox(imageView);
            } catch (SQLException e) {
                img = new HBox(new Text("IMG NON PRESENTE"));
            }
        }else{
            img = new HBox(new Text("IMG NON PRESENTE"));
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Text dataText = new Text("Data: " );
        Text dataText2 = new Text(formatter.format(eventoBean.getData()));
        dataText.setStyle(SETTING1);
        HBox data = new HBox(dataText, dataText2);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Text orarioText = new Text("Orario: " );
        orarioText.setStyle(SETTING1);
        Text orarioText2 = new Text( sdf.format(eventoBean.getOrarioInizio()) + " - " + sdf.format(eventoBean.getOrarioFine()));
        HBox orario = new HBox(orarioText, orarioText2);

        Text tipologiaText = new Text("Tipologia: " );
        Text tipologiaText2 = new Text(eventoBean.getTipoEvento());
        tipologiaText.setStyle(SETTING1);
        HBox tipologia = new HBox(tipologiaText, tipologiaText2);

        Text statoText = new Text("Stato: " );
        Text statoText2 = new Text(eventoBean.getStato());
        statoText.setStyle(SETTING1);
        HBox stato = new HBox(statoText, statoText2);

        Label descrizioneText = new Label(eventoBean.getDescrizione());
        descrizioneText.setWrapText(true);

        HBox descrizione = new HBox(descrizioneText);

        VBox boxInfo = new VBox(data, orario, tipologia, stato);
        boxInfo.setSpacing(6);

        HBox hBoxContentBody = new HBox(img, boxInfo);
        hBoxContentBody.setSpacing(40);
        hBoxContentBody.setPadding(new Insets(10, 0.0, 10, 0.0));

        card.setTop(titleLabel);
        card.setCenter(new VBox(hBoxContentBody, descrizione));
        card.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: white;");

        return card;
    }

    public byte[] compressImage(BufferedImage image, float quality) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}