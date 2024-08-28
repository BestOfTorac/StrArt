package com.strart.dao;

import com.strart.exception.DAOException;
import com.strart.model.dao.LoginProcedureDAO;
import com.strart.model.domain.Credentials;
import com.strart.model.domain.Role;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LoginProcedureDAOTest {

    @Test
    void loginSuccessfullArtista() {
        Credentials.setUsername("Momo");
        Credentials.setPassword("Momo");
        Credentials.setRole(null);

        try {
            new LoginProcedureDAO().execute();
        } catch(DAOException | SQLException e) {
            fail();
        }

        assertEquals(Role.ARTISTA, Credentials.getRole());
    }

    @Test
    void loginSuccessfullSpettatore() {
        Credentials.setUsername("Ali");
        Credentials.setPassword("Ali");
        Credentials.setRole(null);

        try {
            new LoginProcedureDAO().execute();
        } catch(DAOException | SQLException e) {
            fail();
        }

        assertEquals(Role.SPETTATORE, Credentials.getRole());
    }

    @Test
    void loginUnsuccessfull() {
        Credentials.setUsername("Momo1");
        Credentials.setPassword("Momo1");
        Credentials.setRole(null);

        try {
            new LoginProcedureDAO().execute();
        } catch(Exception e) {
           fail();
        }

        assertEquals(null, Credentials.getRole());
    }

}