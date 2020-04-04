package com.netcracker.students.o3.model.templates;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "templates")
@XmlType(name = "template")
@XmlRootElement
public class TemplateImpl implements Template
{
    @Id
    private BigInteger id;

    @Column(name = "template_name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "description")
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

    public TemplateImpl(final String name, final BigDecimal cost, final String description)
    {
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
