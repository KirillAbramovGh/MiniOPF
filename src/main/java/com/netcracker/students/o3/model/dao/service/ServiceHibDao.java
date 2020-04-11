package com.netcracker.students.o3.model.dao.service;

import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.hibernate.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.serialization.log.XMLLogController;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;

public class ServiceHibDao extends AbstractHibDao<Service> implements ServiceDao
{
    XMLLogController logController = XMLLogController.getInstance();
    @Override
    public List<Service> getServicesByCustomer(final Customer customer)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.
                createQuery("from ServiceImpl where customer=:customer");
        query.setParameter("customer", customer);
        List<Service> services = query.getResultList();
        transaction.commit();
        session.close();
        logController.addRequest("from ServiceImpl where customer=:"+customer.getId());
        return services;
    }

    @Override
    public List<Service> getServicesByTemplate(final Template template)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.
                createQuery("from ServiceImpl where template=:template");
        query.setParameter("template", template);
        List<Service> services = (List<Service>) query.getResultList();
        transaction.commit();
        session.close();
        logController.addRequest("from ServiceImpl where template=:"+template.getId());
        return services;
    }

    @Override
    public List<Service> getServicesByStatus(final ServiceStatus status)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.
                createQuery("from ServiceImpl where status=:status");
        query.setParameter("status", status);

        List<Service> services = (List<Service>) query.getResultList();
        transaction.commit();
        session.close();
        logController.addRequest("from ServiceImpl where status=:"+status);
        return services;
    }


    @Override
    public List<Service> getAll()
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Service> services =
                (List<Service>) session
                        .createQuery("From ServiceImpl ")
                        .list();
        transaction.commit();
        session.close();
        logController.addRequest("from ServiceImpl");
        return services;
    }

    @Override
    public Service getEntity(final BigInteger id)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Service service = session.get(ServiceImpl.class, id);
        transaction.commit();
        session.close();
        logController.addRequest("from ServiceImpl where id=:"+id);
        return service;
    }

    @Override
    public void delete(final BigInteger id)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("delete from ServiceImpl where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx1.commit();
        session.close();
        logController.addRequest("delete from ServiceImpl where id=:"+id);
    }

}
