package com.netcracker.students.o3.model.area;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    BigInteger getId();

    /**
     * set area id
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
     * @param description of Area
     */
    void setDescription(String description);
}
