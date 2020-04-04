package com.netcracker.students.o3.model.orders;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Table(name = "orders")
@XmlType(name = "order")
@XmlRootElement
public class OrderImpl implements Order
{
    @Id
    private BigInteger id;

    @Column(name = "templateid")
    private BigInteger templateId;

    @Column(name = "serviceid")
    private BigInteger serviceId;

    @Column(name = "employeeid")
    private BigInteger employeeId;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "orderaction")
    private OrderAction action;

    @Column(name = "creationdate")
    private Date creationDate;

    public OrderImpl()
    {
    }

    public OrderImpl(final BigInteger id, final BigInteger templateId, final BigInteger serviceId,
            final OrderStatus status, final OrderAction action)
    {
        this.id = id;
        this.templateId = templateId;
        this.serviceId = serviceId;
        this.status = status;
        this.action = action;
        this.creationDate = new Date();

    }

    public OrderImpl(final BigInteger templateId, final BigInteger serviceId, final OrderStatus status,
            final OrderAction action)
    {
        this.templateId = templateId;
        this.serviceId = serviceId;
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

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final OrderImpl order = (OrderImpl) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
