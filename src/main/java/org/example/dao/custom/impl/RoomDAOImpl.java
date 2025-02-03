package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.RoomDAO;
import org.example.dto.AddItemDTO;
import org.example.dto.AddRoomDTO;
import org.example.entity.AddGuest;
import org.example.entity.AddRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    public String getCurrentRoomId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('R', MAX(CAST(SUBSTRING(Room_Id, 2) AS UNSIGNED))) AS max_r_id FROM Room");
        if(rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public List<AddRoom> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Room");
        List<AddRoom> addRoom = new ArrayList<>();
        while (rst.next()) {
            addRoom.add(new AddRoom(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return addRoom;
    }


    public boolean delete(String i) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Room WHERE Room_Id = ?", i);

    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(AddRoom addRoomDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Room VALUES (?,?,?,?)", addRoomDTO.getRoomId(), addRoomDTO.getRoomNumber(), addRoomDTO.getRoomStatus(), addRoomDTO.getRoomTypeId());
    }

    @Override
    public boolean update(AddRoom addRoomDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Room SET RoomNumber = ?, RoomStatus = ?, RoomType_Id = ? WHERE Room_Id = ?", addRoomDTO.getRoomNumber(), addRoomDTO.getRoomStatus(), addRoomDTO.getRoomTypeId(), addRoomDTO.getRoomId());
    }

    @Override
    public boolean isAdd(AddItemDTO addItemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Room_Inventory VALUES (?,?)",
                addItemDTO.getRoomId(),
                addItemDTO.getInventoryId());
    }

    @Override
    public List<String> getDeactiveRooms() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Room_Id FROM Room WHERE RoomStatus = 'Occupied'");
        List<String> roomIds = new ArrayList<>();
        while (rst.next()) {
            roomIds.add(rst.getString(1));
        }
        return roomIds;
    }

    @Override
    public boolean checkOut(String roomId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Room SET RoomStatus = 'Available' WHERE Room_Id = ?", roomId);
    }

    @Override
    public List<String> getActiveRoom() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Room_Id FROM Room WHERE RoomStatus = 'Available' ");
        List<String> roomIds = new ArrayList<>();
        while (rst.next()) {
            roomIds.add(rst.getString(1));
        }
        return roomIds;
    }

    @Override
    public boolean updateAvailable(String roomId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Room set RoomStatus = 'Occupied' where Room_Id = ?", roomId);
    }
}
