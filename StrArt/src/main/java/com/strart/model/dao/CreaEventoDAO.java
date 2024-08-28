package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Evento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class CreaEventoDAO {

    public void creaEventoOnDB(Object... params) throws DAOException, SQLException {
        String username= (String) params[0];
        Evento evento= (Evento) params[1];

        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call creaEvento(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, username);
            cs.setDate(2, evento.getData());
            cs.setTime(3, evento.getOrarioInizio());
            cs.setTime(4, evento.getOrarioFine());
            cs.setBlob(5, evento.getImmagine());
            cs.setString(6, evento.getDescrizione());
            cs.setString(7, evento.getLatitudine());
            cs.setString(8, evento.getLongitudine());
            cs.setString(9, "programmato");
            cs.setString(10, evento.getTipo());
            cs.executeQuery();


        } catch(SQLException e) {
            throw new DAOException("creaEventoDao error: " + e.getMessage());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }

    }
}
