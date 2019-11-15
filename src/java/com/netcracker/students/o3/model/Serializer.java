package com.netcracker.students.o3.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

public class Serializer
{
    private ObjectMapper objectMapper;
    private File file;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Model model;


    public Serializer() throws IOException
    {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        objectMapper = new ObjectMapper(jsonFactory);
        file = new File("entities.json");
        if (!file.exists())
        {
            file.createNewFile();
        }
        model = Model.getInstance();
    }

    public void serializeModel() throws IOException
    {
        outputStream = new FileOutputStream(file);
        serializeMap(model.getOrders());
        serializeMap(model.getTemplates());
        serializeMap(model.getServices());
        serializeMap(model.getCustomers());
        serializeMap(model.getEmployers());
        serializeMap(model.getAreas());
        outputStream.close();
    }

    private <T> void serializeMap(Map<BigInteger, T> map) throws IOException
    {
        Collection<T> collection = map.values();
        for (T v : collection)
        {
            objectMapper.writeValue(outputStream, v);
        }
    }

    public void deserializeModel() throws IOException
    {
        inputStream = new FileInputStream(file);
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(inputStream);
        MappingIterator<HasId> iterator = objectMapper.readValues(parser, HasId.class);
        HasId t;
        while (iterator.hasNext())
        {
            t = iterator.nextValue();
            switch (t.getType()){
                case "customer": model.addCustomer((Customer)t);break;
                case "employee": model.addEmployee((Employee) t);break;
                case "order": model.addOrder((Order) t);break;
                case "template": model.addTemplate((Template) t);break;
                case "service": model.addService((Service) t);break;
                case "area": model.addArea((Area) t);break;
            }
        }
        inputStream.close();

    }


    //    public synchronized <T> void serializeList(HashMap<BigInteger, T> list, File file) throws IOException
    //    {
    //        OutputStream outputStream = new FileOutputStream(file, true);
    //        Collection<T> values = list.values();
    //        for (T o : values)
    //        {
    //            objectMapper.writeValue(outputStream, o);
    //        }
    //        outputStream.close();
    //    }
    //
    //    public synchronized <T extends HasId> HashMap<BigInteger, T> deserializeList(File file, Class cl) throws
    //    IOException
    //    {
    //        FileInputStream inputStream = new FileInputStream(file);
    //        HashMap<BigInteger, T> map = new HashMap<>();
    //        JsonFactory factory = new JsonFactory();
    //        JsonParser parser = factory.createParser(inputStream);
    //        MappingIterator<T> iterator = objectMapper.readValues(parser, cl);
    //        while (iterator.hasNext())
    //        {
    //            T t = iterator.nextValue();
    //            map.put(t.getId(), t);
    //        }
    //        return map;
    //    }
}
