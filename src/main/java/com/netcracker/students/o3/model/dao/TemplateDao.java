package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public List<Template> getAll() throws SQLException
    {
        System.out.println("TemplateDao.getAll()");

        List<Template> templates = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                ResultSet areaSet;
                String areaReq = "select * from " + templateAreaLinkTable + " where templateid=";


                for (Template template; resultSet.next(); )
                {
                    template = new TemplateImpl();
                    template.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    template.setName(resultSet.getString("template_name"));
                    template.setCost(resultSet.getBigDecimal("cost"));
                    template.setDescription(resultSet.getString("description"));

                    areaSet = getConnection().createStatement().executeQuery(areaReq + template.getId());

                    List<BigInteger> areaIds = new ArrayList<>();
                    for (BigInteger areaId; areaSet.next(); )
                    {
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
    public Template getEntityById(final BigInteger id) throws SQLException
    {
        Template template;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where id=" + id;
            String areaReq = "select * from " + templateAreaLinkTable + " where templateid=";

            template = getTemplateFromResultSet(statement, sqlReq, areaReq + id);
        }
        return template;
    }

    @Override
    public void update(final Template entity) throws SQLException
    {
        String sqlReq =
                "update " + getTableName() + " set template_name=?, cost=?, description=? where id=?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setString(1,entity.getName());
            statement.setBigDecimal(2,entity.getCost());
            statement.setString(3,entity.getDescription());
            statement.setLong(4,entity.getId().longValue());

            statement.executeUpdate();
        }

    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }

    @Override
    public void delete(final BigInteger id) throws SQLException
    {
        System.out.println("TemplateDao.delete(" + id + ") ");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String areasReq = "delete from template_area_link where templateid=" + id;
            statement.executeUpdate(areasReq);
            String sqlReq = "delete from " + getTableName() + " where id=" + id;
            statement.executeUpdate(sqlReq);
        }
    }

    @Override
    public void create(final Template entity) throws SQLException
    {
        System.out.println("TemplateDao.create(" + entity + ") ");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String values = entity.getId() + ",'" + entity.getName() + "'," + entity.getCost() + ",'" +
                    entity.getDescription() + "'";
            String sqlReq = "INSERT INTO " + getTableName() + " VALUES (" + values + ")";
            System.out.println(sqlReq);

            statement.executeUpdate(sqlReq);

            for (BigInteger areaId : entity.getPossibleAreasId())
            {
                String templateAreaLink =
                        "INSERT INTO " + "template_area_link" + " VALUES (" + entity.getId() + ", " + areaId + ")";
                statement.executeUpdate(templateAreaLink);
            }
        }

    }

    public Template getTemplateByName(String templateName) throws SQLException
    {
        System.out.println("TemplateDao.getEntityById(" + templateName + ") ");

        Template template = new TemplateImpl();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName() + " where template_name='" + templateName + "'";
            String areaReq = "select * from " + templateAreaLinkTable + " where templateid=";
            ResultSet resultSet = statement.executeQuery(sqlReq);
            ResultSet areaSet;

            if (resultSet.next())
            {
                template.setId(resultSet.getBigDecimal("id").toBigInteger());
                template.setName(resultSet.getString("template_name"));
                template.setCost(resultSet.getBigDecimal("cost"));
                template.setDescription(resultSet.getString("description"));

                areaSet = statement.executeQuery(areaReq + template.getId());

                List<BigInteger> areaIds = new ArrayList<>();
                for (BigInteger areaId; areaSet.next(); )
                {
                    areaIds.add(areaSet.getBigDecimal("areaid").toBigInteger());
                }
                template.setPossibleAreasId(areaIds);
            }
            resultSet.close();
        }


        return template;
    }

    public List<Template> getTemplatesByAreaId(BigInteger areaId) throws SQLException
    {
        List<Template> templates = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + templateAreaLinkTable + " where areaid=" + areaId;
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                List<BigInteger> templateIds = new ArrayList<>();
                for (; resultSet.next(); )
                {
                    templateIds.add(resultSet.getBigDecimal("templateid").toBigInteger());
                }
                for (BigInteger id : templateIds)
                {
                    templates.add(getEntityById(id));
                }
            }
        }

        return templates;
    }

    private Template getTemplateFromResultSet(Statement statement, String temSql, String areaSql) throws SQLException
    {
        ResultSet resultSet = statement.executeQuery(temSql);
        if (!resultSet.next() || resultSet.getString("template_name") == null)
        {
            return null;
        }
        Template template = new TemplateImpl();
        template.setId(resultSet.getBigDecimal("id").toBigInteger());
        template.setName(resultSet.getString("template_name"));
        template.setCost(resultSet.getBigDecimal("cost"));
        template.setDescription(resultSet.getString("description"));

        ResultSet areaSet = statement.executeQuery(areaSql);
        List<BigInteger> areaIds = new ArrayList<>();
        for (; areaSet.next(); )
        {
            areaIds.add(areaSet.getBigDecimal("areaid").toBigInteger());
        }
        template.setPossibleAreasId(areaIds);
        resultSet.close();
        areaSet.close();
        return template;
    }

    private List<Template> getTemplates(String sqlReq) throws SQLException
    {
        System.out.println("TemplateDao.getAll()");

        List<Template> templates = new ArrayList<>();


        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                ResultSet areaSet;
                String areaReq = "select * from " + templateAreaLinkTable + " where templateid=";


                for (Template template; resultSet.next(); )
                {
                    template = new TemplateImpl();
                    template.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    template.setName(resultSet.getString("template_name"));
                    template.setCost(resultSet.getBigDecimal("cost"));
                    template.setDescription(resultSet.getString("description"));

                    areaSet = getConnection().createStatement().executeQuery(areaReq + template.getId());

                    List<BigInteger> areaIds = new ArrayList<>();
                    for (BigInteger areaId; areaSet.next(); )
                    {
                        areaIds.add(areaSet.getBigDecimal("areaid").toBigInteger());
                    }
                    template.setPossibleAreasId(areaIds);

                    templates.add(template);
                }
            }
        }


        return templates;
    }
}
