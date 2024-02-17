package com.strart.model.dao;

import com.strart.exception.DAOException;

import java.sql.SQLException;

public interface GenericDAO<P>  {
    P execute(Object... params) throws DAOException, SQLException;
}
