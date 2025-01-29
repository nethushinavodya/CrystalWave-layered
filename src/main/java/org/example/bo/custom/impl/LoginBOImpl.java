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
        System.out.println(user.getEmail()+user.getPassword());
        return new UserDTO(user.getFirstName(),user.getLastName(), user.getPhoneNumber(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRole());

    }

    @Override
    public boolean saveUser(UserDTO userdto) throws SQLException, ClassNotFoundException {
        return loginDAO.save(new User(userdto.getFirstName(), userdto.getLastName(), userdto.getPhoneNumber(), userdto.getAddress(), userdto.getEmail(), userdto.getPassword(), userdto.getRole()));
    }

    @Override
    public boolean updateUserPassword(UserDTO userModel, String newPassword) throws SQLException, ClassNotFoundException {
        return loginDAO.updateUserPassword(userModel, newPassword);
    }
}
