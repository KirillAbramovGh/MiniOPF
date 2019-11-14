package com.netcracker.students.o3.Area;

import com.netcracker.students.o3.HasId;

import java.math.BigInteger;

public interface Area extends HasId
{
    BigInteger getId();
    void setId(BigInteger id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
}
