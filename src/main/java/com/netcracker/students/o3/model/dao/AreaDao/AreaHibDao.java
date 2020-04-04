package com.netcracker.students.o3.model.dao.AreaDao;

import com.netcracker.students.o3.model.area.Area;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class AreaHibDao implements AreaDao
{
    @Override
    public Area getAreaByName(final String areaName) throws SQLException
    {
        return null;
    }

    @Override
    public List<Area> getAll() throws SQLException
    {
        return null;
    }

    @Override
    public Area getEntity(final BigInteger id) throws SQLException
    {
        return null;
    }

    @Override
    public void update(final Area entity) throws SQLException
    {

    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {

    }

    @Override
    public void create(final Area entity) throws SQLException
    {

    }
}
