package com.strart.controller;

import com.strart.model.bean.BeanEvento;
import com.strart.model.bean.IndirizzoBean;
import com.strart.model.domain.Profile;
import javafx.fxml.LoadException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GestisciEventiControllerTest {

    GestisciEventiController gestisciEventi = null;
    OttieniIndicazioniController indicazioni = null;


    @BeforeAll
    void setup() {
        gestisciEventi = new GestisciEventiController();
        indicazioni = new OttieniIndicazioniController();
        Profile.setUsername("UtenteProva");
        Profile.setNomeDArte("Provas");
    }

    @Test
    void creaEventoSuccessfull() {


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


        try {
            gestisciEventi.creaEvento(beanEvento);
        } catch (Exception e) {
            fail();
        }

        IndirizzoBean indirizzoBean= new IndirizzoBean(beanEvento.getIndirizzo());

        //creao l'evento che voglio cercare ovvero quello inserito precedentemente
        BeanEvento mioEvento= new BeanEvento("Provas", data,orarioIn);

        BeanEvento beanEventoVerifica;


        try {
            //cerco gli eventi di quel specifico indirizzo
            indicazioni.cercaEventi(indirizzoBean);

            //ora cerco il mio evento
            beanEventoVerifica= indicazioni.ottieniEvento(mioEvento);

            //verifico le condizioni
            assertEquals("Provas", beanEventoVerifica.getNomeArtista());
            assertEquals(data, beanEventoVerifica.getData());
            assertEquals(orarioIn, beanEventoVerifica.getOrarioInizio());
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
