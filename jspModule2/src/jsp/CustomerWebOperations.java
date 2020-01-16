package jsp;

import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.Searcher;
import com.netcracker.students.o3.controller.sorters.ServiceSorter;
import com.netcracker.students.o3.controller.sorters.SortType;
import com.netcracker.students.o3.controller.sorters.TemplatesSorter;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class CustomerWebOperations {
    private BigInteger customerId;
    private Controller controller;
    private ServiceSorter serviceSorter;
    private TemplatesSorter templatesSorter;


    /**
     * define services sort type
     */
    public void defineServiceSortType(SortType type) {
        serviceSorter.defineSortType(type);
    }

    /**
     * define templates sort type
     */
    public void defineTemplateSortType(SortType type) {
        templatesSorter.defineSortType(type);
    }

    /**
     * @return search result by req
     */
    public String search(String req) {
        String result = "";
        Searcher searcher = new Searcher();

        List<Service> services = searcher.searchServices(
                controller.getCustomerServices(customerId), req);
        List<Template> templates = searcher.searchTemplates(
                controller.getCustomerAvailableTemplates(customerId), req);

        result += servicesToTable(services);
        result += templatesToTable(templates);

        return result;
    }


    /**
     * create table from services
     */
    private String servicesToTable(List<Service> services) {
        if (services.size() == 0) {
            return "";
        }

        String result = "<h2>Services</h2>";

        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        serviceSorter.sort(services);
        tableBuilder.createServicesTable(services);

        result+=tableBuilder.build();
        return result;
    }

    /**
     * create table from templates
     */
    private String templatesToTable(List<Template> templates) {
        if (templates.size() > 0) {
            HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

            templatesSorter.sort(templates);
            tableBuilder.createTemplatesTable(templates);

            return tableBuilder.build();
        }

        return "";
    }

    /**
     * start method set customer id
     */
    public void start(BigInteger id) {
        controller = ControllerImpl.getInstance();
        customerId = id;
        serviceSorter = new ServiceSorter();
        templatesSorter = new TemplatesSorter();
    }

    /**
     * @return customer balance
     */
    public BigDecimal getBalance() {
        return controller.getBalance(customerId);
    }

    /**
     * @return customer name
     */
    public String getFIO() {
        return controller.getCustomerFio(customerId);
    }

    /**
     * @return area name
     */
    public String getAreaName() {
        return controller.getAreaName(customerId);
    }

    public String getPassword(){
        return controller.getCustomer(customerId).getPassword();
    }
    /**
     * @return entering and active customer services
     */
    public List<Service> getEnteringAndActiveServices() {
        return controller.getEnteringAndActiveServices(customerId);
    }

    /**
     * @return unconnected templates
     */
    public List<Template> getUnconnectedTemplates() {
        List<Template> templates = controller.getCustomerAvailableTemplates(customerId);
        for (Service service : getEnteringAndActiveServices()) {
            templates.remove(controller.getTemplate(service.getTemplateId()));
        }
        return templates;
    }

    /**
     * change customer name
     * @throws WrongInputException when name is empty
     */
    public void changeName(String newName) throws WrongInputException {
        if (newName != null && !newName.isEmpty()) {
            controller.setCustomerName(customerId, newName);
        }
    }

    /**
     * change customer login
     */

    @Deprecated
    public void changeLogin(String newLogin) throws WrongInputException, LoginOccupiedException {
        if (newLogin != null && !newLogin.isEmpty()) {
            controller.setUserLogin(customerId, newLogin);
        }
    }

    /**
     * change customer password
     */
    public void changePassword(String newPassword) throws WrongInputException {
        if (newPassword != null && !newPassword.isEmpty()) {
            controller.setUserPassword(customerId, newPassword);
        }
    }

    /**
     * change customer area
     */
    public void changeArea(Area area) throws UnpossibleChangeAreaException {
        if (area != null) {
            controller.setCustomerArea(customerId, area.getId());
        }
    }

    /**
     * put money to customer balance
     */
    public void addBalance(String value) {
        if (value != null && !value.isEmpty()) {
            controller.putOnBalance(customerId, parseBigDec(value));
        }
    }

    /**
     * parse String to BigDecimal
     */
    public BigDecimal parseBigDec(String value) {
        double d = Double.parseDouble(value);
        return BigDecimal.valueOf(d);
    }


    /**
     * disconnect service
     */
    public void disconnectService(BigInteger serviceId) {
        controller.disconnectService(customerId, serviceId);
    }

    /**
     * suspend or resume service
     */
    public void suspendOrResumeService(BigInteger serviceId) {
        controller.suspendOrResumeService(customerId, serviceId);
    }

    /**
     * connect service
     */
    public void connectService(BigInteger templateId) {
        controller.connectService(customerId, templateId);
    }

    /**
     * @return customer login
     */
    public String getLogin() {
        return controller.getCustomer(customerId).getLogin();
    }

    /**
     * return available areas
     */
    public List<Area> getAvailableAreas() {
        return controller.getAvailableAreas(customerId);
    }

    /**
     * @return table of services
     */
    public String showEnteringActiveServices() {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        List<Service> services = getEnteringAndActiveServices();
        serviceSorter.sort(services);

        tableBuilder.createServicesTable(services);

        return tableBuilder.build();
    }

    /**
     * @return templates available to connect
     */
    public String showAllTemplates() {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        List<Template> templates = getUnconnectedTemplates();
        templatesSorter.sort(templates);

        tableBuilder.createTemplatesTable(templates);

        return tableBuilder.build();
    }

}

