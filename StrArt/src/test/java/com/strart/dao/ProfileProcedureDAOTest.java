package com.strart.dao;

import com.strart.exception.DAOException;
import com.strart.model.dao.ProfileProcedureDAO;
import com.strart.model.domain.Credentials;
import com.strart.model.domain.Profile;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileProcedureDAOTest {

    @Test
    void profileSuccessfull() {
        Credentials.setUsername("UtenteProva");
        Profile.setNome(null);

        try {
            new ProfileProcedureDAO().execute();
        } catch(DAOException | SQLException e) {
            fail();
        }
        Date data= Date.valueOf("2000-01-10");
        assertEquals("Prova", Profile.getNome());
        assertEquals("Prova", Profile.getCognome());
        assertEquals(data, Profile.getDataNascita());
        assertEquals("Provas", Profile.getNomedArte());
        assertEquals("Provaaaaa", Profile.getDescrizione());

    }

    @Test
    void profileUnsuccessfull() {
        Credentials.setUsername("UtenteProva2");
        Profile.setNome(null);

        try {
            new ProfileProcedureDAO().execute();
        } catch(Exception e) {
            fail();
        }

        assertNull(Profile.getNome());

    }



}