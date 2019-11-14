package com.netcracker.students.o3.templates;

import com.netcracker.students.o3.Area.Area;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class TemplateImpl implements Template
{
    private BigInteger id;
    private String name;
    private BigDecimal cost;
    private String description;
    private ArrayList<Area> possibleAreas;

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

    public ArrayList<Area> getPossibleAreas()
    {
        return possibleAreas;
    }

    public void setPossibleAreas(final ArrayList<Area> possibleAreas)
    {
        this.possibleAreas = possibleAreas;
    }
}
