package com.netcracker.students.o3;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AreaDao.AreaHibDao;
import com.netcracker.students.o3.model.dao.CustomerDao.CustomerHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.dao.TemplateDao.TemplateHibDao;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelDb;
import com.netcracker.students.o3.model.serializer.ExporterJsonToDb;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;

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
        CustomerHibDao customerHibDao = new CustomerHibDao();

        Area area = new AreaImpl(BigInteger.ONE,"areaName","areaDescription");
        List<Area> areaList = new ArrayList<>();
        areaList.add(area);
        Template template = new TemplateImpl(BigInteger.TWO,"templateName", BigDecimal.TEN,"descr");
        template.setPossibleAreas(areaList);
        Customer customer = new CustomerImpl(BigInteger.valueOf(3),"customerName","customerLogin",
                "customerPassword",area);

        areaHibDao.create(area);
        templateHibDao.create(template);
        customerHibDao.create(customer);

        System.out.println(templateHibDao.getEntity(template.getId()));
        System.out.println(areaHibDao.getEntity(area.getId()));
        System.out.println(customerHibDao.getEntity(BigInteger.valueOf(3)));
    }

    public static void downloadDataFromJSON() throws SQLException, ClassNotFoundException, IOException
    {
        Model model = ModelDb.getInstance();

        ExporterJsonToDb exporterJsonToDb = new ExporterJsonToDb();
        exporterJsonToDb.clearBd();
        exporterJsonToDb.exportToDb();
    }

}
