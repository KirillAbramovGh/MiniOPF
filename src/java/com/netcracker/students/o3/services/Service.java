package com.netcracker.students.o3.services;

import com.netcracker.students.o3.HasId;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface Service extends HasId
{
    public BigInteger getId();

    public void setId(final BigInteger id);

    public BigInteger getUserId();

    public void setUserId(final BigInteger userId);

    public BigInteger getTemplateId();

    public void setTemplateId(final BigInteger templateId);

    public ServiceStatus getStatus();

    public void setStatus(final ServiceStatus status);

    public BigDecimal getCost();

    public void setCost(final BigDecimal cost);

    public Date getActivationDate();

    public void setActivationDate(final Date activationDate);
}
