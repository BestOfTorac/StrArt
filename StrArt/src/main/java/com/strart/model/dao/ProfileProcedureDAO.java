package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.*;

import java.sql.*;

public class ProfileProcedureDAO  {

    public void recuperaProfilo() throws DAOException, SQLException {
        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call recuperaProfilo(?)}");
            cs.setString(1, Credentials.getUsername());

            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {

                    Profile.setUsername(rs.getString(1));
                    Profile.setNome(rs.getString(2));
                    Profile.setCognome(rs.getString(3));
                    Profile.setDataNascita(rs.getDate(4));
                    Profile.setNomeDArte(rs.getString(5));
                    Profile.setDescrizione(rs.getString(6));
                    Profile.setValutazione(rs.getFloat(7));
                    Profile.setNumEventi(rs.getInt(8));

                }
            }

        } catch (SQLException e) {
            throw new DAOException("recupera profilo error: " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }

        }
    }
}
