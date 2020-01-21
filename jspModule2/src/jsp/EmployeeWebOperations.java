package jsp;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.orders.Order;

import java.math.BigInteger;
import java.util.Collection;

public class EmployeeWebOperations {
    BigInteger employeeId;
    Controller controller;
    HtmlTableBuilder tableBuilder;

    public EmployeeWebOperations() {
        controller = ControllerImpl.getInstance();
        tableBuilder = HtmlTableBuilder.getInstance();
    }

    public void start(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public String getFIO() {
        return controller.getEmployee(employeeId).getName();
    }

    public String getLogin() {
        return controller.getEmployee(employeeId).getLogin();
    }

    public String getPassword() {
        return controller.getEmployee(employeeId).getPassword();
    }

    public void changeNameAndPassword(String name, String password) {
        if (!getFIO().equals(name)) {
            changeName(name);
        }
        if (!getPassword().equals(password)) {
            changePassword(password);
        }
    }

    private void changeName(String name) {
        if (isNotNullOrEmpty(name)) {
            controller.getEmployee(employeeId).setName(name);
        }
    }

    private void changePassword(String password) {
        if (isNotNullOrEmpty(password)) {
            controller.getEmployee(employeeId).setPassword(password);
        }
    }

    public String showEmployeeOrders(String search,String field) {
        if(isNotNullOrEmpty(search)){

        }
        return tableBuilder.createOrdersTable(getEmployeeOrders());
    }

    public String showAllOrders() {
        return tableBuilder.createOrdersTable(controller.getOrders());
    }

    public String showAllServices() {
        return tableBuilder.createEmployeeServicesTable(controller.getServices());
    }

    public String showAllTemplates() {
        return tableBuilder.createEmployeeTemplatesTable(controller.getTemplates());
    }

    public String showAllCustomers() {
        return tableBuilder.createCustomersTable(controller.getCustomers());
    }

    public String showAllEmployees() {
        return tableBuilder.createEmployeesTable(controller.getEmployers());
    }

    public String showAllAreas() {
        return tableBuilder.createAreasTable(controller.getAreas());
    }


    public void startOrder(BigInteger orderId){
         controller.startOrder(orderId,employeeId);
    }

    private Collection<Order> getEmployeeOrders() {
        return controller.getOrdersByEmployeeId(employeeId);
    }

    private boolean isNotNullOrEmpty(String value) {
        return value != null && !value.replaceAll(" ", "").isEmpty();
    }

    public void resumeOrder(BigInteger orderId) {
        controller.resumeOrder(orderId);
    }

    public void completeOrder(BigInteger orderId) {
        controller.completeOrder(orderId);
    }

    public void deleteTemplate(BigInteger templateId) {
        controller.deleteTemplate(templateId);
    }

    public void deleteCustomer(BigInteger customerId) {
        controller.deleteCustomer(customerId);
    }

    public void deleteEmployee(BigInteger employeeId) {
        controller.deleteEmployee(employeeId);
    }

    public void deleteArea(BigInteger areaId) {
        controller.deleteArea(areaId);
    }
}
