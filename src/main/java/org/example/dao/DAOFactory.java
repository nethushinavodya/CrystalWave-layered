package org.example.dao;

import org.example.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        ROOM, ROOMTYPE, LOGIN, EMPLOYEE, DISCOUNT, INVENTORY
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case LOGIN:
                return new LoginDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case ROOMTYPE:
                return new RoomTypeDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case DISCOUNT:
                return new DiscountDAOImpl();
            default:
                return null;
        }
    }
}
