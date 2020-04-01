package com.netcracker.students.o3.model.serializer.XMLLog;


import java.util.Date;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class XMLRequest<T>
{
    private Date date;
    private String query;
    private T[] result;

    @SafeVarargs
    public XMLRequest(String q, T... d)
    {
        date = new Date();
        query = q;
        result = d;
    }


    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(final String query)
    {
        this.query = query;
    }

    public T[] getResult()
    {
        return result;
    }

    public void setResult(final T[] result)
    {
        this.result = result;
    }


    public XMLRequest()
    {
    }

}
