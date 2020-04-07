package com.netcracker.students.o3.model.dao.TemplateDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.templates.Template;

import java.sql.SQLException;
import java.util.List;

public interface TemplateDao extends Dao<Template>
{
    Template getTemplateByName(String templateName) throws SQLException;
}
