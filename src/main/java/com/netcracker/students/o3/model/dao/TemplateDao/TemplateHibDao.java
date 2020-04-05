package com.netcracker.students.o3.model.dao.TemplateDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AbstractHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class TemplateHibDao extends AbstractHibDao<Template> implements TemplateDao
{
    @Override
    public Template getTemplateByName(final String templateName)
    {
        return null;
    }

    @Override
    public List<Template> getTemplatesByAreaId(final BigInteger areaId)
    {
        return null;
    }

    @Override
    public List<Template> getAll()
    {
        List<Template> templates =
                (List<Template>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From TemplateImpl  ")
                        .list();
        return templates;
    }

    @Override
    public Template getEntity(final BigInteger id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TemplateImpl.class, id);
    }
}
