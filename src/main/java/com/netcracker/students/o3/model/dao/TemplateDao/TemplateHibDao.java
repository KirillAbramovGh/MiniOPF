package com.netcracker.students.o3.model.dao.TemplateDao;

import com.netcracker.students.o3.model.templates.Template;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class TemplateHibDao implements TemplateDao
{
    @Override
    public Template getTemplateByName(final String templateName) throws SQLException
    {
        return null;
    }

    @Override
    public List<Template> getTemplatesByAreaId(final BigInteger areaId) throws SQLException
    {
        return null;
    }

    @Override
    public List<Template> getAll() throws SQLException
    {
        return null;
    }

    @Override
    public Template getEntity(final BigInteger id) throws SQLException
    {
        return null;
    }

    @Override
    public void update(final Template entity) throws SQLException
    {

    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {

    }

    @Override
    public void delete(final Template entity) throws SQLException
    {

    }

    @Override
    public void create(final Template entity) throws SQLException
    {

    }
}
