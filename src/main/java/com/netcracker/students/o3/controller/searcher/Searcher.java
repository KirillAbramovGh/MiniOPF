package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.services.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Searcher<T> {

    public abstract List<T> search(String search, String field, Collection<T> entities);

    protected boolean checkRegExp(String regExp, String input) {
        try {
            return Pattern.matches(regExp, input);
        }catch (Exception e){
            return false;
        }
    }

    protected BigInteger getServiceArea(Service service) {
        Controller controller = ControllerImpl.getInstance();
        return controller.getCustomer(service.getUserId()).getArea();
    }

    protected BigDecimal parseBigDecimal(String value) {
        try {
            double doubleValue = Double.parseDouble(value);
            return BigDecimal.valueOf(doubleValue);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    protected boolean isCostInDiapason(BigDecimal cost,String value,double diapason){
        return Math.abs(cost.doubleValue()-parseBigDecimal(value).doubleValue())<Math.abs(diapason);
    }

    protected boolean checkArea(String search, BigInteger areaId){
        String areaName = ControllerImpl.getInstance().getArea(areaId).getName();
        return areaId.toString().equals(search) ||
                checkRegExp(search,areaId.toString()) ||
                areaName.contains(search) ||
                checkRegExp(search,areaName);
    }
}
