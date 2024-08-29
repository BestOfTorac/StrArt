package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Evento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PartecipaEventoDAO {

    public void partecipaEventoToDb(String username, Evento evento) throws DAOException, SQLException {

        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call partecipaEvento(?,?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, evento.getNomeArtista());
            cs.setDate(3, evento.getData());
            cs.setTime(4, evento.getOrarioInizio());
            cs.executeQuery();


        } catch(SQLException e) {
            throw new DAOException("partecipaEvento: " + e.getErrorCode());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }

    }
}
