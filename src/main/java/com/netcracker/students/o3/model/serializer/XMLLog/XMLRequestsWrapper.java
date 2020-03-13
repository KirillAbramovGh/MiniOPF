package com.netcracker.students.o3.model.serializer.XMLLog;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class XMLRequestsWrapper
{
    private List<XMLRequest> requests;

    public XMLRequestsWrapper()
    {
        requests = new ArrayList<>();
    }

    public void addRequests(XMLRequest... req)
    {
        requests.addAll(Arrays.asList(req));
    }

    public int getNumOfRequests()
    {
        return requests.size();
    }

    public List<XMLRequest> getRequests()
    {
        return requests;
    }

    public void setRequests(final List<XMLRequest> requests)
    {
        this.requests = requests;
    }
}
