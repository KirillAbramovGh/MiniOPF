package com.netcracker.students.o3.model.dao.template;

import com.netcracker.students.o3.model.dao.Dao;
import com.netcracker.students.o3.model.templates.Template;

import java.sql.SQLException;

public interface TemplateDao extends Dao<Template>
{
    Template getTemplateByName(String templateName) throws SQLException;
}
