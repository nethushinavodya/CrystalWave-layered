package org.example.bo.custom.impl;

import org.example.bo.custom.CheckInDetailBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CheckInDetailDAO;
import org.example.dto.tm.CheckInDetailTM;
import org.example.entity.CheckInDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckInDetailBOImpl implements CheckInDetailBO {
    CheckInDetailDAO checkInDetailDAO = (CheckInDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CHECKINDETAIL);
    @Override
    public List<CheckInDetailTM> getAll() throws SQLException, ClassNotFoundException {
        List<CheckInDetail> checkInDetails = checkInDetailDAO.getAll();
        List<CheckInDetailTM> checkInDetailTMs = new ArrayList<>();
        for (CheckInDetail checkInDetail : checkInDetails) {
            checkInDetailTMs.add(new CheckInDetailTM(checkInDetail.getReservationId(), checkInDetail.getGuestId(), checkInDetail.getRoomId(), checkInDetail.getCheckInDate(), checkInDetail.getCheckOutDate(), checkInDetail.getAmount()));
        }
        return checkInDetailTMs;
    }
}
