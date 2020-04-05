package com.netcracker.students.o3.model.templates;

import com.netcracker.students.o3.model.area.Area;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Table(name = "templates")
public interface Template
{
    /**
     * @return Template id
     */
    @Id
    BigInteger getId();

    /**
     * set template id
     */
    void setId(final BigInteger id);

    /**
     * @return template name
     */
    @Column(name = "template_name")
    String getName();

    /**
     * set template name
     */
    void setName(final String name);

    /**
     * @return template cost per month
     */
    @Column(name = "cost")
    BigDecimal getCost();

    /**
     * set template cost per month
     */
    void setCost(final BigDecimal cost);

    /**
     * @return template description
     */
    @Column(name = "description")
    String getDescription();

    /**
     * set template description
     */
    void setDescription(final String description);

    /**
     * @return possible area id by connected services
     */
    @ManyToMany
    @JoinTable(
            name="template_area_link",
            joinColumns=@JoinColumn(name="templateid"),
            inverseJoinColumns=@JoinColumn(name="areaid")
    )
    List<Area> getPossibleAreas();

     /**
      * set possible area id
      * @param possibleAreas
      */
    void setPossibleAreas(final List<Area> possibleAreas);
}
