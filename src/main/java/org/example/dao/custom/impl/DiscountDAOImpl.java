package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.DiscountDAO;
import org.example.dto.tm.DiscountTM;
import org.example.entity.AddGuest;
import org.example.entity.Discount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {
    @Override
    public List<Discount> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Discount");
        List<Discount> discounts = new ArrayList<>();
        while (rst.next()){
            discounts.add(new Discount(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5)));
        }
        return discounts;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Discount WHERE Discount_Id = ?",id);
    }

    @Override
    public AddGuest search(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Discount discount) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Discount VALUES (?,?,?,?,?)",discount.getDiscountId(),discount.getDiscountType(),discount.getDiscountStartDate(),discount.getDiscountEndDate(),discount.getDiscountCondition());
    }

    @Override
    public boolean update(Discount entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDiscount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Discount_Id FROM Discount ");
        List<String> discount = new ArrayList<>();
        while (rst.next()){
            discount.add(rst.getString(1));
        }
        return discount;
    }

    @Override
    public int search(int discount) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Discount_Amount FROM Discount WHERE Discount_Id = ?",discount);
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }
}
