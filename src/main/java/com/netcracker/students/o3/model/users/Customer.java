package com.netcracker.students.o3.model.users;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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
     Set<Service> getConnectedServices();

     /***
      * set customer connected services
      * @param connectedServices
      */
     void setConnectedServicesIds(final Set<Service> connectedServices);

     /**
      * @return customer area id
      */
     Area getArea();

     /**
      * set customer area id
      * @param area
      */
     void setArea(final Area area);

     /**
      * add service to customer
      * @param serviceId
      */
     void addConnectedServiceId(BigInteger serviceId);
}
