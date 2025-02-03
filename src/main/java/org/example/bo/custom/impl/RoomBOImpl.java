package org.example.bo.custom.impl;

import org.example.bo.custom.RoomBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.RoomDAO;
import org.example.dto.AddItemDTO;
import org.example.dto.AddRoomDTO;
import org.example.entity.AddRoom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM);
    @Override
    public String getCurrentRoomId() throws SQLException, ClassNotFoundException {
        return roomDAO.getCurrentRoomId();
    }

    @Override
    public List<AddRoomDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<AddRoomDTO> addRoomDTOS = new ArrayList<>();
        List<AddRoom> addRooms = roomDAO.getAll();
        for (AddRoom addRoom : addRooms) {
            addRoomDTOS.add(new AddRoomDTO(addRoom.getRoomId(), addRoom.getRoomNumber(), addRoom.getRoomStatus(), addRoom.getRoomTypeId()));
        }
        return addRoomDTOS;
    }

    @Override
    public boolean delete(String i) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(i);
    }

    @Override
    public boolean save(AddRoomDTO e) throws SQLException, ClassNotFoundException {
        return roomDAO.save(new AddRoom(e.getRoomId(), e.getRoomNumber(), e.getRoomStatus(), e.getRoomTypeId()));
    }

    @Override
    public boolean update(AddRoomDTO e) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new AddRoom(e.getRoomId(), e.getRoomNumber(), e.getRoomStatus(), e.getRoomTypeId()));
    }

    @Override
    public boolean isAdd(AddItemDTO addItemDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.isAdd(addItemDTO);
    }

    @Override
    public List<String> getDeactiveRooms() throws SQLException, ClassNotFoundException {
        return roomDAO.getDeactiveRooms();
    }

    @Override
    public boolean checkOut(String roomId) throws SQLException, ClassNotFoundException {
        return roomDAO.checkOut(roomId);
    }

    @Override
    public List<String> getActiveRoom() throws SQLException, ClassNotFoundException {
        return roomDAO.getActiveRoom();
    }
}
