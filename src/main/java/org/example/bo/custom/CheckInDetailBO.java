package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.tm.CheckInDetailTM;

import java.sql.SQLException;
import java.util.List;

public interface CheckInDetailBO extends SuperBO {
    List<CheckInDetailTM> getAll() throws SQLException, ClassNotFoundException;
}
