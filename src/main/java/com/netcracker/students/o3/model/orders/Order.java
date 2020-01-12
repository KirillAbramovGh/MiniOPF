package com.netcracker.students.o3.model.orders;

import java.math.BigInteger;
import java.util.Date;

public interface Order
{
    /**
     * @return id of order
     */
    BigInteger getId();

    /**
     * set order id
     */
    void setId(final BigInteger id);

    /**
     * @return template id
     */
    BigInteger getTemplateId();

    /**
     * set template id
     */
    void setTemplateId(final BigInteger templateId);

    /**
     * @return service id
     */
    BigInteger getServiceId();

    /**
     * set service id
     */
    void setServiceId(final BigInteger serviceId);

    /**
     * @return employee id who
     */
    BigInteger getEmployeeId();

    /**
     * set empl
     */
    void setEmployeeId(final BigInteger employeeId);

    /**
     * @return Order status
     */
    OrderStatus getStatus();

    /**
     * set order status
     */
    void setStatus(final OrderStatus status);

    /**
     * @return order action
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
     */
    void setCreationDate(final Date creationDate);
}
