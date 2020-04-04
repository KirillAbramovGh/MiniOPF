package com.netcracker.students.o3.model.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.students.o3.model.model.ModelDb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Table(name = "services")
@XmlType(name = "service")
@XmlRootElement
public class ServiceImpl implements Service
{
    @Id
    private BigInteger id;

    @Column(name = "userid")
    private BigInteger userId;

    @Column(name = "templateid")
    private BigInteger templateId;

    @Column(name = "status")
    private ServiceStatus status;

    @Column(name = "activationdate")
    private Date activationDate;

    public ServiceImpl(final BigInteger userId, final BigInteger templateId, final ServiceStatus status)
    {
        this.userId = userId;
        this.templateId = templateId;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ServiceImpl{" +
                "id=" + id +
                ", userId=" + userId +
                ", templateId=" + templateId +
                ", status=" + status +
                ", cost=" + getCost() +
                ", activationDate=" + activationDate +
                '}';
    }

    public ServiceImpl()
    {
        this.activationDate = new Date();
    }

    public ServiceImpl(final BigInteger id, final BigInteger userId, final BigInteger templateId,
            final ServiceStatus status)
    {
        this.id = id;
        this.userId = userId;
        this.templateId = templateId;
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

    public BigInteger getUserId()
    {
        return userId;
    }

    public void setUserId(final BigInteger userId)
    {
        this.userId = userId;
    }

    public BigInteger getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId(final BigInteger templateId)
    {
        this.templateId = templateId;
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
    public BigDecimal getCost()
    {
        return ModelDb.getInstance()
                .getTemplate(templateId).getCost();
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
