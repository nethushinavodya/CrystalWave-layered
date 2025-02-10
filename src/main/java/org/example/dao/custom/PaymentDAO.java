package org.example.dao.custom;

import org.example.dao.CrudDAO;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO{
    boolean paymentSaved(String paymentId, String paymentmethod, double total, String date, String reservationId, String disId) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;
}
