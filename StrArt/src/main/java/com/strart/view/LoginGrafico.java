package com.strart.view;

import com.strart.controller.LoginController;
import com.strart.exception.DAOException;
import com.strart.model.bean.CredentialsBean;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginGrafico {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPassword;

    @FXML
    protected void onHelloButtonClick(){
        CredentialsBean credB;

        credB= new CredentialsBean(textFieldUsername.getText(), textFieldPassword.getText());
        try{
            LoginController loginController= new LoginController();
            loginController.start(credB);
        }catch (DAOException e){
            System.out.println("Credenziali non valide");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //callApi(textField.getText());
    }

}