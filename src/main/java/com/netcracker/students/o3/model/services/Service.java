package com.netcracker.students.o3.model.services;

import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * service which could be connected to customer
 */

@Table(name = "services")
public interface Service
{
    /**
     * @return service id
     */
    @Id
    BigInteger getId();

    /**
     * set service id
     *
     * @param id - new service id
     */
    void setId(final BigInteger id);

    /**
     * @return user id if service connected to him
     */
    @ManyToOne()
    @JoinColumn(name = "userid")
    Customer getCustomer();

    /**
     * set user id
     *
     * @param customer who use service
     */
    void setCustomer(final Customer customer);

    /**
     * @return template of service
     */
    @ManyToOne()
    @JoinColumn(name = "templateid")
    Template getTemplate();

    /**
     * set template id
     *
     * @param template - template of service
     */
    void setTemplate(final Template template);

    /**
     * @return status of service
     */
    @Column(name = "status")
    ServiceStatus getStatus();

    /**
     * set service status
     *
     * @param status - new status of service
     */
    void setStatus(final ServiceStatus status);

    /**
     * @return cost of service from template
     */
    BigDecimal templateGetCost();

    /**
     * @return date of activation
     */
    @Column(name = "activationdate")
    Date getActivationDate();

    /**
     * set activation date
     *
     * @param activationDate - date when service was activate
     */
    void setActivationDate(final Date activationDate);

}
