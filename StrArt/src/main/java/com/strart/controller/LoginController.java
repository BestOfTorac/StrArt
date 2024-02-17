package com.strart.controller;

import com.strart.ApplicationStrArt;
import com.strart.exception.DAOException;
import com.strart.model.bean.CredentialsBean;
import com.strart.model.dao.LoginProcedureDAO;
import com.strart.model.domain.ApplicazioneSrage;
import com.strart.model.domain.Credentials;
import com.strart.view.OttieniIndicazioniControllerGrafico;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{


    public void start(CredentialsBean credB) throws DAOException, IOException {

        Credentials cred= new Credentials(credB.getUsername(),credB.getPassword());

        try {
            new LoginProcedureDAO().execute(cred);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneSrage.getStage();
        Scene scene;
        if(cred.getRole() == null) {
            fxmlLoader = new FXMLLoader(ApplicationStrArt.class.getResource("login.fxml"));
            scene = new Scene(fxmlLoader.load(), 414, 795);
        }else{
            String fxmlFile = "/com/strart/hello-view.fxml";
            fxmlLoader = new FXMLLoader();
            Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
            final OttieniIndicazioniControllerGrafico controller = fxmlLoader.getController();
            controller.initMapAndControls();
            scene = new Scene(rootNode, 414, 795);
        }
        System.out.println(cred.getRole());


        stage.setTitle("StrArt");
        stage.setScene(scene);
        stage.show();

    }

    /*
    public BeanEventi cercaEventi() throws DAOException {

        ListEvento listEvento;
        listEvento=new CercaEventiDAO().execute(indirizzo);


    }

     */

}
