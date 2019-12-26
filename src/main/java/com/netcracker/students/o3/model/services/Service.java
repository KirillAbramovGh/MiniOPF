package com.netcracker.students.o3.model.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface Service
{
    BigInteger getId();

    void setId(final BigInteger id);

    BigInteger getUserId();

    void setUserId(final BigInteger userId);

    BigInteger getTemplateId();

    void setTemplateId(final BigInteger templateId);

    ServiceStatus getStatus();

    void setStatus(final ServiceStatus status);

    BigDecimal getCost();

    Date getActivationDate();

    void setActivationDate(final Date activationDate);

    String getName();

}
