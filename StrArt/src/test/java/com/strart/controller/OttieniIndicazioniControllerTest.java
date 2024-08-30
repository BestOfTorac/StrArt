package com.strart.controller;

import com.strart.model.bean.BeanEventi;
import com.strart.model.bean.IndirizzoBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

//Realizzato da Andrea Bellini
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OttieniIndicazioniControllerTest {

    OttieniIndicazioniController indicazioni = null;

    @BeforeAll
    void setup() {
        indicazioni = new OttieniIndicazioniController();
    }

    @Test
    void cercaEventiSuccessfull() {
        
        IndirizzoBean indirizzoB = new IndirizzoBean("via dei pini 00013");
        BeanEventi eventi = null;

        try {
            eventi = indicazioni.cercaEventi(indirizzoB);
        } catch (Exception e) {
            fail();
        }

        assertNotEquals(null, eventi);
    }


    @Test
    void cercaEventiAddressNotFound() {

        IndirizzoBean indirizzoB = new IndirizzoBean("address not found");
        BeanEventi eventi = null;

        try {
            eventi = indicazioni.cercaEventi(indirizzoB);
        } catch(IllegalArgumentException e) {
            assertEquals("L'indirizzo inserito non è valido", e.getMessage());
        } catch (Exception e) {
            fail();
        }

        assertEquals(null, eventi);
    }
}