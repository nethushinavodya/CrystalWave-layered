package org.example.bo.custom.impl;

import org.example.bo.custom.BillBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BillDAO;
import org.example.dto.AddBilDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillBOImpl implements BillBO {
    BillDAO billDAO = (BillDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BILL);
    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return billDAO.getCurrentId();
    }

    @Override
    public boolean save(AddBilDto addBilDto) throws SQLException, ClassNotFoundException {
        return billDAO.save(addBilDto);
    }
}
