package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AreaDao extends AbstractDao<Area>
{
    @Override
    public List<Area> getAll()
    {
        List<Area> areas = new ArrayList<>();


        try (Statement statement = getDbConnection().createStatement())
        {
            String sqlReq = "select * from areas";
            ResultSet resultSet = statement.executeQuery(sqlReq);

            for (Area area; resultSet.next(); )
            {
                area = new AreaImpl();
                area.setId(BigInteger.valueOf(resultSet.getInt("id")));
                area.setName(resultSet.getString("name"));
                area.setDescription(resultSet.getString("description"));
                areas.add(area);
            }
            resultSet.close();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    @Override
    public Area getEntityById(final BigInteger id)
    {
        Area area = new AreaImpl();
        try (Statement statement = getDbConnection().createStatement())
        {
            String sqlReq = "select * from areas where id=" + id;
            ResultSet resultSet = statement.executeQuery(sqlReq);

            area.setId(id);
            if (resultSet.next())
            {
                area.setName(resultSet.getString("name"));
                area.setDescription(resultSet.getString("description"));
            }
            resultSet.close();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return area;
    }

    @Override
    public void update(final Area entity)
    {
        try (Statement statement = getDbConnection().createStatement())
        {
            String name = "'" + entity.getName() + "'";
            String description = "'" + entity.getDescription() + "'";

            String sqlReq =
                    "update areas set name=" + name + ", description=" + description + " where id=" + entity.getId();
            System.out.println(sqlReq);
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(final BigInteger id)
    {
        try (Statement statement = getDbConnection().createStatement())
        {
            String sqlReq = "delete from areas where id=" + id;
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void create(final Area entity) throws ClassNotFoundException
    {
        try (Statement statement = getDbConnection().createStatement())
        {
            String values = entity.getId() + ",'" + entity.getName() + "','" + entity.getDescription() + "'";
            String sqlReq = "INSERT INTO areas VALUES (" + values + ")";
            statement.executeUpdate(sqlReq);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
