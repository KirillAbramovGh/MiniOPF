package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.orders.Order;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T>
{
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/entities";
    private static final String DRIVER_NAME = "org.postgresql.Driver";


    public abstract List<T> getAll();
    public abstract T getEntityById(BigInteger id);
    public abstract void update(T entity);
    public abstract void delete(BigInteger id);
    public abstract void create(T entity) throws ClassNotFoundException;
    protected Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(DRIVER_NAME);
        return DriverManager.getConnection(CONNECTION_URL,USER_NAME,PASSWORD);
    }
}
