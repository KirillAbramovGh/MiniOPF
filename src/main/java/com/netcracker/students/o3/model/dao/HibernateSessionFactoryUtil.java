package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil
{
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil()
    {
    }

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                Configuration configuration = new Configuration().configure();

                configuration.addAnnotatedClass(AreaImpl.class);
                configuration.addAnnotatedClass(EmployerImpl.class);
                configuration.addAnnotatedClass(TemplateImpl.class);
                configuration.addAnnotatedClass(ServiceImpl.class);
                configuration.addAnnotatedClass(OrderImpl.class);
                configuration.addAnnotatedClass(CustomerImpl.class);

                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            }
            catch (Exception e)
            {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}