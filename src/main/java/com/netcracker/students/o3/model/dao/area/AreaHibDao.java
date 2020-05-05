package com.netcracker.students.o3.model.dao.area;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.hibernate.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.serialization.log.XMLLogController;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;

public class AreaHibDao extends AbstractHibDao<Area> implements AreaDao
{
    XMLLogController logController = XMLLogController.getInstance();
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
        logController.addRequest("from AreaImpl where name=:"+areaName);
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
        logController.addRequest("From AreaImpl");
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
        logController.addRequest("from AreaImpl where id=:"+id);
        return area;
    }

}
