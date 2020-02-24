package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TemplateDao extends AbstractDao<Template>
{
    private static final String tableName = "templates";
    private static final String templateAreaLinkTable = "template_area_link";

    @Override
    public List<Template> getAll() throws SQLException, ClassNotFoundException
    {
        List<Template> templates = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                ResultSet areaSet;
                String areaReq = "select * from "+templateAreaLinkTable + " where templateid=";


                for (Template template; resultSet.next(); )
                {
                    template = new TemplateImpl();
                    template.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    template.setName(resultSet.getString("name"));
                    template.setCost(resultSet.getBigDecimal("cost"));
                    template.setDescription(resultSet.getString("description"));

                    areaSet = getConnection().createStatement().executeQuery(areaReq+template.getId());

                    List<BigInteger> areaIds = new ArrayList<>();
                    for(BigInteger areaId;areaSet.next();){
                        areaIds.add(areaSet.getBigDecimal("areaid").toBigInteger());
                    }
                    template.setPossibleAreasId(areaIds);

                    templates.add(template);
                }
            }
        }


        return templates;
    }

    @Override
    public Template getEntityById(final BigInteger id)
    {
        Template template = new TemplateImpl();
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            String areaReq = "select * from "+templateAreaLinkTable + " where templateid=";
            ResultSet resultSet = statement.executeQuery(sqlReq);
            ResultSet areaSet;

            template.setId(id);
            if (resultSet.next())
            {
                template.setName(resultSet.getString("name"));
                template.setCost(resultSet.getBigDecimal("cost"));
                template.setDescription(resultSet.getString("description"));

                areaSet = statement.executeQuery(areaReq+template.getId());

                List<BigInteger> areaIds = new ArrayList<>();
                for(BigInteger areaId;areaSet.next();){
                    areaIds.add(areaSet.getBigDecimal("areaid").toBigInteger());
                }
                template.setPossibleAreasId(areaIds);
            }
            resultSet.close();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return template;
    }

    @Override
    public void update(final Template entity)
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String name = "'" + entity.getName() + "'";
            String cost = entity.getCost().toString();
            String description = "'" + entity.getDescription() + "'";


            String sqlReq =
                    "update " + getTableName() + " set name=" + name + ", cost=" + cost + ", description=" +
                            description + " where id=" + entity.getId();
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
    public void delete(final BigInteger id) throws SQLException, ClassNotFoundException
    {
        try(Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String areasReq = "delete from template_area_link where templateid="+id;
            statement.executeUpdate(areasReq);
            String sqlReq = "delete from " + getTableName() + " where id=" + id;
            statement.executeUpdate(sqlReq);
        }
    }

    @Override
    public void create(final Template entity)
    {
        try (Connection connection = getConnection();Statement statement = connection.createStatement())
        {
            String values = entity.getId() + ",'" + entity.getName() + "'," + entity.getCost() + ",'" +
                    entity.getDescription() + "'";
            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            System.out.println(sqlReq);

            statement.executeUpdate(sqlReq);

            for(BigInteger areaId : entity.getPossibleAreasId())
            {
                String templateAreaLink =
                        "INSERT INTO " + "template_area_link" + " VALUES (" + entity.getId() + ", " + areaId +")";
                statement.executeUpdate(templateAreaLink);
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
