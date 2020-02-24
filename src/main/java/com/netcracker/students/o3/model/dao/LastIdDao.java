package com.netcracker.students.o3.model.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LastIdDao
{
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/entities";
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String TABLE_NAME = "last_id";

    public BigInteger getLastId() throws SQLException, ClassNotFoundException
    {
        BigInteger lastId = null;
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + TABLE_NAME + " where id=" + 1;
            ResultSet resultSet = statement.executeQuery(sqlReq);

            if (resultSet.next())
            {
                lastId = resultSet.getBigDecimal("lastid").toBigInteger();
                System.out.println(lastId);
            }
            resultSet.close();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return lastId;
    }


    public void setLastId(final BigInteger entity) throws SQLException, ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {

            String sqlReq =
                    "update " + TABLE_NAME + " set lastid=" + entity +" where id=" + 1;
            statement.executeUpdate(sqlReq);
        }

    }


    public void createLastId(final BigInteger entity) throws ClassNotFoundException, SQLException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {

            String sqlReq = "INSERT INTO " + TABLE_NAME + " VALUES (" + 1 +","+ entity + ")";

            statement.executeUpdate(sqlReq);

        }

    }

    public void deleteLastId() throws SQLException, ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {

            String sqlReq = "delete from " + TABLE_NAME + " where id=" + 1;
            statement.executeUpdate(sqlReq);
        }
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(DRIVER_NAME);
        return DriverManager.getConnection(CONNECTION_URL,USER_NAME,PASSWORD);
    }
}
