package com.netcracker.students.o3.model.dao.CustomerDao;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AreaDao.AreaDao;
import com.netcracker.students.o3.model.dao.AreaDao.AreaHibDao;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class CustomerHibDaoTest
{
    Area areaForFistCustomer;
    Area areaForSecondCustomer;

    CustomerDao customerDao;
    AreaDao areaDao;

    Customer firstCustomer;
    Customer secondCustomer;

    @Before
    public void setUp() throws SQLException
    {
        customerDao = new CustomerHibDao();
        areaDao = new AreaHibDao();

        areaForFistCustomer = new AreaImpl(BigInteger.valueOf(1), "firstAreaName", "firstAreaDescription");
        areaForFistCustomer = new AreaImpl(BigInteger.valueOf(2), "secondAreaName", "secondAreaDescription");

        firstCustomer = new CustomerImpl(BigInteger.valueOf(3), "firstCustomer", "firstCustomerLogin",
                "firstCustomerPassword", areaForFistCustomer);

        secondCustomer = new CustomerImpl(BigInteger.valueOf(4), "secondCustomer", "secondCustomerLogin",
                "secondCustomerPassword", areaForSecondCustomer);

        customerDao.delete(firstCustomer);
        customerDao.delete(secondCustomer);
//        try
//        {
//            deleteAllCustomers();
//            deleteAllAreas();
//        }
//        catch (Exception e)
//        {
//            System.out.println("!Exception " + e);
//        }
    }

    @After
    public void tearDown()
    {
        try
        {
            deleteAllCustomers();
            deleteAllAreas();
        }
        catch (Exception e)
        {
            System.out.println("!Exception " + e);
        }
    }

    private void deleteAllAreas() throws SQLException
    {
        List<Area> areas = areaDao.getAll();

        for (Area area : areas)
        {
            areaDao.delete(area);
        }
    }

    private void deleteAllCustomers() throws SQLException
    {
        List<Customer> customers = customerDao.getAll();

        for (Customer customer : customers)
        {
            customerDao.delete(customer);
        }
    }

    @Test
    public void createCustomersGetAll() throws SQLException
    {
//        areaDao.create(areaForFistCustomer);
//        areaDao.create(areaForSecondCustomer);
//
//        customerDao.create(firstCustomer);
//        customerDao.create(secondCustomer);
    }
}