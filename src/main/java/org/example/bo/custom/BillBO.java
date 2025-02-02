package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AddBilDto;

import java.sql.SQLException;

public interface BillBO extends SuperBO {
    String getCurrentId() throws SQLException, ClassNotFoundException;

    boolean save(AddBilDto addBilDto) throws SQLException, ClassNotFoundException;
}
