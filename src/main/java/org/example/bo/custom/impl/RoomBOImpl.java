package org.example.bo.custom.impl;

import org.example.bo.custom.RoomBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.RoomDAO;
import org.example.dto.AddItemDTO;
import org.example.dto.AddRoomDTO;

import java.sql.SQLException;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM);
    @Override
    public String getCurrentRoomId() throws SQLException, ClassNotFoundException {
        return roomDAO.getCurrentRoomId();
    }

    @Override
    public List<AddRoomDTO> getAll() throws SQLException, ClassNotFoundException {
        return roomDAO.getAll();
    }

    @Override
    public boolean delete(String i) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(i);
    }

    @Override
    public boolean save(AddRoomDTO addRoomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.save(addRoomDTO);
    }

    @Override
    public boolean update(AddRoomDTO addRoomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.update(addRoomDTO);
    }

    @Override
    public boolean isAdd(AddItemDTO addItemDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.isAdd(addItemDTO);
    }
}
