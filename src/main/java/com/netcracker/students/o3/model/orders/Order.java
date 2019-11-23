package com.netcracker.students.o3.model.orders;

import java.math.BigInteger;
import java.util.Date;

public interface Order
{
    BigInteger getId();

    void setId(final BigInteger id);

    BigInteger getTemplateId();

    void setTemplateId(final BigInteger templateId);

    BigInteger getServiceId();

    void setServiceId(final BigInteger serviceId);

    BigInteger getEmployeeId();

    void setEmployeeId(final BigInteger employeeId);

    OrderStatus getStatus();

    void setStatus(final OrderStatus status);

    OrderAction getAction();

    void setAction(final OrderAction action);

    Date getCreationDate();

    void setCreationDate(final Date creationDate);
}
