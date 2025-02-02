package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.RoomType;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomTypeDAO extends CrudDAO<RoomType>{
    String getCurrentTypeId() throws SQLException, ClassNotFoundException;

    ArrayList<RoomType> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String roomNumber) throws SQLException, ClassNotFoundException;

    boolean save(RoomType roomType) throws SQLException, ClassNotFoundException;

    boolean update(RoomType roomType) throws SQLException, ClassNotFoundException;

    String getPrice(String type) throws SQLException, ClassNotFoundException;
}
