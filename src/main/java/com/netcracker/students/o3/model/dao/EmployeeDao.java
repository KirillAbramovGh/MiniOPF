package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends AbstractDao<Employee>
{
    private static final String tableName = "employees";

    @Override
    public List<Employee> getAll() throws SQLException
    {
        System.out.println("employee getAll");
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try(ResultSet resultSet = statement.executeQuery(sqlReq))
            {

                for (Employee employee; resultSet.next(); )
                {
                    employee = new EmployerImpl();
                    employee.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    employee.setName(resultSet.getString("name"));
                    if(employee.getName()==null){
                        continue;
                    }
                    employee.setLogin(resultSet.getString("login"));
                    employee.setPassword(resultSet.getString("password"));

                    employees.add(employee);
                }
            }
        }


        return employees;
    }

    @Override
    public Employee getEntityById(final BigInteger id) throws SQLException
    {
        System.out.println("employee getEntityById");
        if(id== null){
            return null;
        }
        String sqlReq = "select * from " + getTableName() + " where id=?";
        Employee employee = new EmployerImpl();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
           statement.setLong(1,id.longValue());
            try(ResultSet resultSet = statement.executeQuery())
            {

                employee.setId(id);
                if (resultSet.next())
                {
                    employee.setName(resultSet.getString("name"));
                    if(employee.getName()==null){
                        return null;
                    }
                    employee.setLogin(resultSet.getString("login"));
                    employee.setPassword(resultSet.getString("password"));
                }
            }
        }

        return employee;
    }

    @Override
    public void update(final Employee entity) throws SQLException
    {
        System.out.println("employee update");
        String sqlReq =
                "update " + getTableName() + " set name=?, login=?, password=? where id=?";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setString(1,entity.getName());
            statement.setString(2,entity.getLogin());
            statement.setString(3,entity.getPassword());
            statement.setLong(4,entity.getId().longValue());

            statement.executeUpdate();
        }

    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }

    @Override
    public void create(final Employee entity) throws SQLException
    {
        System.out.println("employee create");
        String sqlReq = "INSERT INTO " + getTableName() + " VALUES (?,?,?,?)";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1,entity.getId().longValue());
            statement.setString(2,entity.getName());
            statement.setString(3,entity.getLogin());
            statement.setString(4,entity.getPassword());

            statement.executeUpdate();
        }

    }

    public Employee getEmployeeByLogin(final String login) throws SQLException
    {
        System.out.println("employee getByLogin");
        Employee employee = new EmployerImpl();
        String sqlReq = "select * from " + getTableName() + " where login=?";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setString(1,login);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    employee.setId(resultSet.getBigDecimal("id").toBigInteger());
                    employee.setName(resultSet.getString("name"));
                    if(employee.getName()==null){
                        return null;
                    }
                    employee.setLogin(resultSet.getString("login"));
                    employee.setPassword(resultSet.getString("password"));
                }
            }
        }

        return employee;
    }
}
