package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.CheckInDetail;

import java.sql.SQLException;
import java.util.List;

public interface CheckInDetailDAO extends CrudDAO<CheckInDetail> {
    List<CheckInDetail> getAll() throws SQLException, ClassNotFoundException;
}
