package org.example.dao.custom;

import javafx.collections.ObservableList;
import org.example.dao.CrudDAO;
import org.example.entity.Reservation;

import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<Reservation> {
    ObservableList<String> getAll()throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    String search(String roomId) throws SQLException, ClassNotFoundException;
}
