package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.transform.Templates;

public interface Controller
{
    static void startOrder(BigInteger orderId, BigInteger employeeId){};

    static void suspendOrder(BigInteger orderId){};

    //трется employeeId и ордер возвращается в Entering
    static void stopOrder(BigInteger orderId){};

    static void completeOrder(BigInteger orderId){};

    //Value положительный - прибавить n рублей на счет пользователя. Отрицательный - списать средства
    static void changeBalance(BigInteger customerId, BigDecimal value){};

    static void createCustomer(Customer customer){};

    static void createEmployee(/*set of parameters*/){};

    //удаляет только непривязанные к другим объектам сущности
    static void deleteArea(BigInteger areaId){};
    static void deleteOrder(BigInteger orderId){};
    static void deleteService(BigInteger serviceId){};
    static void deleteTemplate(BigInteger templateId){};
    static void deleteCustomer(BigInteger customerId){};
    static void deleteEmployee(BigInteger employeeId){};

     //удаляет сущность вместе со связанными объектами
    static void deepDeleteArea(BigInteger areaId){};
    static void deepDeleteOrder(BigInteger orderId){};
    static void deepDeleteService(BigInteger serviceId){};
    static void deepDeleteTemplate(BigInteger templateId){};
    static void deepDeleteCustomer(BigInteger customerId){};
    static void deepDeleteEmployee(BigInteger employeeId){};

    //создает сущности
    static void createArea(BigInteger areaId){};
    static void createOrder(BigInteger orderId){};
    static void createService(BigInteger serviceId){};
    static void createTemplate(BigInteger templateId){};
    static void createCustomer(BigInteger customerId){};
    static void createEmployee(BigInteger employeeId){};


     static Template[] getTemplatesByAreaId( BigInteger areaId){
       return null;
    };

    static Templates[] getAllTemplates(){
        return null;
    };

    static Order[] getOrdersByCustomerId(BigInteger customerId){
        return null;
    };

    static Order[] getOrdersByEmployeeId(BigInteger employeeId){
        return null;
    }

    // В контроллере должны лежать функции и кастомера, и работника

    //дублирование методов модели с проверкой delete() и set() методов на дурака

}
