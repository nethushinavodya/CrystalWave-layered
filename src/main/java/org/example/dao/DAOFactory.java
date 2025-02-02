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
        ROOM, ROOMTYPE, LOGIN, EMPLOYEE, DISCOUNT, CHECKINDETAIL, GUEST, INVENTORY, SERVICE, RESERVATION, BILL
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
            case CHECKINDETAIL:
                return new CheckInDetailDAOImpl();
            case GUEST:
                return new GuestDAOImpl();
            case BILL:
                return new BillDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }
}
