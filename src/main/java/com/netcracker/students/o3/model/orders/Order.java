package com.netcracker.students.o3.model.orders;

import java.math.BigInteger;
import java.util.Date;

/**
 * class order for employee. Create to do action for service
 */
public interface Order
{
    /**
     * @return id of order
     */
    BigInteger getId();

    /**
     * set order id of order
     */
    void setId(final BigInteger id);

    /**
     * @return template id
     */
    BigInteger getTemplateId();

    /**
     * set template id
     * @param  templateId of template
     */
    void setTemplateId(final BigInteger templateId);

    /**
     * @return service id
     */
    BigInteger getServiceId();

    /**
     * set service id
     * @param serviceId of service which
     */
    void setServiceId(final BigInteger serviceId);

    /**
     * @return employee id who response for order
     */
    BigInteger getEmployeeId();

    /**
     * @param employeeId who response for order
     */
    void setEmployeeId(final BigInteger employeeId);

    /**
     * @return Order status
     */
    OrderStatus getStatus();

    /**
     * set order status
     * @param status - order status define stage
     */
    void setStatus(final OrderStatus status);

    /**
     * @return order action define action
     */
    OrderAction getAction();

    /**
     * set order action
     */
    void setAction(final OrderAction action);

    /**
     * @return date of creation
     */
    Date getCreationDate();

    /**
     * set date of creation
     * @param creationDate date of creation
     */
    void setCreationDate(final Date creationDate);
}
