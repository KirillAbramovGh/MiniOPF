package com.netcracker.students.o3.model.users;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class CustomerImpl implements Customer
{
    private BigInteger id;
    private String name;
    private String login;
    private String password;
    private BigDecimal moneyBalance;
    private ArrayList<BigInteger> connectedServicesIds;
    private BigInteger areaId;

    public CustomerImpl()
    {
        moneyBalance = BigDecimal.ZERO;
    }

    public CustomerImpl(final BigInteger id, final String name, final String login, final String password)
    {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        connectedServicesIds = new ArrayList<>();
        moneyBalance = BigDecimal.ZERO;
    }

    @Override
    public String toString()
    {
        return name+" "+"Баланс: " + moneyBalance;
    }

    public BigDecimal getMoneyBalance()
    {
        return moneyBalance;
    }

    public void setMoneyBalance(final BigDecimal moneyBalance)
    {
        this.moneyBalance = moneyBalance;
    }

    public ArrayList<BigInteger> getConnectedServicesIds()
    {
        return connectedServicesIds;
    }

    public void setConnectedServicesIds(final ArrayList<BigInteger> connectedServicesIds)
    {
        this.connectedServicesIds = connectedServicesIds;
    }

    public BigInteger getAreaId()
    {
        return areaId;
    }

    public void setAreaId(final BigInteger areaId)
    {
        this.areaId = areaId;
    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(final BigInteger id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(final String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

}
