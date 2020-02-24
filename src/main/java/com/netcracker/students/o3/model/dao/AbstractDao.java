package com.netcracker.students.o3.model.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T>
{
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/entities";
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();



    public abstract List<T> getAll() throws SQLException, ClassNotFoundException;

    public abstract T getEntityById(BigInteger id) throws SQLException, ClassNotFoundException;

    public abstract void update(T entity) throws SQLException, ClassNotFoundException;

    protected abstract String getTableName();

    public void delete(BigInteger id) throws SQLException, ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "delete from " + getTableName() + " where id=" + id;
            statement.executeUpdate(sqlReq);
        }

    }

    public abstract void create(T entity) throws ClassNotFoundException, SQLException;

    protected Connection getConnection() throws ClassNotFoundException, SQLException
    {
        //return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);

        return connectionPool.getConnection();
    }
}
