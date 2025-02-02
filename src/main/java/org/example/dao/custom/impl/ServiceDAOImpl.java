package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.ServiceDAO;
import org.example.dto.AddServiceDTO;
import org.example.entity.AddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public List<AddService> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Service");
        ArrayList<AddService> addServices = new ArrayList<>();
        while (rst.next()) {
            addServices.add(new AddService(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return addServices;
    }

    @Override
    public String getprice(String serviceId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Price FROM Service WHERE Service_Id = ?", serviceId);
        if (rst.next()) {
            String price = rst.getString(1);
            return price;
        }
        return null;
    }
}
