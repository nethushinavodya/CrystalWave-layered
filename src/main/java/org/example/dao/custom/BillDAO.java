package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.AddBilDto;
import org.example.entity.AddBill;

import java.sql.SQLException;

public interface BillDAO extends CrudDAO<AddBill> {
    String getCurrentId() throws SQLException, ClassNotFoundException;

    boolean save(AddBilDto addBilDto) throws SQLException, ClassNotFoundException;
}
