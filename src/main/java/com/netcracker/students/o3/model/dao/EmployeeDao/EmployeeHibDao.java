package com.netcracker.students.o3.model.dao.EmployeeDao;

import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployeeImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class EmployeeHibDao extends AbstractHibDao<Employee> implements EmployeeDao
{
    @Override
    public Employee getEmployeeByLogin(final String login)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from EmployeeImpl where login=:login");
        query.setParameter("login", login);
        Employee employee = (Employee) query.uniqueResult();
        tx1.commit();
        session.close();

        return employee;
    }

    @Override
    public List<Employee> getAll()
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Employee> employees =
                (List<Employee>) session
                        .createQuery("From EmployeeImpl ")
                        .list();
        tx1.commit();
        session.close();
        return employees;
    }

    @Override
    public Employee getEntity(final BigInteger id) throws SQLException
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(EmployeeImpl.class, id);
        transaction.commit();
        session.close();
        return employee;
    }

    @Override
    public void delete(final BigInteger id)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("delete from EmployeeImpl where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx1.commit();
        session.close();
    }

}
