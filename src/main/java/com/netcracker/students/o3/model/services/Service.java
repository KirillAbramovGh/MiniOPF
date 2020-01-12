package com.netcracker.students.o3.model.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * service which could be connected to customer
 */
public interface Service
{
    /**
     * @return service id
     */
    BigInteger getId();

    /**
     * set service id
     * @param id
     */
    void setId(final BigInteger id);

    /**
     * @return user id if service connected to him
     */
    BigInteger getUserId();

    /**
     * set user id
     * @param userId
     */
    void setUserId(final BigInteger userId);

    /**
     * @return template of service
     */
    BigInteger getTemplateId();

    /**
     * set template id
     * @param templateId
     */
    void setTemplateId(final BigInteger templateId);

    /**
     * @return status of service
     */
    ServiceStatus getStatus();

    /**
     * set service status
     * @param status
     */
    void setStatus(final ServiceStatus status);

    /**
     * @return cost of service from template
     */
    BigDecimal getCost();

    /**
     * @return date of activation
     */
    Date getActivationDate();

    /**
     * set activation date
     * @param activationDate
     */
    void setActivationDate(final Date activationDate);

    /**
     * @return service name
     */
    String getName();

}
