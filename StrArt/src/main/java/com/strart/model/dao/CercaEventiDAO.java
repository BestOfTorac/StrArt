package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Coordinate;
import com.strart.model.domain.Evento;
import com.strart.model.domain.FactoryEvento;
import com.strart.model.domain.ListEvento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercaEventiDAO {

    //metodo per la ricerca degli eventi dal db
    public ListEvento cercaEventiToDb(Coordinate coordinate) throws DAOException, SQLException {

        ListEvento listEvento= new ListEvento();
        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call cercaEventi(?,?)}");
            cs.setString(1, coordinate.getLongitudine());
            cs.setString(2,  coordinate.getLatitudine());

            boolean status = cs.execute();
            if(status) {
                ResultSet rs = cs.getResultSet();
                FactoryEvento factoryEvento= new FactoryEvento();
                while (rs.next()) {

                    Evento evento= factoryEvento.createEvento(rs.getString(9));
                    evento.setNomeArtista(rs.getString(1));
                    evento.setDescrizione(rs.getString(2));
                    evento.setLatitudine(rs.getString(3));
                    evento.setLongitudine(rs.getString(4));
                    evento.setData(rs.getDate(5));
                    evento.setOrarioInizio(rs.getTime(6));
                    evento.setOrarioFine(rs.getTime(7));
                    evento.setImmagine(rs.getBlob(8));
                    evento.setStato(rs.getString(10));


                    listEvento.addEvento(evento);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Listino eventi error: " + e.getMessage());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }


        return listEvento;
    }


}
