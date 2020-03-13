package com.netcracker.students.o3.model.serializer.XMLLog;


import java.util.Date;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class XMLRequest<T>
{
    Date date;
    String query;
    T[] data;

    @SafeVarargs
    public XMLRequest(String q, T... d)
    {
        date = new Date();
        query = q;
        data = d;
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

    public T[] getData()
    {
        return data;
    }

    public void setData(final T[] data)
    {
        this.data = data;
    }


    public XMLRequest()
    {
    }

}
