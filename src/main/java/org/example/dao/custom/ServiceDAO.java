package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.AddServiceEmployeeDTO;
import org.example.entity.AddService;
import org.example.entity.AddServiceEmployee;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends CrudDAO<AddService> {
/*
    List<AddService> getAll()throws SQLException, ClassNotFoundException;
*/

    String getprice(String serviceId) throws SQLException, ClassNotFoundException;

    String getCurrentSId() throws SQLException, ClassNotFoundException;

    boolean saveAssociate(AddServiceEmployee addServiceEmployee) throws SQLException, ClassNotFoundException;

    boolean isAdd(AddServiceEmployeeDTO addServiceEmployeeDTO) throws SQLException, ClassNotFoundException;
}
