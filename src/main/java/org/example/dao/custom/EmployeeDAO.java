package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    String getCurrentEmployeeId() throws SQLException, ClassNotFoundException;

    List<Employee> getAll() throws SQLException, ClassNotFoundException;

    boolean update(Employee employee) throws SQLException, ClassNotFoundException;

    boolean delete(String eId) throws SQLException, ClassNotFoundException;

    boolean save(Employee employee) throws SQLException, ClassNotFoundException;

    EmployeeDTO search(String contact) throws SQLException, ClassNotFoundException;
}
