package com.netcracker.students.o3.model.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LastIdDao
{
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/entities";
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String TABLE_NAME = "last_id";

    public BigInteger getLastId() throws SQLException
    {
        System.out.println("getLastId");
        BigInteger lastId = null;
        String sqlReq = "select * from " + TABLE_NAME + " where id=?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1, 1);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                lastId = resultSet.getBigDecimal("lastid").toBigInteger();
            }
            resultSet.close();
        }


        return lastId;
    }


    public void setLastId(final BigInteger entity) throws SQLException
    {
        System.out.println("SetLastId");
        String sqlReq =
                "update " + TABLE_NAME + " set lastid=? where id=?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1, entity.longValue());
            statement.setLong(2, 1);
            statement.executeUpdate();
        }
    }


    public void createLastId(final BigInteger entity) throws SQLException
    {
        System.out.println("createLastId");
        String sqlReq = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1, 1);
            statement.setLong(2, entity.longValue());
            statement.executeUpdate();
        }
    }

    public void deleteLastId() throws SQLException
    {
        System.out.println("deleteLastID");
        String sqlReq = "delete from " + TABLE_NAME + " where id=?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1, 1);
            statement.executeUpdate();
        }
    }

    protected Connection getConnection() throws SQLException
    {
//                try
//                {
//                    Class.forName(DRIVER_NAME);
//                }
//                catch (ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        return ConnectionPool.getInstance().getConnection();
    }
}
