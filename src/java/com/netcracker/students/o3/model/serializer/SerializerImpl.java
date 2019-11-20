package com.netcracker.students.o3.model.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.ModelJsonFormat;

import java.io.File;
import java.io.IOException;

public class SerializerImpl implements Serializer
{

    public void serializeModel() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("entities.json");
        if (!file.exists())
        {
            file.createNewFile();
        }
       Model model = Model.getInstance();
        ModelJsonFormat modelJsonFormat = new ModelJsonFormat();

        modelJsonFormat.setCustomers(model.getCustomers().values().toArray(modelJsonFormat.getCustomers()));
        modelJsonFormat.setEmployees(model.getEmployers().values().toArray(modelJsonFormat.getEmployees()));
        modelJsonFormat.setAreas(model.getAreas().values().toArray(modelJsonFormat.getAreas()));
        modelJsonFormat.setTemplates(model.getTemplates().values().toArray(modelJsonFormat.getTemplates()));
        modelJsonFormat.setOrders(model.getOrders().values().toArray(modelJsonFormat.getOrders()));
        modelJsonFormat.setServices(model.getServices().values().toArray(modelJsonFormat.getServices()));
        objectMapper.writeValue(file,modelJsonFormat);

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
       ModelJsonFormat o =  objectMapper.readValue(file,ModelJsonFormat.class);

    }




}
