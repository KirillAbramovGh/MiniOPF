package com.netcracker.students.o3.orders;

import com.netcracker.students.o3.HasId;

import java.math.BigInteger;
import java.util.Date;

public interface Order extends HasId
{
    public BigInteger getId();

    public void setId(final BigInteger id);

    public BigInteger getTemplateId();

    public void setTemplateId(final BigInteger templateId);

    public BigInteger getServiceId();

    public void setServiceId(final BigInteger serviceId);

    public BigInteger getEmployeeId();

    public void setEmployeeId(final BigInteger employeeId);

    public OrderStatus getStatus();

    public void setStatus(final OrderStatus status);

    public OrderAction getAction();

    public void setAction(final OrderAction action);

    public Date getCreationDate();

    public void setCreationDate(final Date creationDate);
}
