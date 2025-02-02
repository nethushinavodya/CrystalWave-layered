package org.example.bo.custom.impl;

import javafx.collections.ObservableList;
import org.example.bo.custom.ReservtionBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ReservationDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservtionBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);
    @Override
    public ObservableList<String> getAll() throws SQLException, ClassNotFoundException {
        return reservationDAO.getAll();
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return reservationDAO.getCurrentId();
    }

    @Override
    public String search(String roomId) throws SQLException, ClassNotFoundException {
        return reservationDAO.search(roomId);
    }
}
