package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.bean.Coordinate;
import com.strart.model.domain.*;

import java.sql.*;

public class ProfileProcedureDAO  {

    public void execute(Object... params) throws DAOException, SQLException {
        Credentials cred = (Credentials) params[0];
        Profile profilo;
        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call recuperaProfilo(?)}");
            cs.setString(1, cred.getUsername());

            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {
                    profilo = new Profile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getFloat(7), rs.getInt(8));

                }
            }

        } catch (SQLException e) {
            throw new DAOException("Listino eventi error: " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }

        }
    }
}