package com.netcracker.students.o3.model.dao.TemplateDao;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface TemplateDao extends Dao<Template>
{
    Template getTemplateByName(String templateName) throws SQLException;

    List<Template> getTemplatesByAreaId(BigInteger areaId) throws SQLException;
}
