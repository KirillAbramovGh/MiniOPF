package com.netcracker.students.o3.model.dao.order;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order>
{
    List<Order> getOrdersByTemplateId(final BigInteger templateId) throws SQLException;

    List<Order> getOrdersByServiceId(final BigInteger serviceId) throws SQLException;

    List<Order> getOrdersByEmployeeId(final BigInteger employeeId) throws SQLException;

    List<Order> getOrdersByStatus(final OrderStatus status) throws SQLException;

    List<Order> getOrdersByAction(final OrderAction action) throws SQLException;
}
