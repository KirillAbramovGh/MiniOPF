package com.netcracker.students.o3.model.dao.AreaDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.dao.Dao;

import java.sql.SQLException;

public interface AreaDao extends Dao<Area>
{
    Area getAreaByName(String areaName) throws SQLException;
}
