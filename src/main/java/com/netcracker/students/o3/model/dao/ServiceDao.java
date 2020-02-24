package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;

import java.math.BigInteger;
import java.sql.Connection;
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
    public List<Service> getAll() throws SQLException, ClassNotFoundException
    {
        List<Service> services = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {

                for (Service service; resultSet.next(); )
                {
                    service = new ServiceImpl();
                    service.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    service.setUserId(BigInteger.valueOf(resultSet.getLong("userid")));
                    service.setTemplateId(BigInteger.valueOf(resultSet.getLong("templateid")));
                    service.setStatus(ServiceStatus.valueOf(resultSet.getString("status")));
                    service.setActivationDate(new Date(resultSet.getDate("activationdate").getTime()));

                    services.add(service);
                }
            }
        }


        return services;
    }

    @Override
    public Service getEntityById(final BigInteger id) throws SQLException, ClassNotFoundException
    {
        Service service = new ServiceImpl();
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                service.setId(id);
                if (resultSet.next())
                {
                    service.setUserId(BigInteger.valueOf(resultSet.getLong("userid")));
                    service.setTemplateId(BigInteger.valueOf(resultSet.getLong("templateid")));
                    service.setStatus(ServiceStatus.valueOf(resultSet.getString("status")));
                    service.setActivationDate(new Date(resultSet.getDate("activationdate").getTime()));

                }
            }
        }


        return service;
    }

    @Override
    public void update(final Service entity) throws SQLException, ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String userId = entity.getUserId().toString();
            String templateId = entity.getTemplateId().toString();
            String status = "'" + entity.getStatus().toString() + "'";
            String date = "'" + new java.sql.Date(entity.getActivationDate().getTime()) + "'";

            String sqlReq =
                    "update " + getTableName() + " set userid=" + userId + ", templateid=" + templateId +
                            ", status=" + status + ", activationdate=" + date
                            + " where id=" + entity.getId();
            statement.executeUpdate(sqlReq);
        }

    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }


    @Override
    public void create(final Service entity) throws ClassNotFoundException, SQLException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            System.out.println(entity);
            String values = entity.getId() + ", " + entity.getUserId() + "," + entity.getTemplateId() + ", " +
                    "'" + entity.getStatus() + "', '"
                    + new java.sql.Date(entity.
                    getActivationDate().
                    getTime()) + "'";

            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            statement.executeUpdate(sqlReq);
        }

    }
}
