package com.strart.controller;

import com.strart.ApplicationStrArt;
import com.strart.exception.DAOException;
import com.strart.model.bean.CredentialsBean;
import com.strart.model.dao.LoginProcedureDAO;
import com.strart.model.domain.ApplicazioneSrage;
import com.strart.model.domain.Credentials;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{


    public void start(CredentialsBean credB) throws DAOException, IOException {
        /*
        Credentials cred= new Credentials(credB.getUsername(),credB.getPassword());

        try {
            new LoginProcedureDAO().execute(cred);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

         */

        Stage stage = ApplicazioneSrage.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStrArt.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 414, 795);
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
