package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AddServiceDTO;
import org.example.dto.AddServiceEmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBO {
    List<AddServiceDTO> getAll() throws SQLException, ClassNotFoundException;

    String getprice(String serviceId)throws SQLException, ClassNotFoundException;

    String getCurrentSId() throws SQLException, ClassNotFoundException;

    boolean delete(String serviceId) throws SQLException, ClassNotFoundException;

    boolean saveService(AddServiceDTO addServiceDTO, AddServiceEmployeeDTO addServiceEmployeeDTO) throws SQLException, ClassNotFoundException;

    boolean update(AddServiceDTO addServiceDTO) throws SQLException, ClassNotFoundException;

    boolean isAdd(AddServiceEmployeeDTO addServiceEmployeeDTO) throws SQLException, ClassNotFoundException;
}
