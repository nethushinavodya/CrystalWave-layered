package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.AddGuest;

import java.sql.SQLException;
import java.util.List;

public interface GuestDAO extends CrudDAO<AddGuest> {
    String getCurrentGuestId() throws SQLException, ClassNotFoundException;

   /* List<AddGuest> getAll() throws SQLException, ClassNotFoundException;

    boolean save(AddGuest addGuest) throws SQLException, ClassNotFoundException;

    boolean update(AddGuest addGuest) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    AddGuest search(String guestId) throws SQLException, ClassNotFoundException;*/
}
