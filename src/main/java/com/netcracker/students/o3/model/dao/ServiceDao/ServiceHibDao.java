package com.netcracker.students.o3.model.dao.ServiceDao;

import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class ServiceHibDao implements ServiceDao
{
    @Override
    public List<Service> getServicesByUserId(final BigInteger userId) throws SQLException
    {
        return null;
    }

    @Override
    public List<Service> getServicesByTemplateId(final BigInteger templateId) throws SQLException
    {
        return null;
    }

    @Override
    public List<Service> getServicesByStatus(final ServiceStatus status) throws SQLException
    {
        return null;
    }

    @Override
    public List<Service> getServicesByStatusAndCustomerId(final BigInteger userId, final ServiceStatus status)
            throws SQLException
    {
        return null;
    }

    @Override
    public List<Service> getAll() throws SQLException
    {
        return null;
    }

    @Override
    public Service getEntity(final BigInteger id) throws SQLException
    {
        return null;
    }

    @Override
    public void update(final Service entity) throws SQLException
    {

    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {

    }

    @Override
    public void delete(final Service entity) throws SQLException
    {

    }

    @Override
    public void create(final Service entity) throws SQLException
    {

    }
}
