package org.example.bo.custom.impl;

import org.example.bo.custom.InventoryBO;
import org.example.dao.DAOFactory;
import org.example.dao.SQLUtil;
import org.example.dao.custom.InventoryDAO;
import org.example.dto.InventoryDTO;
import org.example.dto.tm.InventoryTm;
import org.example.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryBOImpl implements InventoryBO {
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);
    @Override
    public String getCurrentItemId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.getCurrentItemId();
    }

    @Override
    public List<InventoryTm> getAll() throws SQLException, ClassNotFoundException {
        List<Inventory> inventories = inventoryDAO.getAll();
        List<InventoryTm> inventoryTms = new ArrayList<>();
        for (Inventory inventory : inventories) {
            inventoryTms.add(new InventoryTm(inventory.getItemId(), inventory.getItemName(), inventory.getItemQuantity(), inventory.getItemPrice()));
        }
        return inventoryTms;
    }

    @Override
    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(itemId);
    }

    @Override
    public boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(inventoryDTO.getItemId(), inventoryDTO.getItemName(), inventoryDTO.getItemQuantity(), inventoryDTO.getItemPrice()));
    }

    @Override
    public boolean save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(inventoryDTO.getItemId(), inventoryDTO.getItemName(), inventoryDTO.getItemQuantity(), inventoryDTO.getItemPrice()));
    }

}
