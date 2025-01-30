package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.InventoryDTO;
import org.example.dto.tm.InventoryTm;

import java.sql.SQLException;
import java.util.List;

public interface InventoryBO extends SuperBO {
    String getCurrentItemId() throws SQLException, ClassNotFoundException;

    List<InventoryTm> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String itemId) throws SQLException, ClassNotFoundException;

    boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    boolean save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;
}
