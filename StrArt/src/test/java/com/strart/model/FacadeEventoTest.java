package com.strart.model;

import com.strart.controller.GestisciEventiController;
import com.strart.model.bean.BeanEvento;
import com.strart.model.domain.FacadeEvento;
import com.strart.model.domain.ListEvento;
import com.strart.model.domain.Profile;
import javafx.fxml.LoadException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

//Realizzato da Valerio Torac
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FacadeEventoTest {
    GestisciEventiController gestisciEventi = null;
    FacadeEvento facadeEvento = null;


    @BeforeAll
    void setup() {
        gestisciEventi = new GestisciEventiController();
        facadeEvento = new FacadeEvento();
        Profile.setUsername("UtenteProva");
    }

    @Test
    void visualizzaEventiSuccessfull() {
        int numEVenti=0;
        //L'utente di prova visializza il numero di eventi da lui creati
        try {
            ListEvento listEvento = facadeEvento.visualizzaEventi();
            numEVenti = listEvento.getListaEvento().size();
        } catch (Exception e) {
            fail();
        }

        //creazione di un evento
        BeanEvento beanEvento = null;
        Date data= Date.valueOf("2030-07-17");
        Time orarioIn=Time.valueOf("17:29:00");
        Time orarioFine=Time.valueOf("21:00:00");

        try {
            beanEvento = new BeanEvento("Descrizione di prova: Ci sarà spazio per ballare, cantare insieme...",
                    null,
                    "via monte bianco 20",
                    data,
                    orarioIn,
                    orarioFine,
                    "musicale");
            beanEvento.setPersistenza("Database");
        } catch (LoadException e) {
            fail();
        }

        //inserimento dell'evento
        try {
            gestisciEventi.creaEvento(beanEvento);
        } catch (Exception e) {
            fail();
        }

        //visualizzazione degli eventi
        try {
            ListEvento listEvento = facadeEvento.visualizzaEventi();
            assertEquals(numEVenti+1,listEvento.getListaEvento().size());
        } catch (Exception e) {
            fail();
        }


        //ora eliminiamo l'evento
        try {
            gestisciEventi.eliminaEvento(beanEvento);
        } catch (Exception e) {
            fail();
        }


    }
}
