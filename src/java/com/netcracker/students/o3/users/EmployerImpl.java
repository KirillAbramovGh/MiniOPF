package com.netcracker.students.o3.users;

import java.math.BigInteger;

public class EmployerImpl implements Employee
{
    private BigInteger id;
    private String name;
    private String login;
    private String password;

    @Override
    public String toString()
    {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public EmployerImpl(){}
    public EmployerImpl(final BigInteger id, final String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public BigInteger getId()
    {
        return id;
    }

    @Override
    public void setId(final BigInteger id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    @Override
    public void setLogin(final String login)
    {
        this.login = login;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(final String password)
    {
        this.password = password;
    }
}
