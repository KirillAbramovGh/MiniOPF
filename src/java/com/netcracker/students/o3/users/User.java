package com.netcracker.students.o3.users;

import com.netcracker.students.o3.HasId;

import java.math.BigInteger;

public interface User extends HasId
{
    public BigInteger getId();

    public void setId(final BigInteger id);

    public String getName();

    public void setName(final String name);

    public String getLogin();

    public void setLogin(final String login);

    public String getPassword();

    public void setPassword(final String password);
}
