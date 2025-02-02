package org.example.bo.custom.impl;

import org.example.bo.custom.ServiceBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ServiceDAO;
import org.example.dto.AddServiceDTO;
import org.example.entity.AddService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SERVICE);
    @Override
    public List<AddServiceDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<AddServiceDTO> addServiceDTOS = new ArrayList<>();
        List<AddService> addServices = serviceDAO.getAll();
        for (AddService addService : addServices) {
            addServiceDTOS.add(new AddServiceDTO(addService.getServiceId(), addService.getServiceName(), addService.getServiceDescription(), addService.getServicePrice()));
        }
        return addServiceDTOS;
    }

    @Override
    public String getprice(String serviceId) throws SQLException, ClassNotFoundException {
        return serviceDAO.getprice(serviceId);
    }
}
