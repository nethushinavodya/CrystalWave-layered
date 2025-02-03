package org.example.bo.custom.impl;

import javafx.collections.ObservableList;
import org.example.bo.custom.ReservtionBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PaymentDAO;
import org.example.dao.custom.ReservationDAO;
import org.example.dto.ReservationDTO;
import com.example.layeredarchitecture.db.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservtionBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    @Override
    public ObservableList<String> getAll() throws SQLException, ClassNotFoundException {
        return reservationDAO.getAllids();
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return reservationDAO.getCurrentId();
    }

    @Override
    public String search(String roomId) throws SQLException, ClassNotFoundException {
        return reservationDAO.searchType(roomId);
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO, double total, String paymentId, String paymentmethod, String disId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            System.out.println("000");
            boolean isReserved = reservationDAO.saveReservation1(reservationDTO);

            if(isReserved) {
                System.out.println("111");
                boolean isSave = reservationDAO.saved(reservationDTO.getRoomReserves());

                if(isSave) {
                    String date = LocalDate.now().toString();
                    System.out.println("555");
                    boolean isPaymentSaved = paymentDAO.paymentSaved(paymentId,paymentmethod,total,date,reservationDTO.getReservationId(),disId);
                    if(isPaymentSaved) {
                        connection.commit();
                        return true;
                    }else {
                        connection.rollback();
                        return false;
                    }
                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                System.out.println("222");
                connection.rollback();
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
