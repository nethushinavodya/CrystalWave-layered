package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.RoomTypeDAO;
import org.example.entity.AddGuest;
import org.example.entity.RoomType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomTypeDAOImpl implements RoomTypeDAO {
    @Override
    public String getCurrentTypeId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('T', MAX(CAST(SUBSTRING(RoomType_Id, 2) AS UNSIGNED))) AS max_p_id FROM Room_Type");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<RoomType> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Room_Type");
        ArrayList<RoomType> roomTypes = new ArrayList<>();
        try {
            while (rst.next()){
                roomTypes.add(new RoomType(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomTypes;
    }

    @Override
    public boolean delete(String roomNumber) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Room_Type WHERE RoomType_Id = ?",roomNumber);
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(RoomType roomType) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Room_Type VALUES (?,?,?,?)",roomType.getRoomTypeId(),roomType.getRoomTypeName(),roomType.getRoomTypeDescription(),roomType.getRoomTypePrice());
    }

    @Override
    public boolean update(RoomType roomType) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Update Room_Type set RoomType = ?,Description = ?,Rate = ? where RoomType_Id = ?",roomType.getRoomTypeName(),roomType.getRoomTypeDescription(),roomType.getRoomTypePrice(),roomType.getRoomTypeId());
    }

    @Override
    public String getPrice(String type) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Rate FROM Room_Type WHERE RoomType_Id = ?",type);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
