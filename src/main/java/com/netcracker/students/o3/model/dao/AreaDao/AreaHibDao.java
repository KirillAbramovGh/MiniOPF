package com.netcracker.students.o3.model.dao.AreaDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;

import org.hibernate.query.Query;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class AreaHibDao extends AbstractHibDao<Area> implements AreaDao
{
    @Override
    public Area getAreaByName(final String areaName)
    {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("from AreaImpl where name=:name");
        query.setParameter("name", areaName);
        return (Area) query.uniqueResult();
    }

    @Override
    public List<Area> getAll()
    {
        List<Area> areas =
                (List<Area>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From AreaImpl ")
                        .list();
        return areas;
    }

    @Override
    public Area getEntity(final BigInteger id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(AreaImpl.class, id);
    }

}
