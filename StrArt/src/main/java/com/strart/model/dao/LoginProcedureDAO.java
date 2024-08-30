package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Credentials;
import com.strart.model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO{

    //metodo per la validazione delle credenziali
    public void login() throws DAOException, SQLException {
        int role;
        CallableStatement cs = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call login(?,?,?)}");
            cs.setString(1, Credentials.getUsername());
            cs.setString(2, Credentials.getPassword());
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.executeQuery();
            role = cs.getInt(3);
            Credentials.setRole(Role.fromInt(role));
        } catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }finally {
            if(cs!= null){
                cs.close();
            }

        }

    }
}
