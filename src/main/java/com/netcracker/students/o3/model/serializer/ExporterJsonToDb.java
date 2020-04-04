package com.netcracker.students.o3.model.serializer;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.dao.AbstractJdbcDao;
import com.netcracker.students.o3.model.dao.AreaDao.AreaJdbcDao;
import com.netcracker.students.o3.model.dao.CustomerDao.CustomerJdbcDao;
import com.netcracker.students.o3.model.dao.EmployeeDao.EmployeeJdbcDao;
import com.netcracker.students.o3.model.dao.LastIdDao;
import com.netcracker.students.o3.model.dao.OrderDao.OrderJdbcDao;
import com.netcracker.students.o3.model.dao.ServiceDao.ServiceJdbcDao;
import com.netcracker.students.o3.model.dao.TemplateDao.TemplateJdbcDao;
import com.netcracker.students.o3.model.model.ModelJson;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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
        AbstractJdbcDao<Template> templateAbstractJdbcDao = new TemplateJdbcDao();

        for(Template template : templates){
            templateAbstractJdbcDao.create(template);
        }
    }

    private void exportServices(Collection<Service> services) throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Service> serviceAbstractJdbcDao = new ServiceJdbcDao();

        for(Service service : services){
            serviceAbstractJdbcDao.create(service);
        }
    }

    private void exportCustomers(Collection<Customer> customers) throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Customer> customerDao = new CustomerJdbcDao();

        for(Customer customer : customers){
            customerDao.create(customer);
        }
    }

    private void exportAreas(Collection<Area> areas) throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Area> serviceAbstractJdbcDao = new AreaJdbcDao();

        for(Area area : areas){
            serviceAbstractJdbcDao.create(area);
        }
    }

    private void exportEmployees(Collection<Employee> employees) throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Employee> serviceAbstractJdbcDao = new EmployeeJdbcDao();

        for(Employee employee : employees){
            serviceAbstractJdbcDao.create(employee);
        }
    }

    public void deleteAllAreasFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Area> dao = new AreaJdbcDao();
        List<Area> areas = dao.getAll();

        for(Area area : areas){
            dao.delete(area.getId());
        }
    }

    public void deleteAllServicesFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Service> dao = new ServiceJdbcDao();
        List<Service> services = dao.getAll();

        for(Service service : services){
            dao.delete(service.getId());
        }
    }
    public void deleteAllTemplatesFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Template> dao = new TemplateJdbcDao();
        List<Template> templates = dao.getAll();

        for(Template template : templates){
            dao.delete(template.getId());
        }
    }
    public void deleteAllOrdersFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Order> dao = new OrderJdbcDao();
        List<Order> areas = dao.getAll();

        for(Order order : areas){
            dao.delete(order.getId());
        }
    }

    public void deleteAllCustomersFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Customer> dao = new CustomerJdbcDao();
        List<Customer> customers = dao.getAll();

        for(Customer customer : customers){
            dao.delete(customer.getId());
        }
    }

    public void deleteAllEmployeesFromDb() throws SQLException, ClassNotFoundException
    {
        AbstractJdbcDao<Employee> dao = new EmployeeJdbcDao();
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
