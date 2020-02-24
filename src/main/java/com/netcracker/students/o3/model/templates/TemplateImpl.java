package com.netcracker.students.o3.model.templates;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class TemplateImpl implements Template
{
    private BigInteger id;
    private String name;
    private BigDecimal cost;
    private String description;
    private List<BigInteger> possibleAreasId;


    public TemplateImpl()
    {
    }

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


    public List<BigInteger> getPossibleAreasId()
    {
        return possibleAreasId;
    }

    public void setPossibleAreasId(final List<BigInteger> possibleAreasId)
    {
        this.possibleAreasId = possibleAreasId;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final TemplateImpl template = (TemplateImpl) o;
        return Objects.equals(id, template.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
