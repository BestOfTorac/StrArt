package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Evento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class EliminaEventoDAO {

    public void eliminaEventoOnDB(String username, Evento evento) throws DAOException, SQLException {

        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call eliminaEvento(?,?,?)}");
            cs.setString(1, username);
            cs.setDate(2, evento.getData());
            cs.setTime(3, evento.getOrarioInizio());
            cs.executeQuery();


        } catch(SQLException e) {
            throw new DAOException("eliminaEventoDao error: " + e.getMessage());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }

    }
}
