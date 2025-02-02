package org.example.bo.custom;

import javafx.collections.ObservableList;
import org.example.bo.SuperBO;

import java.sql.SQLException;

public interface ReservtionBO extends SuperBO {
    ObservableList<String> getAll() throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;

    String search(String roomId) throws SQLException, ClassNotFoundException;
}
