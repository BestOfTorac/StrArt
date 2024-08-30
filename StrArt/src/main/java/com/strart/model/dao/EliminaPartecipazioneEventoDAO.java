package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Evento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EliminaPartecipazioneEventoDAO {

    //metodo per l'eliminazione della partecipazione ad un evento dal db
    public void eliminaPartecipazioneEventoToDb(String username, Evento evento) throws DAOException, SQLException {

        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call eliminaPartecipazioneEvento(?,?,?,?)}");
            cs.setString(1, username);
            cs.setDate(3, evento.getData());
            cs.setTime(4, evento.getOrarioInizio());
            cs.setString(2, evento.getNomeArtista());
            cs.executeQuery();


        } catch(SQLException e) {
            throw new DAOException("eliminaPartecipazioneEvento: " + e.getErrorCode());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }

    }
}
