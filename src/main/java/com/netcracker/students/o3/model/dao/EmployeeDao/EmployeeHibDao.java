package com.netcracker.students.o3.model.dao.EmployeeDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployeeImpl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class EmployeeHibDao extends AbstractHibDao<Employee> implements EmployeeDao
{
    @Override
    public Employee getEmployeeByLogin(final String login) throws SQLException
    {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException
    {
        List<Employee> employees =
                (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From EmployeeImpl ")
                        .list();
        return employees;
    }

    @Override
    public Employee getEntity(final BigInteger id) throws SQLException
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(EmployeeImpl.class, id);
    }

}
