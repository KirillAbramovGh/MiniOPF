package com.netcracker.students.o3.view;

import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class CustomerView implements View
{
    @Override
    public void start() throws IOException
    {
        Model model = Model.getInstance();
        Serializer serializer = new SerializerImpl();
        serializer.deserializeModel();
        Map<BigInteger, Customer> customers = model.getCustomers();
        Map<BigInteger, Employee> employers = model.getEmployers();
        for (Customer v: customers.values()
             )
        {
            System.out.println(v);
        }

        for (Employee v: employers.values()
        )
        {
            System.out.println(v);
        }
    }
}
