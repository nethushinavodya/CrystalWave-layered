package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.LoginDAO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAOImpl implements LoginDAO {
    @Override
    public User getUserByEmailAndRole(String email) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM users WHERE email = ?",email);
        if (rst.next()){
            return new User(
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            );
        }
        return null;
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO users VALUES (?,?,?,?,?,?,?)",user.getFirstName(),user.getLastName(),user.getPhoneNumber(),user.getAddress(),user.getEmail(),user.getPassword(),user.getRole());
    }

    @Override
    public boolean updateUserPassword(UserDTO userModel, String newPassword) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE users SET password = ? WHERE email = ?",newPassword,userModel.getEmail());
    }
}
