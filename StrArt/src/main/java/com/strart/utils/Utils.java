package com.strart.utils;

import com.strart.model.bean.BeanEvento;
import com.strart.model.domain.ApplicazioneStage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Utils {
    public static double SCENEW = 414;
    public static double SCENEH = 695;

    private static final String SETTING1 = "-fx-alignment: center;";

    private static int grafica=0;


    private Utils(){}

    public static void showNotify(String message) {
        // Crea il popup
        Popup popup = new Popup();

        Stage owner = ApplicazioneStage.getStage();

        // Crea l'overlay nero
        Rectangle overlay = new Rectangle(owner.getWidth() - 5, owner.getHeight() - 5, Color.BLACK);
        overlay.setOpacity(0.3);

        // Crea il pulsante di chiusura
        Button closeButton = new Button("X");
        closeButton.setOnAction(e -> popup.hide());
        closeButton.setStyle("-fx-alignment: center-right;");

        Text title = new Text("Notifica \t");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        title.setStyle(SETTING1);

        HBox header = new HBox(10, title, closeButton);
        header.setAlignment(Pos.CENTER);

        Label messageLabel = new Label();
        messageLabel.setText("\n" + message);
        messageLabel.setWrapText(true);

        VBox vBoxContentBody = new VBox(messageLabel);

        // Crea il contenuto del popup
        VBox popupContent = new VBox(header, vBoxContentBody);
        popupContent.setFillWidth(true);
        popupContent.setMaxWidth(owner.getWidth() - 200);
        popupContent.setMaxHeight(owner.getHeight() - 600);
        popupContent.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-margin: 20px; ");

        // Aggiungi l'overlay e il contenuto al popup
        StackPane popupRoot = new StackPane(overlay, popupContent);
        popupRoot.setStyle(SETTING1); // Centra il contenuto del popup
        popup.getContent().add(popupRoot);

        // Mostra il popup
        popup.show(owner);

        // Close the popup after 5 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(popup::hide);
            }
        }, 3000);
    }

    public static void showErrorPopup(String message) {
        // Crea il popup
        Popup popup = new Popup();

        Stage owner = ApplicazioneStage.getStage();

        // Crea l'overlay nero
        Rectangle overlay = new Rectangle(owner.getWidth() - 5, owner.getHeight() - 5, Color.BLACK);
        overlay.setOpacity(0.3);

        // Crea il pulsante di chiusura
        Button closeButton = new Button("X");
        closeButton.setOnAction(e -> popup.hide());
        closeButton.setStyle("-fx-alignment: center-right;");

        Text title = new Text("Attenzione \t");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        title.setStyle(SETTING1);

        HBox header = new HBox(10, title, closeButton);
        header.setAlignment(Pos.CENTER);

        Label messageLabel = new Label();
        messageLabel.setText("\n" + message);
        messageLabel.setWrapText(true);

        VBox vBoxContentBody = new VBox(messageLabel);

        // Crea il contenuto del popup
        VBox popupContent = new VBox(header, vBoxContentBody);
        popupContent.setFillWidth(true);
        popupContent.setMaxWidth(owner.getWidth() - 200);
        popupContent.setMaxHeight(owner.getHeight() - 600);
        popupContent.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-margin: 20px; ");

        // Aggiungi l'overlay e il contenuto al popup
        StackPane popupRoot = new StackPane(overlay, popupContent);
        popupRoot.setStyle(SETTING1); // Centra il contenuto del popup
        popup.getContent().add(popupRoot);

        // Mostra il popup
        popup.show(owner);
    }

    public static void showEventDetailPopup(BeanEvento eventoBean, EventHandler partecipaHandler,
                                            EventHandler indicazioniHandler) {
        // Crea il popup
        Popup popup = new Popup();

        Stage owner = ApplicazioneStage.getStage();

        // Crea l'overlay nero
        Rectangle overlay = new Rectangle(owner.getWidth() - 5, owner.getHeight() - 5, Color.BLACK);
        overlay.setOpacity(0.3);

        // Crea il pulsante di chiusura
        Button closeButton = new Button("X");
        closeButton.setOnAction(e -> popup.hide());
        closeButton.setStyle("-fx-alignment: center-right;");

        Text title = new Text("Dettagli Evento \t");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        title.setStyle(SETTING1);

        HBox header = new HBox(10, title, closeButton);
        header.setAlignment(Pos.CENTER);

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

        Text nomeArtista = new Text(eventoBean.getNomeArtista());
        nomeArtista.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        HBox nome = new HBox(nomeArtista);
        nome.setPadding(new Insets(5, 0.0, 5, 0.0));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        HBox data = new HBox(new Text("Data: " + formatter.format(eventoBean.getData())));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        HBox orario = new HBox(new Text("Orario: " + sdf.format(eventoBean.getOrarioInizio()) + " - " + sdf.format(eventoBean.getOrarioFine())));
        HBox tipologia = new HBox(new Text("Tipologia: " + eventoBean.getTipoEvento()));

        Label descrizioneText = new Label(eventoBean.getDescrizione());
        descrizioneText.setWrapText(true);

        HBox descrizione = new HBox(descrizioneText);
        Button indicazioniButton = new Button("Indicazioni");
        indicazioniButton.setOnAction(e -> indicazioniHandler.handle(e));

        Button partecipaButton = new Button("Partecipa");
        partecipaButton.setOnAction(e -> partecipaHandler.handle(e));

        VBox boxInfo = new VBox(nome, data, orario, tipologia);
        boxInfo.setSpacing(6);

        HBox hBoxContentBody = new HBox(img, boxInfo);
        hBoxContentBody.setSpacing(40);
        hBoxContentBody.setPadding(new Insets(10, 0.0, 10, 0.0));

        HBox hBoxContentButtons = new HBox(indicazioniButton, partecipaButton);
        hBoxContentButtons.setSpacing(20);
        hBoxContentButtons.setPadding(new Insets(10, 0.0, 0, 0.0));
        hBoxContentButtons.setAlignment(Pos.BOTTOM_CENTER);

        // Crea il contenuto del popup
        VBox popupContent = new VBox(header, hBoxContentBody, descrizione, hBoxContentButtons);
        popupContent.setFillWidth(true);
        popupContent.setMaxWidth(owner.getWidth() - 200);
        popupContent.setMaxHeight(owner.getHeight() - 300);
        popupContent.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-margin: 20px; ");

        // Aggiungi l'overlay e il contenuto al popup
        StackPane popupRoot = new StackPane(overlay, popupContent);
        popupRoot.setStyle(SETTING1); // Centra il contenuto del popup
        popup.getContent().add(popupRoot);

        // Mostra il popup
        popup.show(owner);
    }

    public static int getGrafica() {
        return grafica;
    }

    public static void setGrafica(int grafica) {
        Utils.grafica = grafica;
    }

    public static void switchGrafica(){
        if(grafica==0){
            grafica=1;
            SCENEW = 1200;
            SCENEH = 700;
        }else{
            grafica=0;
            SCENEW = 414;
            SCENEH = 695;
        }
    }

}
