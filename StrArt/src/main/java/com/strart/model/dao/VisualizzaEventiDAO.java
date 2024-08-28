package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Evento;
import com.strart.model.domain.FactoryEvento;
import com.strart.model.domain.ListEvento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizzaEventiDAO implements GenericDAO<ListEvento> {

    @Override
    public ListEvento execute(Object... params) throws DAOException, SQLException {

        String username = (String) params[0];
        ListEvento listEvento= new ListEvento();
        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call visualizzaEventi(?)}");
            cs.setString(1, username);

            boolean status = cs.execute();
            if(status) {
                ResultSet rs = cs.getResultSet();
                FactoryEvento factoryEvento= new FactoryEvento();
                while (rs.next()) {
                    Evento evento= factoryEvento.createEvento(rs.getString(8));
                    evento.setDescrizione(rs.getString(1));
                    evento.setImmagine(rs.getBlob(7));
                    evento.setLatitudine(rs.getString(2));
                    evento.setLongitudine(rs.getString(3));
                    evento.setData(rs.getDate(4));
                    evento.setOrarioInizio(rs.getTime(5));
                    evento.setOrarioFine(rs.getTime(6));
                    evento.setStato(rs.getString(9));

                    listEvento.addEvento(evento);
                }
            }

        } catch (SQLException e) {
            //lancio un messaggio di errore
            throw new DAOException("Listino eventi in VisualizzaEventiDAO error: " + e.getMessage());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }


        return listEvento;
    }


}
