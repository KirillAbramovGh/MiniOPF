package com.netcracker.students.o3.model.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "services")
@XmlType(name = "service")
@XmlRootElement
public class ServiceImpl implements Service
{
    @Id
    private BigInteger id;

    @ManyToOne()
    @JoinColumn(name = "userid")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "templateid")
    private Template template;

    @Column(name = "status")
    private ServiceStatus status;

    @Column(name = "activationdate")
    private Date activationDate;


    @Override
    public String toString()
    {
        return "ServiceImpl{" +
                "id=" + id +
                ", userId=" + customer +
                ", template=" + template +
                ", status=" + status +
                ", cost=" + templateGetCost() +
                ", activationDate=" + activationDate +
                '}';
    }

    public ServiceImpl()
    {
        this.activationDate = new Date();
    }

    public ServiceImpl(final BigInteger id, final Customer customer, final Template template,
            final ServiceStatus status)
    {
        this.id = id;
        this.customer = customer;
        this.template = template;
        this.status = status;
        this.activationDate = new Date();
    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(final BigInteger id)
    {
        this.id = id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(final Customer customer)
    {
        this.customer = customer;
    }

    public Template getTemplate()
    {
        return template;
    }

    public void setTemplate(final Template template)
    {
        this.template = template;
    }

    public ServiceStatus getStatus()
    {
        return status;
    }

    public void setStatus(final ServiceStatus status)
    {
        this.status = status;
    }

    @JsonIgnore
    public BigDecimal templateGetCost()
    {
        return template.getCost();
    }

    public Date getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate(final Date activationDate)
    {
        this.activationDate = activationDate;
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
        final ServiceImpl service = (ServiceImpl) o;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
