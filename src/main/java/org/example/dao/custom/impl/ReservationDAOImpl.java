package org.example.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.dao.DAOFactory;
import org.example.dao.SQLUtil;
import org.example.dao.custom.ReservationDAO;
import org.example.dao.custom.RoomDAO;
import org.example.dto.ReservationDTO;
import com.example.layeredarchitecture.db.DBConnection;
import org.example.dto.RoomReserveDTO;
import org.example.entity.AddGuest;
import org.example.entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM);
    @Override
    public ObservableList<String> getAllids() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from Reservation ");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rst.next()){
            String id = rst.getString(1);
            list.add(id);
        }
        return FXCollections.observableList(list);
    }

    @Override
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Reservation_Id \n" +
                "FROM Reservation\n" +
                "ORDER BY Reservation_Id DESC \n" +
                "LIMIT 1;");
        while (rst.next()){
            String id = rst.getString(1);
            return id;
        }
        return null;
    }

    @Override
    public String searchType(String roomId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from Room where Room_Id=?",roomId);

        while (resultSet.next()) {
            String roomType  = resultSet.getString(4);
            return roomType ;
        }
        return null;
    }

    @Override
    public boolean saveReservation1(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Reservation VALUES (?,?,?,?,?)");
        stm.setObject(1, reservationDTO.getReservationId());
        stm.setObject(2, reservationDTO.getGuestId());
        stm.setObject(3, reservationDTO.getRoomId());
        stm.setObject(4, reservationDTO.getCheckInDate());
        stm.setObject(5, reservationDTO.getCheckOutDate());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean saved(ArrayList<RoomReserveDTO> roomReserves) throws SQLException, ClassNotFoundException {

        for (RoomReserveDTO roomReserve : roomReserves) {
            System.out.println("ertjk");
            System.out.println( roomReserves);
            boolean isSaved = save1(roomReserve.getRoomId(),roomReserve.getReservationId());
            if (!isSaved) {
                return false;

            }
            boolean isUpdate1 = roomDAO.updateAvailable(roomReserve.getRoomId());
            if (!isUpdate1) {
                return false;
            }
        }
        return true;
    }

    private boolean save1(String roomId, String reservationId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Reservation_Room VALUES (?,?)", reservationId,roomId);
    }
}
