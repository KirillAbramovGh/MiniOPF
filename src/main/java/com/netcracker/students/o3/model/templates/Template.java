package com.netcracker.students.o3.model.templates;

import com.netcracker.students.o3.model.area.Area;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

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

     ArrayList<BigInteger> getPossibleAreasId();

     void setPossibleAreasId(final ArrayList<BigInteger> possibleAreasId);
}
