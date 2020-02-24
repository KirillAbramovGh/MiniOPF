package com.netcracker.students.o3.model.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.model.model.ModelJson;

import java.io.File;
import java.io.IOException;

public class SerializerImpl implements Serializer
{

    public void serializeModel(ModelJson modelJson) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\Kirill\\IdeaProjects\\MiniOPF\\entities.json");
        objectMapper.writeValue(file, modelJson);
    }

    public void deserializeModel(ModelJson modelJson) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\Kirill\\IdeaProjects\\MiniOPF\\entities.json");
        ModelJson newModelJson = objectMapper.readValue(file, ModelJson.class);

        modelJson.setCustomers(newModelJson.getCustomers());
        modelJson.setEmployees(newModelJson.getEmployees());
        modelJson.setOrders(newModelJson.getOrders());
        modelJson.setTemplates(newModelJson.getTemplates());
        modelJson.setAreas(newModelJson.getAreas());
        modelJson.setServices(newModelJson.getServices());
        modelJson.setLastId(newModelJson.getLastId());
    }

    public String serializeToString(Object object) throws IOException
    {
        return new ObjectMapper().writeValueAsString(object);
    }


}
