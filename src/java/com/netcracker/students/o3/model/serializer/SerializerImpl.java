package com.netcracker.students.o3.model.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public class SerializerImpl implements Serializer
{

    public void serializeModel() throws IOException
    {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

        File file = new File("entities.json");
        if (!file.exists())
        {
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
       Model model = Model.getInstance();
        objectMapper.writeValue(outputStream,new Object[]{model.getCustomers().values(),model.getEmployers().values(),
        model.getTemplates().values(),model.getServices().values(),model.getAreas().values(),model.getOrders().values()});
        outputStream.close();
    }

    public void deserializeModel() throws IOException
    {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("entities.json");
        if (!file.exists())
        {
            file.createNewFile();
        }
        Model model = Model.getInstance();
       JsonNode node = objectMapper.readTree(file);
        Iterator<JsonNode> iterator = node.iterator();
        deserializeCustomers(model,iterator.next());
        deserializeEmployers(model,iterator.next());
        deserializeTemplates(model,iterator.next());
        deserializeServices(model,iterator.next());
        deserializeAreas(model,iterator.next());
        deserializeOrders(model,iterator.next());

    }

    private void deserializeCustomers(Model model,final JsonNode customers){
        for (JsonNode n: customers)
        {
            Customer customer = new CustomerImpl();
            customer.setId(BigInteger.valueOf(Integer.parseInt(n.get("id").asText())));
            customer.setName(n.get("name").asText());
            customer.setLogin(n.get("login").asText());
            customer.setPassword(n.get("password").asText());
            customer.setAreaId(BigInteger.valueOf(Integer.parseInt(n.get("areaId").asText())));

            model.addCustomer(customer);
        }
    }
    private void deserializeEmployers(Model model,final JsonNode employers){
        for (JsonNode n: employers)
        {
            Employee employee = new EmployerImpl();
            employee.setId(BigInteger.valueOf(Integer.parseInt(n.get("id").asText())));
            employee.setName(n.get("name").asText());
            employee.setLogin(n.get("login").asText());
            employee.setPassword(n.get("password").asText());
            model.addEmployee(employee);
        }
    }
    private void deserializeTemplates(Model model,final JsonNode templates){
        for (JsonNode n: templates)
        {
            Template template = new TemplateImpl();
            template.setId(BigInteger.valueOf(Integer.parseInt(n.get("id").asText())));
            template.setName(n.get("name").asText());
            template.setCost(BigDecimal.valueOf(Integer.parseInt(n.get("cost").asText())));
            template.setDescription(n.get("description").asText());
            model.addTemplate(template);
        }
    }

    private void deserializeServices(Model model,final JsonNode services){
        for (JsonNode n: services)
        {
            Service service = new ServiceImpl();
            service.setId(BigInteger.valueOf(Integer.parseInt(n.get("id").asText())));
            service.setCost(BigDecimal.valueOf(Integer.parseInt(n.get("cost").asText())));
            service.setTemplateId(BigInteger.valueOf(Integer.parseInt(n.get("templateId").asText())));
            service.setUserId(BigInteger.valueOf(Integer.parseInt(n.get("userId").asText())));

            model.addService(service);
        }
    }
    private void deserializeOrders(Model model,final JsonNode orders){
        for (JsonNode n: orders)
        {
            Order order = new OrderImpl();
            order.setId(BigInteger.valueOf(Integer.parseInt(n.get("id").asText())));
            order.setTemplateId(BigInteger.valueOf(Integer.parseInt(n.get("templateId").asText())));
            order.setServiceId(BigInteger.valueOf(Integer.parseInt(n.get("serviceId").asText())));
            order.setEmployeeId(BigInteger.valueOf(Integer.parseInt(n.get("employeeId").asText())));

            model.addOrder(order);
        }
    }
    private void deserializeAreas(Model model,final JsonNode areas){
        for (JsonNode n: areas)
        {
            Area area = new AreaImpl();
            area.setId(BigInteger.valueOf(Integer.parseInt(n.get("id").asText())));
            area.setName(n.get("name").asText());
            area.setDescription(n.get("description").asText());

            model.addArea(area);
        }
    }
}
