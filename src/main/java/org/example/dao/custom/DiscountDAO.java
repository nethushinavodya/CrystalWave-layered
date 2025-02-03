package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.DiscountDTO;
import org.example.entity.Discount;

import java.sql.SQLException;
import java.util.List;

public interface DiscountDAO extends CrudDAO<Discount> {
    /*List<Discount> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean save(Discount discount) throws SQLException, ClassNotFoundException;
*/
    List<String> getDiscount() throws SQLException, ClassNotFoundException;

    int search(int discount) throws SQLException, ClassNotFoundException;
}
