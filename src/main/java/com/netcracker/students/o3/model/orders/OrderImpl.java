package com.netcracker.students.o3.model.orders;

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

    public OrderImpl()
    {
    }

    public OrderImpl(final BigInteger id, final BigInteger templateId, final BigInteger serviceId,
            final BigInteger employeeId,
            final OrderStatus status, final OrderAction action)
    {
        this.id = id;
        this.templateId = templateId;
        this.serviceId = serviceId;
        this.employeeId = employeeId;
        this.status = status;
        this.action = action;
    }

    @Override
    public String toString()
    {
        return "OrderImpl{" +
                "id=" + id +
                ", status=" + status +
                ", action=" + action +
                ", creationDate=" + creationDate +
                '}';
    }


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
