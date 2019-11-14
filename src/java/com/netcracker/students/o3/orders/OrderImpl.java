package com.netcracker.students.o3.orders;

import java.math.BigInteger;
import java.util.Date;

public class OrderImpl implements Order
{
    private BigInteger id;
    private BigInteger templateId;
    private BigInteger serviceId;
    private BigInteger employeeId;
    private OrderStatus status;
    private OrderAction action;
    private Date creationDate;

    public BigInteger getId()
    {
        return id;
    }

    public void setId(final BigInteger id)
    {
        this.id = id;
    }

    public BigInteger getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId(final BigInteger templateId)
    {
        this.templateId = templateId;
    }

    public BigInteger getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(final BigInteger serviceId)
    {
        this.serviceId = serviceId;
    }

    public BigInteger getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(final BigInteger employeeId)
    {
        this.employeeId = employeeId;
    }

    public OrderStatus getStatus()
    {
        return status;
    }

    public void setStatus(final OrderStatus status)
    {
        this.status = status;
    }

    public OrderAction getAction()
    {
        return action;
    }

    public void setAction(final OrderAction action)
    {
        this.action = action;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate)
    {
        this.creationDate = creationDate;
    }
}
