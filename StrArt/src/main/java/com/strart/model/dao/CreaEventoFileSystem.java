package com.strart.model.dao;

import com.strart.model.domain.Evento;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreaEventoFileSystem {

    //metodo per la creazione di un evento nel file system
    public void salvaEventoSuFile(Evento evento, String nomeFile) {
        try (FileOutputStream fos = new FileOutputStream(nomeFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(evento);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }



}
