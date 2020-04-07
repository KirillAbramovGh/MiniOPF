package com.netcracker.students.o3.model.services;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * service which could be connected to customer
 */
@Entity
@Table(name = "services")
public interface Service
{
    /**
     * @return service id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "last_id")
    @SequenceGenerator(name="last_id",
            sequenceName="last_id")
    @Column(name = "id", updatable = false, nullable = false)
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
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne(targetEntity = CustomerImpl.class,fetch = FetchType.EAGER)
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
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne(targetEntity = TemplateImpl.class,fetch = FetchType.EAGER)
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
