package com.strart.model.dao;

import com.strart.model.domain.Evento;
import com.strart.model.domain.ListEvento;

import java.io.*;

public class VisualizzaEventiFileSystem {

    //metodo per recuperare gli eventi di un artista dal file system
    public ListEvento recuperaEventiDaFile(ListEvento eventi, String nomeFile) {


        try (FileInputStream fis = new FileInputStream(nomeFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Evento evento = null;
            // Continua a leggere finché riesci a deserializzare oggetti
            while ((evento = (Evento) ois.readObject()) != null) {
                eventi.addEvento(evento);
            }

        } catch (EOFException | FileNotFoundException e) {
            // La EOFException è normale, significa che siamo alla fine del file
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        return eventi;
    }

}
