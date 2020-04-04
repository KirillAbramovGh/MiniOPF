package com.netcracker.students.o3.model.orders;

import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @ManyToOne
    @JoinColumn(name = "templateid")
    private Template template;

    @OneToOne
    @JoinColumn(name = "serviceid")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "employeeid")
    private Employee employee;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "orderaction")
    private OrderAction action;

    @Column(name = "creationdate")
    private Date creationDate;

    public OrderImpl()
    {
    }

    public OrderImpl(final BigInteger id, final Template template, final Service service,
            final OrderStatus status, final OrderAction action)
    {
        this.id = id;
        this.template = template;
        this.service = service;
        this.status = status;
        this.action = action;
        this.creationDate = new Date();

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

    public Template getTemplate()
    {
        return template;
    }

    public void setTemplate(final Template template)
    {
        this.template = template;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(final Service service)
    {
        this.service = service;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(final Employee employee)
    {
        this.employee = employee;
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
