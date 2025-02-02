package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.AddService;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends CrudDAO<AddService> {
    List<AddService> getAll()throws SQLException, ClassNotFoundException;

    String getprice(String serviceId) throws SQLException, ClassNotFoundException;
}
