package com.netcracker.students.o3.model.dto;

import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.services.ServiceStatus;

import java.math.BigDecimal;
import java.math.BigInteger;

public class HtmlParser
{
    public String parseToHtml(Dto dto)
    {
        StringBuilder res = new StringBuilder("{</br>");
        for (String key : dto.getParameters().keySet())
        {
            Object value = dto.getParameters().get(key);
            appendPair(key, value, res).append("</br>");
        }
        res.append("}</br>");
        return res.toString();
    }

    private StringBuilder appendPair(String key, Object value, StringBuilder res)
    {
        res.append(key).append(":");
        appendObject(value, res);
        return res;
    }

    private void appendObject(Object value, StringBuilder res)
    {
        if (value instanceof BigInteger || value instanceof BigDecimal)
        {
            res.append(value);
        }
        else if (value instanceof String || value instanceof ServiceStatus || value instanceof OrderAction
                || value instanceof OrderStatus)
        {
            res.append('"').append(value).append('"');
        }
        else if (value instanceof Iterable)
        {
            try
            {
                appendCollection((Iterable) value, res);
            }
            catch (ClassCastException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            res.append(value);
        }
        res.append(",");
    }

    private void appendCollection(Iterable value, StringBuilder res)
    {
        res.append("[");
        for (Object obj : value)
        {
            appendObject(obj, res);
        }
        res.append("]");
    }
}
