package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.GuestDAO;
import org.example.entity.AddGuest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAOImpl implements GuestDAO {
    @Override
    public String getCurrentGuestId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('G', MAX(CAST(SUBSTRING(Guest_Id, 2) AS UNSIGNED))) AS max_g_id FROM Guest");
        if (rst.next()) {
            String gid = rst.getString(1);
            return gid;
        }
        return null;
    }

    @Override
    public List<AddGuest> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Guest");
        ArrayList<AddGuest> addGuests = new ArrayList<>();
        while (rst.next()) {
            addGuests.add(new AddGuest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return addGuests;
    }

    @Override
    public boolean save(AddGuest addGuest) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Guest VALUES(?, ?, ?, ?, ?)", addGuest.getGuestId(), addGuest.getGuestName(), addGuest.getGuestPhone(), addGuest.getGuestAddress(), addGuest.getGuestEmail());
    }

    @Override
    public boolean update(AddGuest addGuest) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Guest set Name = ?,Contact_No = ?,Address=?,Email=? where Guest_Id = ?", addGuest.getGuestName(), addGuest.getGuestPhone(), addGuest.getGuestAddress(), addGuest.getGuestEmail(), addGuest.getGuestId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Guest WHERE Guest_Id = ?", id);
    }

    @Override
    public AddGuest search(String guestId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Guest WHERE Guest_Id = ?", guestId);
        if (rst.next()) {
            return new AddGuest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }
}
