package com.netcracker.students.o3.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.students.o3.model.HasId;
import com.netcracker.students.o3.model.area.Area;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class TemplateImpl extends HasId implements Template
{
    private BigInteger id;
    private String name;
    private BigDecimal cost;
    private String description;
    private ArrayList<Area> possibleAreas;


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

    @Override
    @JsonIgnore
    public String getType()
    {
        return "template";
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

    public ArrayList<Area> getPossibleAreas()
    {
        return possibleAreas;
    }

    public void setPossibleAreas(final ArrayList<Area> possibleAreas)
    {
        this.possibleAreas = possibleAreas;
    }
}
