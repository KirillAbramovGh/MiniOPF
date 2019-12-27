package com.netcracker.students.o3.model.templates;

import com.netcracker.students.o3.model.area.Area;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface Template
{
     BigInteger getId();

     void setId(final BigInteger id);

     String getName();

     void setName(final String name);

     BigDecimal getCost();

     void setCost(final BigDecimal cost);

     String getDescription();

     void setDescription(final String description);

     List<BigInteger> getPossibleAreasId();

     void setPossibleAreasId(final List<BigInteger> possibleAreasId);
}
