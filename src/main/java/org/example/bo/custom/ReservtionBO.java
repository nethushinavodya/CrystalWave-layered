package org.example.bo.custom;

import javafx.collections.ObservableList;
import org.example.bo.SuperBO;
import org.example.dto.ReservationDTO;

import java.sql.SQLException;

public interface ReservtionBO extends SuperBO {
    ObservableList<String> getAll() throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    String search(String roomId) throws SQLException, ClassNotFoundException;

    boolean saveReservation(ReservationDTO reservationDTO, double total, String paymentId, String paymentmethod, String disId) throws SQLException, ClassNotFoundException;
}
