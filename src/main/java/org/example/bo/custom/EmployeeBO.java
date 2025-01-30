package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO{
    String getCurrentEmployeeId() throws SQLException, ClassNotFoundException;

    List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String eId) throws SQLException, ClassNotFoundException;

    boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    EmployeeDTO search(String contact) throws SQLException, ClassNotFoundException;
}
