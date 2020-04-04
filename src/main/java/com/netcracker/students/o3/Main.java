package com.netcracker.students.o3;

import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AreaDao.AreaHibDao;
import com.netcracker.students.o3.model.dao.CustomerDao.CustomerDao;
import com.netcracker.students.o3.model.dao.CustomerDao.CustomerHibDao;
import com.netcracker.students.o3.model.dao.HibernateSessionFactoryUtil;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelDb;
import com.netcracker.students.o3.model.serializer.ExporterJsonToDb;
import com.netcracker.students.o3.model.users.Customer;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBException;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, JAXBException
    {
        HibernateSessionFactoryUtil.getSessionFactory();
    }

    public static void downloadDataFromJSON() throws SQLException, ClassNotFoundException, IOException
    {
        Model model = ModelDb.getInstance();

        ExporterJsonToDb exporterJsonToDb = new ExporterJsonToDb();
        exporterJsonToDb.clearBd();
        exporterJsonToDb.exportToDb();
    }

    public static void deleteAllCustomers() throws SQLException
    {
        CustomerDao customerDao = new CustomerHibDao();
        List<Customer> customerList = customerDao.getAll();

        for(Customer customer : customerList){
            customerDao.delete(customer);
        }
    }

}
