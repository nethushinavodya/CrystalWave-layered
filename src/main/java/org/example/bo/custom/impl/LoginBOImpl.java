package org.example.bo.custom.impl;

import org.example.bo.custom.LoginBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.LoginDAO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
    @Override
    public UserDTO getUserByEmailAndRole(String email) throws SQLException, ClassNotFoundException {
        User user = loginDAO.getUserByEmailAndRole(email);
        return new UserDTO(user.getFirstName(),user.getLastName(), user.getPhoneNumber(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
