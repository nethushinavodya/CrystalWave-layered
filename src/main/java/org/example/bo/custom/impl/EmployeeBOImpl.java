package org.example.bo.custom.impl;

import org.example.bo.custom.EmployeeBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.EmployeeDAO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public String getCurrentEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentEmployeeId();
    }

    @Override
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> employees = employeeDAO.getAll();
        for (Employee employee : employees) {
            employeeDTOS.add(new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getRole(), employee.getContact()));
        }
        return employeeDTOS;
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new org.example.entity.Employee(employeeDTO.getEmployeeId(), employeeDTO.getName(), employeeDTO.getRole(), employeeDTO.getContact()));
    }

    @Override
    public boolean delete(String eId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(eId);
    }

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new org.example.entity.Employee(employeeDTO.getEmployeeId(), employeeDTO.getName(), employeeDTO.getRole(), employeeDTO.getContact()));
    }

    @Override
    public EmployeeDTO search(String contact) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(contact);
    }
}
