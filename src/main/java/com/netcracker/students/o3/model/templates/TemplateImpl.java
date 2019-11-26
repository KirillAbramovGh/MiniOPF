package com.netcracker.students.o3.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.students.o3.model.area.Area;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class TemplateImpl implements Template
{
    private BigInteger id;
    private String name;
    private BigDecimal cost;
    private String description;
    private ArrayList<BigInteger> possibleAreasId;


    public TemplateImpl(){}
    public TemplateImpl(final BigInteger id, final String name, final BigDecimal cost, final String description)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "TemplateImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
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

    public BigDecimal getCost()
    {
        return cost;
    }

    public void setCost(final BigDecimal cost)
    {
        this.cost = cost;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }


    public ArrayList<BigInteger> getPossibleAreasId()
    {
        return possibleAreasId;
    }

    public void setPossibleAreasId(final ArrayList<BigInteger> possibleAreasId)
    {
        this.possibleAreasId = possibleAreasId;
    }
}
