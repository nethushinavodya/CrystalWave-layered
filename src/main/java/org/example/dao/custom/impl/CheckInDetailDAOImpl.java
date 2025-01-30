package org.example.dao.custom.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.CheckInDetailDAO;
import org.example.entity.CheckInDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckInDetailDAOImpl implements CheckInDetailDAO {
    @Override
    public List<CheckInDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT \n" +
                "    R.Reservation_Id,\n" +
                "    R.Guest_Id,\n" +
                "    Ro.Room_Id,\n" +
                "    R.CheckIn_Date,\n" +
                "    R.CheckOut_Date,\n" +
                "    P.Amount_Paid\n" +
                "FROM \n" +
                "    Reservation R\n" +
                "JOIN \n" +
                "    Reservation_Room RR ON R.Reservation_Id = RR.Reservation_Id\n" +
                "JOIN \n" +
                "    Room Ro ON RR.Room_Id = Ro.Room_Id\n" +
                "LEFT JOIN \n" +
                "    Payment P ON R.Reservation_Id = P.Reservation_Id\n" +
                "ORDER BY \n" +
                "    R.Reservation_Id;\n");
        ArrayList<CheckInDetail> checkInDetails = new ArrayList<>();
        try {
            while (rst.next()) {
                checkInDetails.add(new CheckInDetail(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkInDetails;
    }
}
