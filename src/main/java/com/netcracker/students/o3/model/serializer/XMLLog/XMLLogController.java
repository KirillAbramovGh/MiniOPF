package com.netcracker.students.o3.model.serializer.XMLLog;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.bind.JAXBException;

public class XMLLogController
{
    private static XMLLogController instance;

    private XMLRequestsWrapper wrapper;
    private XMLSerializer serializer;
    private int numOfLogs;
    private static final String rootName = "log.xml";

    private XMLLogController()
    {
        numOfLogs = 1;
        wrapper = new XMLRequestsWrapper();
        serializer = new XMLSerializer();
    }

    public void addRequest(String sqlReq, Area... d)
    {
        wrapper.addRequests(new XMLRequest<>(sqlReq, d));
        checkWrapperSize();
    }

    public void addRequest(String sqlReq, Service... d)
    {
        wrapper.addRequests(new XMLRequest<>(sqlReq, d));
        checkWrapperSize();
    }

    public void addRequest(String sqlReq, Template... d)
    {
        wrapper.addRequests(new XMLRequest<>(sqlReq, d));
        checkWrapperSize();
    }

    public void addRequest(String sqlReq, Order... d)
    {
        wrapper.addRequests(new XMLRequest<>(sqlReq, d));
        checkWrapperSize();
    }

    public void addRequest(String sqlReq, Customer... d)
    {
        wrapper.addRequests(new XMLRequest<>(sqlReq, d));
        checkWrapperSize();
    }

    public void addRequest(String sqlReq, Employee... d)
    {
        wrapper.addRequests(new XMLRequest<>(sqlReq, d));
        checkWrapperSize();
    }

    private void checkWrapperSize()
    {
        final int fileSize = 50;
        if (wrapper.getNumOfRequests() > fileSize)
        {
            serializer.serializeObjectToXML(wrapper,
                    "C:\\Users\\Kirill\\IdeaProjects\\MiniOPF\\logs\\" + numOfLogs + rootName);
            wrapper = new XMLRequestsWrapper();
            numOfLogs++;
        }
    }

    public static XMLLogController getInstance()
    {
        if (instance == null)
        {
            instance = new XMLLogController();
        }

        return instance;
    }
}
