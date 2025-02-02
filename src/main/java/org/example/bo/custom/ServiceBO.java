package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AddServiceDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBO {
    List<AddServiceDTO> getAll() throws SQLException, ClassNotFoundException;

    String getprice(String serviceId)throws SQLException, ClassNotFoundException;
}
