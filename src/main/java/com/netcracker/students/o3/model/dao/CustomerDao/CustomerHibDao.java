package com.netcracker.students.o3.model.dao.CustomerDao;

import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;

import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;

public class CustomerHibDao extends AbstractHibDao<Customer> implements CustomerDao
{
    @Override
    public Customer getCustomerByLogin(final String login)
    {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("from CustomerImpl where login=:login");
        query.setParameter("login", login);

        return (Customer) query.uniqueResult();
    }

    @Override
    public List<Customer> getAll()
    {
        List<Customer> customers = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("from CustomerImpl ").list();
        return customers;
    }

    @Override
    public Customer getEntity(final BigInteger id)
    {
        Customer customer = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .get(CustomerImpl.class, id);
        return customer;
    }

}
