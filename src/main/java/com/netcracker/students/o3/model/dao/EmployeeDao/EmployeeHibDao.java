package com.netcracker.students.o3.model.dao.EmployeeDao;

import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class EmployeeHibDao implements EmployeeDao
{
    @Override
    public Employee getEmployeeByLogin(final String login) throws SQLException
    {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException
    {
        return null;
    }

    @Override
    public Employee getEntity(final BigInteger id) throws SQLException
    {
        return null;
    }

    @Override
    public void update(final Employee entity) throws SQLException
    {

    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {

    }

    @Override
    public void create(final Employee entity) throws SQLException
    {

    }
}
