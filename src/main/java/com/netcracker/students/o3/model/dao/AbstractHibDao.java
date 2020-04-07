package com.netcracker.students.o3.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.sql.SQLException;

public abstract class AbstractHibDao<T> implements Dao<T>
{
    @Override
    public void update(final T entity)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void create(final T entity)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        if (entity != null)
        {
            session.save(entity);
        }
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        T entity = getEntity(id);
        if (entity != null)
        {
            session.delete(entity);
        }
        else
        {
            System.out.println("entity by id=" + id + " isn't exist");
        }
        tx1.commit();
        session.close();
    }


    public void delete(final T entity)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        if (entity != null)
        {
            session.delete(entity);
        }
        else
        {
            System.out.println("entity = NULL isn't exist");
        }
        tx1.commit();
        session.close();
    }
}
