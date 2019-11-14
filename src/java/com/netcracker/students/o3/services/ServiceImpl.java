package com.netcracker.students.o3.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ServiceImpl implements Service
{
    private BigInteger id;
    private BigInteger userId;
    private BigInteger templateId;
    private ServiceStatus status;
    private BigDecimal cost;
    private Date activationDate;

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
        return cost;
    }

    public void setCost(final BigDecimal cost)
    {
        this.cost = cost;
    }

    public Date getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate(final Date activationDate)
    {
        this.activationDate = activationDate;
    }
}
