package com.netcracker.students.o3.model.dao.ServiceDao;

import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;

import java.math.BigInteger;
import java.util.List;

public class ServiceHibDao extends AbstractHibDao<Service> implements ServiceDao
{
    @Override
    public List<Service> getServicesByUserId(final BigInteger userId)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByTemplateId(final BigInteger templateId)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByStatus(final ServiceStatus status)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByStatusAndCustomerId(final BigInteger userId, final ServiceStatus status)
    {
        return null;
    }

    @Override
    public List<Service> getAll()
    {
        List<Service> services =
                (List<Service>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                        .createQuery("From AreaImpl ")
                        .list();
        return services;
    }

    @Override
    public Service getEntity(final BigInteger id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ServiceImpl.class, id);
    }

}
