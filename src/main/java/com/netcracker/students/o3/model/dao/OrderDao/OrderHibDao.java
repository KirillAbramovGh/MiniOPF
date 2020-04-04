package com.netcracker.students.o3.model.dao.OrderDao;

import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class OrderHibDao implements OrderDao
{
    @Override
    public List<Order> getOrdersByTemplateId(final BigInteger templateId) throws SQLException
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByServiceId(final BigInteger serviceId) throws SQLException
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByEmployeeId(final BigInteger employeeId) throws SQLException
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByStatus(final OrderStatus status) throws SQLException
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByAction(final OrderAction action) throws SQLException
    {
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException
    {
        return null;
    }

    @Override
    public Order getEntity(final BigInteger id) throws SQLException
    {
        return null;
    }

    @Override
    public void update(final Order entity) throws SQLException
    {

    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {

    }

    @Override
    public void create(final Order entity) throws SQLException
    {

    }
}
