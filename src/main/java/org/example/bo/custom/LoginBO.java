package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {


    UserDTO getUserByEmailAndRole(String email) throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDTO userdto) throws SQLException, ClassNotFoundException;

    boolean updateUserPassword(UserDTO userModel, String newPassword) throws SQLException, ClassNotFoundException;
}
