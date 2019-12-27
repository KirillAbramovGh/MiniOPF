package com.netcracker.students.o3.model.users;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface Customer extends User
{
     BigInteger getId();

     void setId(final BigInteger id);

     String getName();

     void setName(final String name);

     String getLogin();

     void setLogin(final String login);

     String getPassword();

     void setPassword(final String password);

     BigDecimal getMoneyBalance();

     void setMoneyBalance(final BigDecimal moneyBalance);

     List<BigInteger> getConnectedServicesIds();

     void setConnectedServicesIds(final List<BigInteger> connectedServicesIds);

     BigInteger getAreaId();

     void setAreaId(final BigInteger areaId);

     void addConnectedServiceId(BigInteger serviceId);

     void disconnectService(BigInteger serviceId);
}
