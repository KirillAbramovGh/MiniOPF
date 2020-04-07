package com.netcracker.students.o3.model.orders;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployeeImpl;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * class order for employee. Create to do action for service
 */
@Entity
@Table(name = "orders")
public interface Order
{
    /**
     * @return id of order
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "last_id")
    @SequenceGenerator(name="last_id",
            sequenceName="last_id")
    @Column(name = "id", updatable = false, nullable = false)
    BigInteger getId();

    /**
     * set order id of order
     */
    void setId(final BigInteger id);

    /**
     * @return template id
     */
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne(targetEntity = TemplateImpl.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "templateid")
    Template getTemplate();

    /**
     * set template id
     * @param  template of template
     */
    void setTemplate(final Template template);

    /**
     * @return service id
     */
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne(targetEntity = ServiceImpl.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceid")
    Service getService();

    /**
     * set service id
     * @param service of service which
     */
    void setService(final Service service);

    /**
     * @return employee id who response for order
     */
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne(targetEntity = EmployeeImpl.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "employeeid")
    Employee getEmployee();

    /**
     * @param employee who response for order
     */
    void setEmployee(final Employee employee);

    /**
     * @return Order status
     */
    @Column(name = "status")
    OrderStatus getStatus();

    /**
     * set order status
     * @param status - order status define stage
     */
    void setStatus(final OrderStatus status);

    /**
     * @return order action define action
     */
    @Column(name = "orderaction")
    OrderAction getAction();

    /**
     * set order action
     */
    void setAction(final OrderAction action);

    /**
     * @return date of creation
     */
    @Column(name = "creationdate")
    Date getCreationDate();

    /**
     * set date of creation
     * @param creationDate date of creation
     */
    void setCreationDate(final Date creationDate);
}
