package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.bean.Coordinate;
import com.strart.model.domain.Evento;
import com.strart.model.domain.ListEvento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercaEventiDAO implements GenericDAO<ListEvento> {

    @Override
    public ListEvento execute(Object... params) throws DAOException, SQLException {

        Coordinate coordinate = (Coordinate) params[0];
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
                if (rs.next()) {
                    Evento evento = new Evento(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getTime(6), rs.getTime(7), rs.getBlob(8));
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
