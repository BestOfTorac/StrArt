package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.ListEvento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercaEventiDAO implements GenericDAO<ListEvento> {

    @Override
    public ListEvento execute(Object... params) throws DAOException {

        String partitaIva = (String) params[0];
        ListEvento listEvento= new ListEvento();
        /*
        Ordine ordine = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call crea_ordine(?)}");
            cs.setLong(1, partitaIva);

            boolean status = cs.execute();
            if(status) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {
                    ordine = new Ordine(rs.getTimestamp(1), partitaIva, "ordinato");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Crea ordine error: " + e.getMessage());
        }
        */

        return listEvento;
    }


}
