package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AreaDao extends AbstractDao<Area>
{
    private static final String tableName = "areas";

    @Override
    public List<Area> getAll() throws SQLException, ClassNotFoundException
    {
        List<Area> areas = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
           try(ResultSet resultSet = statement.executeQuery(sqlReq))
           {

               for (Area area; resultSet.next(); )
               {
                   area = new AreaImpl();
                   area.setId(BigInteger.valueOf(resultSet.getInt("id")));
                   area.setName(resultSet.getString("name"));
                   area.setDescription(resultSet.getString("description"));

                   if(area.getName()==null){
                       continue;
                   }

                   areas.add(area);
               }
           }
        }
        return areas;
    }

    @Override
    public Area getEntityById(final BigInteger id) throws SQLException, ClassNotFoundException
    {
        Area area = new AreaImpl();
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                if (resultSet.next())
                {
                    area.setId(id);
                    area.setName(resultSet.getString("name"));
                    area.setDescription(resultSet.getString("description"));
                    if(area.getName() == null){
                        return null;
                    }
                }
            }
        }
        return area;
    }

    @Override
    public void update(final Area entity)
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String name = "'" + entity.getName() + "'";
            String description = "'" + entity.getDescription() + "'";

            String sqlReq =
                    "update " + getTableName() + " set name=" + name + ", description=" + description + " where id=" +
                            entity.getId();
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }

    @Override
    public void create(final Area entity) throws ClassNotFoundException
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String values = entity.getId() + ",'" + entity.getName() + "','" + entity.getDescription() + "'";
            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
