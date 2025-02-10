package org.example.bo.custom.impl;

import org.example.bo.custom.GuestBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.GuestDAO;
import org.example.dto.AddGuestDTO;
import org.example.dto.tm.AddGuestTM;
import org.example.entity.AddGuest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBOImpl implements GuestBO {
    GuestDAO guestDAO = (GuestDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.GUEST);
    @Override
    public String getCurrentGuestId() throws SQLException, ClassNotFoundException {
        return guestDAO.getCurrentGuestId();
    }

    @Override
    public List<AddGuestTM> getAll() throws SQLException, ClassNotFoundException {
        List<AddGuest> guests = guestDAO.getAll();
        List<AddGuestTM> guestTMs = new ArrayList<>();
        for (AddGuest guest : guests) {
            guestTMs.add(new AddGuestTM(guest.getGuestId(), guest.getGuestName(),guest.getGuestPhone(), guest.getGuestAddress(),guest.getGuestEmail()));
        }
        return guestTMs;
    }

    @Override
    public boolean save(AddGuestDTO addGuestDTO) throws SQLException, ClassNotFoundException {
        return guestDAO.save(new AddGuest(addGuestDTO.getGuestId(),addGuestDTO.getGuestName(),addGuestDTO.getGuestPhone(),addGuestDTO.getGuestAddress(),addGuestDTO.getGuestEmail()));
    }

    @Override
    public boolean update(AddGuestDTO addGuestDTO) throws SQLException, ClassNotFoundException {
        return guestDAO.update(new AddGuest(addGuestDTO.getGuestId(),addGuestDTO.getGuestName(),addGuestDTO.getGuestPhone(),addGuestDTO.getGuestAddress(),addGuestDTO.getGuestEmail()));
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return guestDAO.delete(id);
    }

    @Override
    public AddGuestDTO search(String guestId) throws SQLException, ClassNotFoundException {
        AddGuest guest = guestDAO.search(guestId);
        return new AddGuestDTO(guest.getGuestId(),guest.getGuestName(),guest.getGuestPhone(),guest.getGuestAddress(),guest.getGuestEmail());
    }
}
