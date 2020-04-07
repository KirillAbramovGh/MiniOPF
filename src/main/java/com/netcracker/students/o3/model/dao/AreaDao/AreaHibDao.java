package com.netcracker.students.o3.model.dao.AreaDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class AreaHibDao extends AbstractHibDao<Area> implements AreaDao
{
    @Override
    public Area getAreaByName(final String areaName)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.
                createQuery("from AreaImpl where name=:name");
        query.setParameter("name", areaName);
        Area area = (Area) query.uniqueResult();
        tx1.commit();
        session.close();
        return area;
    }

    @Override
    public List<Area> getAll()
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Area> areas =
                (List<Area>) session.createQuery("From AreaImpl ")
                        .list();
        tx1.commit();
        session.close();
        return areas;
    }

    @Override
    public Area getEntity(final BigInteger id)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Area area = session.get(AreaImpl.class, id);
        tx1.commit();
        session.close();
        return area;
    }

    @Override
    public void delete(final BigInteger id)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("delete from AreaImpl where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx1.commit();
        session.close();
    }
}
