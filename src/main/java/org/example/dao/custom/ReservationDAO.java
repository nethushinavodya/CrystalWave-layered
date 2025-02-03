package org.example.dao.custom;

import javafx.collections.ObservableList;
import org.example.dao.CrudDAO;
import org.example.dto.ReservationDTO;
import org.example.dto.RoomReserveDTO;
import org.example.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation> {
    ObservableList<String> getAllids()throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    String searchType(String roomId) throws SQLException, ClassNotFoundException;

    boolean saveReservation1(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    boolean saved(ArrayList<RoomReserveDTO> roomReserves) throws SQLException, ClassNotFoundException;
}
