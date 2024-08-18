package com.strart.controller;

import com.strart.ApplicationStrArt;
import com.strart.exception.DAOException;
import com.strart.model.bean.CredentialsBean;
import com.strart.model.dao.ConnectionFactory;
import com.strart.model.dao.LoginProcedureDAO;
import com.strart.model.dao.ProfileProcedureDAO;
import com.strart.model.domain.ApplicazioneStage;
import com.strart.model.domain.Credentials;
import com.strart.view.OttieniIndicazioniControllerGrafico;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController{


    public void start(CredentialsBean credB) throws DAOException, IOException {

        Credentials cred= new Credentials(credB.getUsername(),credB.getPassword());

        try {
            new LoginProcedureDAO().execute(cred);
        } catch(DAOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }

        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;
        if(cred.getRole() == null) {
            fxmlLoader = new FXMLLoader(ApplicationStrArt.class.getResource("login.fxml"));
            scene = new Scene(fxmlLoader.load(), 414, 795);
        }else{
            String fxmlFile;

            try {
                ConnectionFactory.changeRole(cred.getRole());
            } catch(SQLException e) {
                throw new IllegalArgumentException(e);
            }

            if(cred.getRole().getId() == 1) {
                System.out.println("Artistaa");

                try {
                    new ProfileProcedureDAO().execute(cred);
                } catch(DAOException | SQLException e) {
                    throw new IllegalArgumentException(e);
                }

                fxmlFile = "/com/strart/artistiview.fxml";
            }else{
                System.out.println("Spettatore");
                fxmlFile = "/com/strart/hello-view.fxml";
            }
            fxmlLoader = new FXMLLoader();
            Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
            final OttieniIndicazioniControllerGrafico controller = fxmlLoader.getController();
            controller.initMapAndControls("41.9028","12.4964","city");
            scene = new Scene(rootNode, 414, 795);
        }

        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();

    }

}
