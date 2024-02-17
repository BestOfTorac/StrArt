package com.strart.model.dao;

import com.strart.exception.DAOException;
import com.strart.model.domain.Credentials;
import com.strart.model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO{

    public void execute(Object... params) throws DAOException {
        Credentials cred= (Credentials) params[0];
        int role;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call login(?,?,?)}");
            cs.setString(1, cred.getUsername());
            cs.setString(2, cred.getPassword());
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.executeQuery();
            role = cs.getInt(3);
            cred.setRole(Role.fromInt(role));
        } catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }

    }
}
