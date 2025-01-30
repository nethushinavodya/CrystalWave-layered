package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.RoomTypeDTO;
import org.example.dto.tm.RoomTypeTm;

import java.sql.SQLException;
import java.util.List;

public interface RoomTypeBO extends SuperBO {
    String getCurrentTypeId() throws SQLException, ClassNotFoundException;

    List<RoomTypeTm> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String roomNumber) throws SQLException, ClassNotFoundException;

    boolean save(RoomTypeDTO roomTypeDTO) throws SQLException, ClassNotFoundException;

    boolean update(RoomTypeDTO roomTypeDTO) throws SQLException, ClassNotFoundException;
}
