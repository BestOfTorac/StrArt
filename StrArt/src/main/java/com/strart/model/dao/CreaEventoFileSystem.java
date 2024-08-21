package com.strart.model.dao;

import com.strart.model.domain.Evento;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreaEventoFileSystem {

    public void salvaEventoSuFile(Evento evento, String nomeFile) {
        try (FileOutputStream fos = new FileOutputStream(nomeFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(evento);
            System.out.println("Evento salvato con successo nel file " + nomeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
