package com.netcracker.students.o3.model.users;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class CustomerImpl implements Customer {
    private BigInteger id;
    private String name;
    private String login;
    private String password;
    private BigDecimal moneyBalance;
    private Set<BigInteger> connectedServicesIds;
    private BigInteger areaId;

    public CustomerImpl() {
        moneyBalance = BigDecimal.ZERO;
        connectedServicesIds = new HashSet<>();
    }

    public CustomerImpl(final BigInteger id, final String name, final String login, final String password,
                        final BigInteger areaId) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.areaId = areaId;
        connectedServicesIds = new HashSet<>();
        moneyBalance = BigDecimal.ZERO;
    }

    @Override
    public String toString()
    {
        return "CustomerImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", moneyBalance=" + moneyBalance +
                ", connectedServicesIds=" + connectedServicesIds +
                ", areaId=" + areaId +
                '}';
    }

    public BigDecimal getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(final BigDecimal moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public Set<BigInteger> getConnectedServicesIds() {
        return connectedServicesIds;
    }

    public void setConnectedServicesIds(final Set<BigInteger> connectedServicesIds) {
        this.connectedServicesIds = connectedServicesIds;
    }

    public BigInteger getAreaId() {
        return areaId;
    }

    public void setAreaId(final BigInteger areaId) {
        this.areaId = areaId;
    }

    @Override
    public void addConnectedServiceId(final BigInteger serviceId) {
            connectedServicesIds.add(serviceId);
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(final BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final CustomerImpl customer = (CustomerImpl) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
