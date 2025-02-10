package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.PaymentDAO;
import org.example.entity.AddGuest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean paymentSaved(String paymentId, String paymentmethod, double total, String date, String reservationId, String disId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment VALUES (?,?,?,?,?,?)",paymentId,paymentmethod,total,date,reservationId,disId);
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Payment_Id \n" +
                "FROM Payment\n" +
                "ORDER BY Payment_Id DESC \n" +
                "LIMIT 1;");
        while (rst.next()){
            String id = rst.getString(1);
            return id;
        }
        return null;
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Object entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Object entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
