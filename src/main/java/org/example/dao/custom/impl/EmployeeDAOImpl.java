package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.EmployeeDAO;
import org.example.dto.EmployeeDTO;
import org.example.entity.AddGuest;
import org.example.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String getCurrentEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('E', MAX(CAST(SUBSTRING(Employeement_Id, 2) AS UNSIGNED))) AS max_e_id FROM Employeement");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM Employeement");
       List<Employee> employeeDTOS = new ArrayList<>();
       while (rst.next()){
           employeeDTOS.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
       }
       return employeeDTOS;
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Update Employeement set Name = ?, Role = ? , ContactNo = ? where Employeement_Id = ?", employee.getName(), employee.getRole(), employee.getContact(), employee.getEmployeeId());
    }

    @Override
    public boolean delete(String eId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employeement WHERE Employeement_Id = ?", eId);
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employeement VALUES(?,?,?,?)", employee.getEmployeeId(), employee.getName(), employee.getRole(), employee.getContact());
    }

    @Override
    public EmployeeDTO search1(String contact) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employeement WHERE ContactNo = ?", contact);
        if (rst.next()) {
            return new EmployeeDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }
}
