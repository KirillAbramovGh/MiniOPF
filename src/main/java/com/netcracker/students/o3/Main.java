package com.netcracker.students.o3;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AreaDao.AreaHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.dao.TemplateDao.TemplateHibDao;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelDb;
import com.netcracker.students.o3.model.serializer.ExporterJsonToDb;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, JAXBException
    {
        HibernateSessionFactoryUtil.getSessionFactory();
        AreaHibDao areaHibDao = new AreaHibDao();
        TemplateHibDao templateHibDao = new TemplateHibDao();

        Area area = new AreaImpl(BigInteger.ONE,"areaName","areaDescription");
        List<Area> areaList = new ArrayList<>();
        areaList.add(area);
        Template template = new TemplateImpl(BigInteger.TWO,"templateName", BigDecimal.TEN,"descr");
        template.setPossibleAreas(areaList);

        //areaHibDao.create(area);
        //templateHibDao.create(template);

        System.out.println(templateHibDao.getEntity(template.getId()));
    }

    public static void downloadDataFromJSON() throws SQLException, ClassNotFoundException, IOException
    {
        Model model = ModelDb.getInstance();

        ExporterJsonToDb exporterJsonToDb = new ExporterJsonToDb();
        exporterJsonToDb.clearBd();
        exporterJsonToDb.exportToDb();
    }

}
