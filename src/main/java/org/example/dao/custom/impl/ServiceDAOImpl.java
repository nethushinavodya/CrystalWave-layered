package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.ServiceDAO;
import org.example.dto.AddServiceDTO;
import org.example.dto.AddServiceEmployeeDTO;
import org.example.entity.AddGuest;
import org.example.entity.AddService;
import org.example.entity.AddServiceEmployee;

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
    public boolean save(AddService entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Service VALUES (?,?,?,?)", entity.getServiceId(), entity.getServiceName(), entity.getServiceDescription(), entity.getServicePrice());
    }

    @Override
    public boolean update(AddService entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Service set Service_Name = ?, Description = ?, Price = ? where Service_Id = ?", entity.getServiceName(), entity.getServiceDescription(), entity.getServicePrice(), entity.getServiceId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
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

    @Override
    public String getCurrentSId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('S', MAX(CAST(SUBSTRING(Service_Id, 2) AS UNSIGNED))) AS max_s_id FROM Service");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveAssociate(AddServiceEmployee addServiceEmployee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee_Service VALUES (?,?)", addServiceEmployee.getEmployeeId(), addServiceEmployee.getServiceId());
    }

    @Override
    public boolean isAdd(AddServiceEmployeeDTO addServiceEmployeeDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee_Service VALUES (?,?)", addServiceEmployeeDTO.getEmployeeId(), addServiceEmployeeDTO.getServiceId());
    }


}
