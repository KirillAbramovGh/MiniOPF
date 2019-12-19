package com.netcracker.students.o3;

import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.view.StartConsoleView;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Model model = Model.getInstance();
        Serializer serializer = new SerializerImpl();
        serializer.deserializeModel(model);

//        Service service1 = new ServiceImpl();
//        service1.setUserId(BigInteger.valueOf(18));
//        service1.setTemplateId(BigInteger.ONE);
//        service1.setCost(BigDecimal.valueOf(900));
//        service1.setId(model.getNextId());
//        service1.setStatus(ServiceStatus.Entering);
//        model.addService(service1);
//        ArrayList<BigInteger> services = new ArrayList<>();
//        services.add(service1.getId());

//        model.getCustomerById(BigInteger.valueOf(18)).setConnectedServicesIds(services);

        model.getServiceById(BigInteger.valueOf(19));
     serializer.serializeModel(model);
    }
}
