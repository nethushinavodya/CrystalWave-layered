package org.example.bo.custom.impl;

import org.example.bo.custom.ServiceBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ServiceDAO;
import org.example.dto.AddServiceDTO;
import org.example.dto.AddServiceEmployeeDTO;
import org.example.entity.AddService;
import org.example.entity.AddServiceEmployee;

import java.sql.Connection;
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

    @Override
    public String getCurrentSId() throws SQLException, ClassNotFoundException {
        return serviceDAO.getCurrentSId();
    }

    @Override
    public boolean delete(String serviceId) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(serviceId);
    }

    @Override
    public boolean saveService(AddServiceDTO addServiceDTO, AddServiceEmployeeDTO addServiceEmployeeDTO) throws SQLException, ClassNotFoundException {
        Connection connection = com.example.layeredarchitecture.db.DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

            try {
                boolean isSave = serviceDAO.save(new AddService(addServiceDTO.getServiceId(), addServiceDTO.getServiceName(), addServiceDTO.getServiceDescription(), addServiceDTO.getServicePrice()));

                if (isSave) {
                    boolean isSave2 = serviceDAO.saveAssociate(new AddServiceEmployee(addServiceEmployeeDTO.getEmployeeId(), addServiceDTO.getServiceId()));

                    if (isSave2) {
                        connection.commit();
                        return true;

                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                connection.setAutoCommit(true);
            }
    }

    @Override
    public boolean update(AddServiceDTO addServiceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new AddService(addServiceDTO.getServiceId(), addServiceDTO.getServiceName(), addServiceDTO.getServiceDescription(), addServiceDTO.getServicePrice()));
    }

    @Override
    public boolean isAdd(AddServiceEmployeeDTO addServiceEmployeeDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.isAdd(addServiceEmployeeDTO);
    }
}
