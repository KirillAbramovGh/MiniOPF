package com.netcracker.students.o3.model.dao.CustomerDao;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.users.Customer;

import java.sql.SQLException;

public interface CustomerDao extends Dao<Customer>
{
    Customer getCustomerByLogin(String login) throws SQLException;
}
