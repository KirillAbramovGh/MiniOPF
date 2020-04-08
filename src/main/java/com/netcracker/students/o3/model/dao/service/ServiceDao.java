package com.netcracker.students.o3.model.dao.service;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDao extends Dao<Service>
{
    List<Service> getServicesByCustomer(Customer customer) throws SQLException;
    List<Service> getServicesByTemplate(Template template) throws SQLException;
    List<Service> getServicesByStatus(ServiceStatus status) throws SQLException;
}
