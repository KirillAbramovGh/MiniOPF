package com.netcracker.students.o3.model.serializer.XMLLog;

import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLSerializer
{
    private static final Class[] classes = new Class[]{
            XMLRequestsWrapper.class,XMLRequest.class,
            HashSet.class, HashMap.class,
            CustomerImpl.class, EmployerImpl.class, AreaImpl.class,
            OrderImpl.class, TemplateImpl.class, ServiceImpl.class
    };
    public void serializeObjectToXML(XMLRequestsWrapper o,String fileName) throws JAXBException, IOException
    {
        Writer writer = new FileWriter(fileName);


        JAXBContext context = JAXBContext.newInstance(classes);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(o, writer);

    }


}
