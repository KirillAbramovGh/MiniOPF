package com.netcracker.students.o3.model.dao.CustomerDao;

import com.netcracker.students.o3.model.users.Customer;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class CustomerHibDao implements CustomerDao
{
    @Override
    public Customer getCustomerByLogin(final String login) throws SQLException
    {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException
    {
        return null;
    }

    @Override
    public Customer getEntity(final BigInteger id) throws SQLException
    {
        return null;
    }

    @Override
    public void update(final Customer entity) throws SQLException
    {

    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {

    }

    @Override
    public void create(final Customer entity) throws SQLException
    {
    }
}
