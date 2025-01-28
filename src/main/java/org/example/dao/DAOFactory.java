package org.example.dao;

import org.example.dao.custom.impl.LoginDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        LOGIN
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }
}
