package com.netcracker.students.o3.model.users;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface Customer extends User
{
     /**
      * @return customer money balance
      * */
     BigDecimal getMoneyBalance();

     /**
      * set customer money balance
      * */
     void setMoneyBalance(final BigDecimal moneyBalance);

     /**
      * @return set of connected customer services ids
      */
     Set<BigInteger> getConnectedServicesIds();

     /***
      * set customer connected services
      * @param connectedServicesIds
      */
     void setConnectedServicesIds(final Set<BigInteger> connectedServicesIds);

     /**
      * @return customer area id
      */
     BigInteger getAreaId();

     /**
      * set customer area id
      * @param areaId
      */
     void setAreaId(final BigInteger areaId);

     /**
      * add service to customer
      * @param serviceId
      */
     void addConnectedServiceId(BigInteger serviceId);
}
