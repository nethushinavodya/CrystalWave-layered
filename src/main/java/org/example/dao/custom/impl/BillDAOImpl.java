package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.BillDAO;
import org.example.dto.AddBilDto;
import org.example.entity.AddBill;
import org.example.entity.AddGuest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDAOImpl implements BillDAO {
    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Bill_Id \n" +
                "FROM Billing\n" +
                "ORDER BY Bill_Id DESC \n" +
                "LIMIT 1;\n");
        while (rst.next()){
            String id = rst.getString(1);
            return id;
        }
        return null;
    }

    @Override
    public boolean save(AddBilDto addBilDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into Billing values (?,?,?,?)",addBilDto.getBillid(),addBilDto.getRid(),addBilDto.getTot(),addBilDto.getDate());
    }

    @Override
    public List<AddBill> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(AddBill entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AddBill entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
