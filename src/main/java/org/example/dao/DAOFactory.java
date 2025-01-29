package org.example.dao;

import org.example.dao.custom.impl.LoginDAOImpl;
import org.example.dao.custom.impl.RoomTypeDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        ROOM, ROOMTYPE, LOGIN
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case LOGIN:
                return new LoginDAOImpl();
            case ROOM:
                return new RoomTypeDAOImpl();
            default:
                return null;
        }
    }
}
