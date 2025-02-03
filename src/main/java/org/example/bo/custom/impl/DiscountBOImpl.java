package org.example.bo.custom.impl;

import org.example.bo.custom.DiscountBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.DiscountDAO;
import org.example.dto.DiscountDTO;
import org.example.dto.tm.DiscountTM;
import org.example.entity.Discount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountBOImpl implements DiscountBO {
    DiscountDAO discountDAO = (DiscountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DISCOUNT);
    @Override
    public List<DiscountTM> getAll() throws SQLException, ClassNotFoundException {
        List<Discount> discounts = discountDAO.getAll();
        List<DiscountTM> discountTMs = new ArrayList<>();
        for (Discount discount : discounts) {
            discountTMs.add(new DiscountTM(discount.getDiscountId(),discount.getDiscountType(),discount.getDiscountStartDate(),discount.getDiscountEndDate(),discount.getDiscountCondition()));
        }

        return discountTMs;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return discountDAO.delete(id);
    }

    @Override
    public boolean save(DiscountDTO discountDTO) throws SQLException, ClassNotFoundException {
        return discountDAO.save(new Discount(discountDTO.getDiscountId(),discountDTO.getDiscountType(),discountDTO.getDiscountStartDate(),discountDTO.getDiscountEndDate(),discountDTO.getDiscountCondition()));
    }

    @Override
    public List<String> getDiscount() throws SQLException, ClassNotFoundException {
        return discountDAO.getDiscount();
    }

    @Override
    public int search(int discount) throws SQLException, ClassNotFoundException {
        return discountDAO.search(discount);
    }
}
