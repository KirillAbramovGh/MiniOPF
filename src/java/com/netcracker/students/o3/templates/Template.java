package com.netcracker.students.o3.templates;

import com.netcracker.students.o3.Area.Area;
import com.netcracker.students.o3.HasId;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public interface Template extends HasId
{
    public BigInteger getId();

    public void setId(final BigInteger id);

    public String getName();

    public void setName(final String name);

    public BigDecimal getCost();
    public void setCost(final BigDecimal cost);

    public String getDescription();

    public void setDescription(final String description);

    public ArrayList<Area> getPossibleAreas();

    public void setPossibleAreas(final ArrayList<Area> possibleAreas);
}
