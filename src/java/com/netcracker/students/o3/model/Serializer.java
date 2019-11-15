package com.netcracker.students.o3.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.HasId;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;

public class Serializer
{
    private ObjectMapper objectMapper;
    public Serializer(){
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
         objectMapper = new ObjectMapper(jsonFactory);
    }
    public synchronized <T> void serializeList(HashMap<BigInteger,T> list,File file) throws IOException
    {
        OutputStream outputStream = new FileOutputStream(file,true);
        Collection<T> values = list.values();
        for (T o: values)
        {
            objectMapper.writeValue(outputStream,o);
        }
        outputStream.close();
    }

    public synchronized <T extends HasId> HashMap<BigInteger,T> deserializeList(File file,Class cl) throws IOException
    {
        FileInputStream inputStream = new FileInputStream(file);
        HashMap<BigInteger,T> map = new HashMap<>();
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(inputStream);
        MappingIterator<T> iterator = objectMapper.readValues(parser,cl);
        while (iterator.hasNext()){
            T t = iterator.nextValue();
            map.put(t.getId(),t);
        }
        return map;
    }
}
