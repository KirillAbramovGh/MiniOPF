package com.netcracker.students.o3.model.serializer;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.dao.AbstractDao;
import com.netcracker.students.o3.model.dao.AreaDao;
import com.netcracker.students.o3.model.dao.CustomerDao;
import com.netcracker.students.o3.model.dao.EmployeeDao;
import com.netcracker.students.o3.model.dao.LastIdDao;
import com.netcracker.students.o3.model.dao.OrderDao;
import com.netcracker.students.o3.model.dao.ServiceDao;
import com.netcracker.students.o3.model.dao.TemplateDao;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelJson;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.server.ServerCloneException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class ExporterJsonToDb
{
    private Serializer serializer;
    private ModelJson model;

    public ExporterJsonToDb(){
        serializer = new SerializerImpl();
        model = ModelJson.getInstance();
    }

    public void exportToDb() throws IOException, SQLException, ClassNotFoundException
    {
        serializer.deserializeModel(model);

        exportAreas(model.getAreas().values());
        exportTemplates(model.getTemplates().values());
        exportEmployees(model.getEmployees().values());
        exportCustomers(model.getCustomers().values());
        exportServices(model.getServices().values());
        exportLastId(model.getLastId());
    }



    private void exportLastId(final BigInteger lastId) throws SQLException, ClassNotFoundException
    {
        LastIdDao lastIdDao = new LastIdDao();
        lastIdDao.createLastId(lastId);
    }

    private void exportTemplates(Collection<Template> templates) throws SQLException, ClassNotFoundException
    {
        AbstractDao<Template> templateAbstractDao = new TemplateDao();

        for(Template template : templates){
            templateAbstractDao.create(template);
        }
    }

    private void exportServices(Collection<Service> services) throws SQLException, ClassNotFoundException
    {
        AbstractDao<Service> serviceAbstractDao = new ServiceDao();

        for(Service service : services){
            serviceAbstractDao.create(service);
        }
    }

    private void exportCustomers(Collection<Customer> customers) throws SQLException, ClassNotFoundException
    {
        AbstractDao<Customer> customerDao = new CustomerDao();

        for(Customer customer : customers){
            customerDao.create(customer);
        }
    }

    private void exportAreas(Collection<Area> areas) throws SQLException, ClassNotFoundException
    {
        AbstractDao<Area> serviceAbstractDao = new AreaDao();

        for(Area area : areas){
            serviceAbstractDao.create(area);
        }
    }

    private void exportEmployees(Collection<Employee> employees) throws SQLException, ClassNotFoundException
    {
        AbstractDao<Employee> serviceAbstractDao = new EmployeeDao();

        for(Employee employee : employees){
            serviceAbstractDao.create(employee);
        }
    }

    public void deleteAllAreasFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractDao<Area> dao = new AreaDao();
        List<Area> areas = dao.getAll();

        for(Area area : areas){
            dao.delete(area.getId());
        }
    }

    public void deleteAllServicesFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractDao<Service> dao = new ServiceDao();
        List<Service> services = dao.getAll();

        for(Service service : services){
            dao.delete(service.getId());
        }
    }
    public void deleteAllTemplatesFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractDao<Template> dao = new TemplateDao();
        List<Template> templates = dao.getAll();

        for(Template template : templates){
            dao.delete(template.getId());
        }
    }
    public void deleteAllOrdersFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractDao<Order> dao = new OrderDao();
        List<Order> areas = dao.getAll();

        for(Order order : areas){
            dao.delete(order.getId());
        }
    }

    public void deleteAllCustomersFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractDao<Customer> dao = new CustomerDao();
        List<Customer> customers = dao.getAll();

        for(Customer customer : customers){
            dao.delete(customer.getId());
        }
    }

    public void deleteAllEmployeesFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractDao<Employee> dao = new EmployeeDao();
        List<Employee> employees = dao.getAll();

        for(Employee employee : employees){
            dao.delete(employee.getId());
        }
    }

    public void deleteLastIdFromDb() throws SQLException, ClassNotFoundException
    {
        LastIdDao dao = new LastIdDao();
        dao.deleteLastId();
    }

    public void clearBd() throws SQLException, ClassNotFoundException
    {
        deleteAllOrdersFromDb();
        deleteAllServicesFromDb();
        deleteAllTemplatesFromDb();
        deleteAllCustomersFromDb();
        deleteAllEmployeesFromDb();
        deleteAllAreasFromDb();
        deleteLastIdFromDb();
    }

}
