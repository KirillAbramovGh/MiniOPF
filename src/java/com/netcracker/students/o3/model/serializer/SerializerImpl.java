package com.netcracker.students.o3.model.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.io.File;
import java.io.IOException;

public class SerializerImpl implements Serializer
{

    public void serializeModel(Model model) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("entities.json");
        objectMapper.writeValue(file, model);
    }

    public void deserializeModel(Model model) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("entities.json");
        if (!file.exists())
        {
            file.createNewFile();
        }
        Model newModel = objectMapper.readValue(file, Model.class);

        for (Customer v : newModel.getCustomers().values())
        {
            model.addCustomer(v);
        }

        for (Employee v : newModel.getEmployers().values())
        {
            model.addEmployee(v);
        }
        for (Order v : newModel.getOrders().values())
        {
            model.addOrder(v);
        }
        for (Template v : newModel.getTemplates().values())
        {
            model.addTemplate(v);
        }
        for (Area v : newModel.getAreas().values())
        {
            model.addArea(v);
        }
        for (Service v : newModel.getServices().values())
        {
            model.addService(v);
        }
    }


}
