package com.netcracker.students.o3.model.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.students.o3.model.Model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ServiceImpl implements Service
{
    private BigInteger id;
    private BigInteger userId;
    private BigInteger templateId;
    private ServiceStatus status;
    private Date activationDate;

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

    }

    public ServiceImpl(final BigInteger id, final BigInteger userId, final BigInteger templateId,
            final ServiceStatus status)
    {
        this.id = id;
        this.userId = userId;
        this.templateId = templateId;
        this.status = status;
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

    public BigDecimal getCost()
    {
        return Model.getInstance()
                .getTemplateById(templateId).getCost();
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
    public String getName()
    {
       return Model.getInstance().getTemplateById(templateId).getName();
    }
}
