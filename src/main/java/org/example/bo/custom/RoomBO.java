package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AddItemDTO;
import org.example.dto.AddRoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    String getCurrentRoomId() throws SQLException, ClassNotFoundException;

    List<AddRoomDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String i) throws SQLException, ClassNotFoundException;

    boolean save(AddRoomDTO addRoomDTO) throws SQLException, ClassNotFoundException;

    boolean update(AddRoomDTO addRoomDTO) throws SQLException, ClassNotFoundException;

    boolean isAdd(AddItemDTO addItemDTO) throws SQLException, ClassNotFoundException;

    List<String> getDeactiveRooms() throws SQLException, ClassNotFoundException;

    boolean checkOut(String roomId) throws SQLException, ClassNotFoundException;

    List<String> getActiveRoom() throws SQLException, ClassNotFoundException;
}
