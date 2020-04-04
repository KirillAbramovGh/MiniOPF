package com.netcracker.students.o3.model.dao.ServiceDao;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface ServiceDao extends Dao<Service>
{
    List<Service> getServicesByUserId(BigInteger userId) throws SQLException;
    List<Service> getServicesByTemplateId(BigInteger templateId) throws SQLException;
    List<Service> getServicesByStatus(ServiceStatus status) throws SQLException;
    List<Service> getServicesByStatusAndCustomerId(BigInteger userId, ServiceStatus status) throws SQLException;
}
