package com.netcracker.students.o3.model.area;

import java.math.BigInteger;

/**
 * district of customer
 */
public interface Area
{
    /**
     * @return area id
     */
    BigInteger getId();

    /**
     * set area id
     * @param id
     */
    void setId(BigInteger id);

    /**
     * @return area name
     */
    String getName();

    /**
     * set area name
     * @param name
     */
    void setName(String name);

    /**
     * @return description of area
     */
    String getDescription();

    /**
     * set area description
     * @param description
     */
    void setDescription(String description);
}
