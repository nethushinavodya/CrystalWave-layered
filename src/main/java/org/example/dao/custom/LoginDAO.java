package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.User;

import java.sql.SQLException;

public interface LoginDAO extends CrudDAO<User> {
    User getUserByEmailAndRole(String email) throws SQLException, ClassNotFoundException;
}
