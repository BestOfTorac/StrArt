package com.strart.model;
import com.strart.model.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FacadeOttieniIndicazioniTest {
    FacadeOttieniIndicazioni facadeOttieniIndicazioni = null;
    FactoryEvento factoryEvento = null;


    @BeforeAll
    void setup() {
        facadeOttieniIndicazioni = new FacadeOttieniIndicazioni();
        factoryEvento= new FactoryEvento();
        Credentials.setUsername("UtenteProva");
    }

    @Test
    void partecipazioneEventoSuccessfull() {


        Date data= Date.valueOf("2026-09-06");
        Time orarioIn=Time.valueOf("17:30:00");

        Evento evento = factoryEvento.createEvento("musicale");
        evento.setNomeArtista("UrloDelMomo");
        evento.setData(data);
        evento.setOrarioInizio(orarioIn);


        //L'utente di prova visializza il numero di eventi da lui creati
        try {
            facadeOttieniIndicazioni.partecipaEvento(evento);
        } catch (Exception e) {
            fail();
        }

        try {
            facadeOttieniIndicazioni.partecipaEvento(evento);
        } catch (Exception e) {
            String fullMessage = e.getMessage();
            String errorMessage = fullMessage.substring(fullMessage.lastIndexOf(":") + 1).trim();
            assertEquals("1062", errorMessage);
        }

        try {
            facadeOttieniIndicazioni.eliminaPartecipazioneEvento(evento);
        } catch (Exception e) {
            fail();
        }



    }
}
