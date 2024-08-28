package com.strart.controller;

import com.strart.ApplicationStrArt;
import com.strart.exception.DAOException;
import com.strart.model.bean.CredentialsBean;
import com.strart.model.dao.ConnectionFactory;
import com.strart.model.dao.LoginProcedureDAO;
import com.strart.model.dao.ProfileProcedureDAO;
import com.strart.model.domain.ApplicazioneStage;
import com.strart.model.domain.Credentials;
import com.strart.utils.Utils;
import com.strart.view.OttieniIndicazioniControllerGrafico;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    public void start(CredentialsBean credB) throws DAOException, IOException {

        Credentials.setUsername(credB.getUsername());
        Credentials.setPassword(credB.getPassword());
        Credentials.setRole(null);

        try {
            new LoginProcedureDAO().execute();
        } catch(DAOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }

        FXMLLoader fxmlLoad;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;

        if (Credentials.getRole() == null) {
            fxmlLoad = new FXMLLoader(ApplicationStrArt.class.getResource("login.fxml"));
            scene = new Scene(fxmlLoad.load(), Utils.getSceneW(), Utils.getSceneH());
        } else {
            String fxmlFile;

            try {
                ConnectionFactory.changeRole(Credentials.getRole());
            } catch(SQLException e) {
                throw new IllegalArgumentException(e);
            }

            if (Credentials.getRole().getId() == 1) {

                try {
                    new ProfileProcedureDAO().execute();
                } catch(DAOException | SQLException e) {
                    throw new IllegalArgumentException(e);
                }

                fxmlFile = "/com/strart/artistiview.fxml";
            } else {

                fxmlFile = "/com/strart/utente-view.fxml";
            }

            fxmlLoad = new FXMLLoader();
            Parent rootNode = fxmlLoad.load(getClass().getResourceAsStream(fxmlFile));
            final OttieniIndicazioniControllerGrafico controller = fxmlLoad.getController();
            controller.initMapAndControls("41.9028","12.4964","city");
            scene = new Scene(rootNode, Utils.getSceneW(), Utils.getSceneH());

            // Rimuovere il focus dal TextField
            scene.getRoot().requestFocus();
        }

        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();
    }
}
