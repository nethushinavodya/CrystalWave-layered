package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.InventoryDAO;
import org.example.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public String getCurrentItemId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('C', MAX(CAST(SUBSTRING(Inventory_Id, 2) AS UNSIGNED))) AS max_i_id FROM Inventory");
        if(rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Inventory");
        ArrayList<Inventory> inventories = new ArrayList<>();
        try {
            while (rst.next()) {
                inventories.add(new Inventory(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories;
    }

    @Override
    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Inventory WHERE Inventory_Id = ?", itemId);
    }

    @Override
    public boolean update(Inventory inventory) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Inventory SET ItemName = ?, Quantity = ?, Price = ? WHERE Item_Id = ?", inventory.getItemName(), inventory.getItemQuantity(), inventory.getItemPrice(), inventory.getItemId());

    }

    @Override
    public boolean save(Inventory inventory) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Inventory VALUES(?, ?, ?, ?)", inventory.getItemId(), inventory.getItemName(), inventory.getItemQuantity(), inventory.getItemPrice());
    }
}
