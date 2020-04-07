package com.netcracker.students.o3.model.area;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * district of customer
 */
@Entity
@Table(name = "areas")
public interface Area
{
    /**
     * @return area id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "last_id")
    @SequenceGenerator(name="last_id",
            sequenceName="last_id")
    @Column(name = "id", updatable = false, nullable = false)
    BigInteger getId();

    /**
     * set area id
     *
     * @param id of Area
     */
    void setId(BigInteger id);

    /**
     * @return area name
     */
    @Column(name = "area_name")
    String getName();

    /**
     * set area name
     *
     * @param name of Area
     */
    void setName(String name);

    /**
     * @return description of area
     */
    @Column(name = "description")
    String getDescription();

    /**
     * set area description
     *
     * @param description of Area
     */
    void setDescription(String description);


    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToMany(cascade = CascadeType.REFRESH,targetEntity = TemplateImpl.class,fetch = FetchType.EAGER)
    @JoinTable(
            name="template_area_link",
            joinColumns=@JoinColumn(name="areaid"),
            inverseJoinColumns=@JoinColumn(name="templateid")
    )
    List<Template> getTemplates();

    void setTemplates(List<Template> templates);
}
