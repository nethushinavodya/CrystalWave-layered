package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.RoomDAO;
import org.example.dto.AddItemDTO;
import org.example.dto.AddRoomDTO;

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

    public List<AddRoomDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Room");
        List<AddRoomDTO> addRoomDTOS = new ArrayList<>();
        try {
            while (rst.next()) {
                AddRoomDTO addRoomDTO = new AddRoomDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
                addRoomDTOS.add(addRoomDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addRoomDTOS;
    }

    public boolean delete(String i) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Room WHERE Room_Id = ?", i);

    }

    public boolean save(AddRoomDTO addRoomDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Room VALUES (?,?,?,?)", addRoomDTO.getRoomId(), addRoomDTO.getRoomNumber(), addRoomDTO.getRoomStatus(), addRoomDTO.getRoomTypeId());
    }

    @Override
    public boolean update(AddRoomDTO addRoomDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Room SET RoomNumber = ?, RoomStatus = ?, RoomType_Id = ? WHERE Room_Id = ?", addRoomDTO.getRoomNumber(), addRoomDTO.getRoomStatus(), addRoomDTO.getRoomTypeId(), addRoomDTO.getRoomId());
    }

    @Override
    public boolean isAdd(AddItemDTO addItemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Room_Inventory VALUES (?,?)",
                addItemDTO.getRoomId(),
                addItemDTO.getInventoryId());
    }
}
