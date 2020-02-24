package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends AbstractDao<Employee>
{
    private static final String tableName = "employees";

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException
    {
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
    public Employee getEntityById(final BigInteger id) throws SQLException, ClassNotFoundException
    {
        Employee employee = new EmployerImpl();
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            try(ResultSet resultSet = statement.executeQuery(sqlReq))
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
    public void update(final Employee entity)
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String name = "'" + entity.getName() + "'";
            String login = "'" + entity.getLogin() + "'";
            String password = "'" + entity.getPassword() + "'";

            String sqlReq =
                    "update " + getTableName() + " set name=" + name + ", login=" + login + ", password=" + password +
                            " where id=" + entity.getId();
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
    public void create(final Employee entity)
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String values = entity.getId() + ",'" + entity.getName() + "','" + entity.getLogin() + "','" +
                    entity.getPassword() + "'";
            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
