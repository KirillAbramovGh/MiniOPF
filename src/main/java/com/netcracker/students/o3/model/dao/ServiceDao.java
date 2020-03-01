package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceDao extends AbstractDao<Service>
{
    private static final String tableName = "services";

    @Override
    public List<Service> getAll() throws SQLException
    {
        System.out.println("ServiceDao.getAll()");
        String sqlReq = "select * from " + getTableName();
        return getServices(sqlReq);
    }

    @Override
    public Service getEntityById(final BigInteger id) throws SQLException
    {
        Service service = null;
        String sqlReq = "select * from " + getTableName() + " where id=?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1,id.longValue());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    service = getServiceFromResultSet(resultSet);
                }
            }
        }


        return service;
    }

    @Override
    public void update(final Service entity) throws SQLException
    {
        String sqlReq =
                "update " + getTableName() + " set userid=?, templateid=?, status=?, activationdate=? where id=?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1,entity.getUserId().longValue());
            statement.setLong(2,entity.getTemplateId().longValue());
            statement.setString(3,entity.getStatus()+"");
            statement.setDate(4,new java.sql.Date(entity.getActivationDate().getTime()));
            statement.setLong(5,entity.getId().longValue());

            statement.executeUpdate();
        }

    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }

    @Override
    public void create(final Service entity) throws SQLException
    {
        String sqlReq = "INSERT INTO " + getTableName() + " VALUES (?,?,?,?,?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1,entity.getId().longValue());
            statement.setLong(2,entity.getUserId().longValue());
            statement.setLong(3,entity.getTemplateId().longValue());
            statement.setString(4,entity.getStatus()+"");
            statement.setDate(5,new java.sql.Date(entity.
                    getActivationDate().
                    getTime()));

            statement.executeUpdate();
        }

    }

    public List<Service> getServicesByUserId(BigInteger userId) throws SQLException
    {
        String sqlReq = "select * from " + getTableName() + " where userid=" + userId;
        return getServices(sqlReq);
    }

    public List<Service> getServicesByTemplateId(BigInteger templateId) throws SQLException
    {
        String sqlReq = "select * from " + getTableName() + " where templateid=" + templateId;
        return getServices(sqlReq);
    }

    public List<Service> getServicesByStatus(ServiceStatus status) throws SQLException
    {
        String sqlReq = "select * from " + getTableName() + " where status='" + status+"'";
        return getServices(sqlReq);
    }

    public List<Service> getServicesByStatusAndCustomerId(BigInteger userId,ServiceStatus status) throws SQLException
    {
        String sqlReq = "select * from " + getTableName() + " where status='" + status+"' and userid="+userId;
        return getServices(sqlReq);
    }

    private List<Service> getServices(String sqlReq) throws SQLException
    {
        List<Service> services = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {

                for (Service service; resultSet.next(); )
                {
                    service = getServiceFromResultSet(resultSet);
                    if (service != null)
                    {
                        services.add(service);
                    }
                }
            }
        }


        return services;
    }

    private Service getServiceFromResultSet(ResultSet set) throws SQLException
    {
        Service service;
        if (set.getString("status") == null)
        {
            service = null;
        }
        else
        {
            service = new ServiceImpl();
            service.setId(set.getBigDecimal("id").toBigInteger());
            service.setUserId(BigInteger.valueOf(set.getLong("userid")));
            service.setTemplateId(BigInteger.valueOf(set.getLong("templateid")));
            service.setStatus(ServiceStatus.valueOf(set.getString("status")));
            service.setActivationDate(new Date(set.getDate("activationdate").getTime()));
        }
        return service;
    }
}
