package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.AddItemDTO;
import org.example.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDAO extends CrudDAO<Inventory> {
    String getCurrentItemId() throws SQLException, ClassNotFoundException;

    ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String itemId) throws SQLException, ClassNotFoundException;

    boolean update(Inventory inventory) throws SQLException, ClassNotFoundException;

    boolean save(Inventory inventory) throws SQLException, ClassNotFoundException;
}
