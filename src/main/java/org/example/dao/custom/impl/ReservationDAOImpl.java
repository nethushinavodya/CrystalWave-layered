package org.example.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.dao.SQLUtil;
import org.example.dao.custom.ReservationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ObservableList<String> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from Reservation ");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rst.next()){
            String id = rst.getString(1);
            list.add(id);
        }
        return FXCollections.observableList(list);
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
    public String search(String roomId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from Room where Room_Id=?",roomId);
        if (rst.next()){
            String type = rst.getString(1);
            return type;
        }
        return null;
    }
}
