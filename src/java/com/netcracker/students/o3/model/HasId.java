package com.netcracker.students.o3.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.math.BigInteger;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= CustomerImpl.class, name="customer"),
        @JsonSubTypes.Type(value= EmployerImpl.class, name="employee"),
        @JsonSubTypes.Type(value= OrderImpl.class, name="order"),
        @JsonSubTypes.Type(value= AreaImpl.class, name="area"),
        @JsonSubTypes.Type(value= ServiceImpl.class, name="service"),
        @JsonSubTypes.Type(value= TemplateImpl.class, name="template")
})
public abstract class HasId
{
    public abstract BigInteger getId();
    public abstract String getType();
}
