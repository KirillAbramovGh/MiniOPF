package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.orders.OrderStatus;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao extends AbstractDao<Order>
{
    private static final String tableName = "orders";

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException
    {
        List<Order> orders = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {

                for (Order order; resultSet.next(); )
                {
                    order = new OrderImpl();
                    order.setId(BigInteger.valueOf(resultSet.getInt("id")));

                    if(resultSet.getBigDecimal("id")==null){
                        continue;
                    }
                    order.setTemplateId(BigInteger.valueOf(resultSet.getLong("templateid")));
                    order.setServiceId(BigInteger.valueOf(resultSet.getLong("serviceid")));
                    order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
                    order.setCreationDate(new Date(resultSet.getDate("creationdate").getTime()));
                    order.setAction(OrderAction.valueOf(resultSet.getString("orderaction")));
                    order.setEmployeeId(BigInteger.valueOf(resultSet.getLong("employeeid")));

                    orders.add(order);
                }
            }
        }


        return orders;
    }

    @Override
    public Order getEntityById(final BigInteger id) throws SQLException, ClassNotFoundException
    {
        Order order = new OrderImpl();
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {

                order.setId(id);
                if (resultSet.next())
                {
                    if(resultSet.getBigDecimal("id")==null){
                       return null;
                    }
                    order.setTemplateId(BigInteger.valueOf(resultSet.getLong("templateid")));
                    order.setServiceId(BigInteger.valueOf(resultSet.getLong("serviceid")));
                    order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
                    order.setCreationDate(new Date(resultSet.getDate("creationdate").getTime()));
                    order.setAction(OrderAction.valueOf(resultSet.getString("orderaction")));
                    order.setEmployeeId(BigInteger.valueOf(resultSet.getLong("employeeid")));
                }
            }
        }


        return order;
    }

    @Override
    public void update(final Order entity) throws SQLException, ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String serviceId = entity.getServiceId().toString();
            String templateId = entity.getTemplateId().toString();
            String status = "'" + entity.getStatus().toString() + "'";
            String date = "'" + new java.sql.Date(entity.getCreationDate().getTime()) + "'";
            String employeeId = entity.getEmployeeId().toString();
            String action = "'" + entity.getAction() + "'";

            String sqlReq =
                    "update " + getTableName() + " set templateid=" + templateId + ", serviceid=" + serviceId +
                            ", status=" + status + ", creationdate=" + date + ", employeeid=" + employeeId
                            + ", orderaction=" + action
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
    public void create(final Order entity) throws SQLException, ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String values = entity.getId() + ", " + entity.getTemplateId() + "," + entity.getServiceId() + ", " +
                    entity.getEmployeeId() + ", '" + entity.getStatus() + "', '" + entity.getAction() + "', '" +
                    new java.sql.Date(entity.getCreationDate().getTime()) + "'";

            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            statement.executeUpdate(sqlReq);
        }
    }
}
