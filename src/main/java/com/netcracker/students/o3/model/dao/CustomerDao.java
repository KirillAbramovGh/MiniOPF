package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends AbstractDao<Customer>
{

    private static final String tableName = "customers";

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException
    {
        List<Customer> customers = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                String sqlServiceReq = "select id from services where userid=";
                ResultSet serviceSet;
                for (Customer customer; resultSet.next(); )
                {
                    customer = new CustomerImpl();
                    customer.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    customer.setName(resultSet.getString("name"));
                    if(customer.getName() == null){
                        continue;
                    }
                    customer.setLogin(resultSet.getString("login"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setMoneyBalance(resultSet.getBigDecimal("moneybalance"));
                    customer.setAreaId(BigInteger.valueOf(resultSet.getLong("areaid")));

                    serviceSet = getConnection().createStatement().executeQuery(sqlServiceReq+customer.getId());
                    for(BigInteger serviceId;serviceSet.next();){
                        serviceId = serviceSet.getBigDecimal("id").toBigInteger();
                        customer.addConnectedServiceId(serviceId);
                    }

                    customers.add(customer);
                }
            }
        }


        return customers;
    }

    @Override
    public Customer getEntityById(final BigInteger id) throws SQLException, ClassNotFoundException
    {
        Customer customer = new CustomerImpl();
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {

                customer.setId(id);
                if (resultSet.next())
                {
                    customer.setName(resultSet.getString("name"));

                    if(customer.getName() == null){
                        return null;
                    }
                    customer.setLogin(resultSet.getString("login"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setMoneyBalance(resultSet.getBigDecimal("moneybalance"));
                    customer.setAreaId(BigInteger.valueOf(resultSet.getLong("areaid")));
                    String sqlServiceReq = "select id from services where userid="+id;
                    ResultSet serviceSet = statement.executeQuery(sqlServiceReq);

                    for(BigInteger serviceId;serviceSet.next();){
                        serviceId = serviceSet.getBigDecimal("id").toBigInteger();
                        customer.addConnectedServiceId(serviceId);
                    }
                    serviceSet.close();
                }
            }
        }

        return customer;
    }

    @Override
    public void update(final Customer entity)
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String name = "'" + entity.getName() + "'";
            String login = "'" + entity.getLogin() + "'";
            String password = "'" + entity.getPassword() + "'";
            String moneyBalance = entity.getMoneyBalance().toString();
            String areaId = entity.getAreaId().toString();

            String sqlReq =
                    "update " + getTableName() + " set name=" + name + ", login=" + login + ", password=" + password +
                            ", moneybalance=" + moneyBalance + ", areaid=" + areaId
                            + " where id=" + entity.getId();
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }

    @Override
    public void create(final Customer entity) throws ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String values = entity.getId() + ",'" + entity.getName() + "','" + entity.getLogin()
                    + "','" + entity.getPassword() + "'," + entity.getMoneyBalance() + "," + entity.getAreaId();
            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
