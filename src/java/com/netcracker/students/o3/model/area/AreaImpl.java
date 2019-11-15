package com.netcracker.students.o3.model.area;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.students.o3.model.HasId;

import java.math.BigInteger;

public class AreaImpl extends HasId implements Area
{
    private BigInteger id;
    private String name;
    private String description;


    @Override
    @JsonIgnore
    public String getType()
    {
        return "employee";
    }

    @Override
    public String toString()
    {
        return "AreaImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(final BigInteger id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }
}
