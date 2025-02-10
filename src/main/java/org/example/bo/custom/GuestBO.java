package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AddGuestDTO;
import org.example.dto.tm.AddGuestTM;

import java.sql.SQLException;
import java.util.List;

public interface GuestBO extends SuperBO {
    String getCurrentGuestId() throws SQLException, ClassNotFoundException;

    List<AddGuestTM> getAll() throws SQLException, ClassNotFoundException;

    boolean save(AddGuestDTO addGuestDTO) throws SQLException, ClassNotFoundException;

    boolean update(AddGuestDTO addGuestDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws ClassNotFoundException, SQLException;

    AddGuestDTO search(String guestId) throws SQLException, ClassNotFoundException;
}
