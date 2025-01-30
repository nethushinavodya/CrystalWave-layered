package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.DiscountDTO;
import org.example.dto.tm.DiscountTM;

import java.sql.SQLException;
import java.util.List;

public interface DiscountBO extends SuperBO {
    List<DiscountTM> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean save(DiscountDTO discountDTO) throws SQLException, ClassNotFoundException;
}
