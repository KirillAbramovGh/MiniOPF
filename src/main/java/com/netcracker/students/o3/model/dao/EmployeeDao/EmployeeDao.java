package com.netcracker.students.o3.model.dao.EmployeeDao;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.users.Employee;

import java.sql.SQLException;

public interface EmployeeDao extends Dao<Employee>
{
    Employee getEmployeeByLogin(final String login) throws SQLException;
}
