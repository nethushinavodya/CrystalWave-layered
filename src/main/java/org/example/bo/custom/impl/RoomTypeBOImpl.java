package org.example.bo.custom.impl;

import org.example.bo.custom.RoomTypeBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.RoomTypeDAO;
import org.example.dto.RoomTypeDTO;
import org.example.dto.tm.RoomTypeTm;
import org.example.entity.RoomType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeBOImpl implements RoomTypeBO {
    RoomTypeDAO roomTypeDAO = (RoomTypeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOMTYPE);
    @Override
    public String getCurrentTypeId() throws SQLException, ClassNotFoundException {
        return roomTypeDAO.getCurrentTypeId();
    }

    @Override
    public List<RoomTypeTm> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<RoomType> roomTypes = roomTypeDAO.getAll();
        List<RoomTypeTm> roomTypeTms = new ArrayList<>();
        for(RoomType roomType : roomTypes){
            roomTypeTms.add(new RoomTypeTm(roomType.getRoomTypeId(),roomType.getRoomTypeName(),roomType.getRoomTypeDescription(),String.valueOf(roomType.getRoomTypePrice())));
        }
         return roomTypeTms;
        }

    @Override
    public boolean delete(String roomNumber) throws SQLException, ClassNotFoundException {
        return roomTypeDAO.delete(roomNumber);
    }

    @Override
    public boolean save(RoomTypeDTO roomTypeDTO) throws SQLException, ClassNotFoundException {
        return roomTypeDAO.save(new RoomType(roomTypeDTO.getRoomTypeId(),roomTypeDTO.getRoomTypeName(),roomTypeDTO.getRoomTypeDescription(),roomTypeDTO.getRoomTypePrice()));
    }

    @Override
    public boolean update(RoomTypeDTO roomTypeDTO) throws SQLException, ClassNotFoundException {
        return roomTypeDAO.update(new RoomType(roomTypeDTO.getRoomTypeId(),roomTypeDTO.getRoomTypeName(),roomTypeDTO.getRoomTypeDescription(),roomTypeDTO.getRoomTypePrice()));
    }
}
