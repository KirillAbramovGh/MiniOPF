package com.netcracker.students.o3.model.templates;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface Template
{
    /**
     * @return Template id
     */
    BigInteger getId();

    /**
     * set template id
     */
    void setId(final BigInteger id);

    /**
     * @return template name
     */
    String getName();

    /**
     * set template name
     */
    void setName(final String name);

    /**
     * @return template cost per month
     */
    BigDecimal getCost();

    /**
     * set template cost per month
     */
    void setCost(final BigDecimal cost);

    /**
     * @return template description
     */
    String getDescription();

    /**
     * set template description
     */
    void setDescription(final String description);

    /**
     * @return possible area id by connected services
     */
    List<BigInteger> getPossibleAreasId();

     /**
      * set possible area id
      * @param possibleAreasId
      */
    void setPossibleAreasId(final List<BigInteger> possibleAreasId);
}
