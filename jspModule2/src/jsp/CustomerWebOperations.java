package jsp;

import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.SearcherService;
import com.netcracker.students.o3.controller.searcher.SearcherTemplates;
import com.netcracker.students.o3.controller.sorters.ServiceSorter;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.controller.sorters.TemplateSorter;
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
    private TemplateSorter templateSorter;
    private HtmlTableBuilder tableBuilder;

    private static CustomerWebOperations instance;


    private CustomerWebOperations(){
        controller = ControllerImpl.getInstance();
        serviceSorter = ServiceSorter.getInstance();
        templateSorter = TemplateSorter.getInstance();
        tableBuilder = HtmlTableBuilder.getInstance();
    }
    /**
     * define services sort type
     */
    public void sortCustomerServiceByType(ServiceSortType type) {
        serviceSorter.sort(getEnteringAndActiveServices(), type);
    }

    /**
     * @return search result by req
     */
    public String search(String req) {
        String result = "";
        SearcherTemplates searcher = SearcherTemplates.getInstance();
        SearcherService searcherService = SearcherService.getInstance();

        List<Service> services = searcherService.searchServicesByAllEntities(
                controller.getCustomerServices(customerId), req);
        List<Template> templates = searcher.searchTemplatesByAllFields(
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

        result += tableBuilder.createCustomerServicesTable(services);

        result += "<hr>";
        return result;
    }

    /**
     * create table from templates
     */
    private String templatesToTable(List<Template> templates) {
        if (templates.size() == 0) {
            return "";
        }
        String result = "<h2>Templates</h2>";

        result += tableBuilder.createCustomerTemplatesTable(templates);

        return result;
    }

    /**
     * start method set customer id
     */
    public void start(BigInteger id) {
        controller = ControllerImpl.getInstance();
        customerId = id;
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

    public String getPassword() {
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
     *
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

    public List<Area> getAreas() {
        return controller.getAreas();
    }

    /**
     * @return table of services
     */
    public String showEnteringActiveServices() {
        List<Service> services = getEnteringAndActiveServices();

        return tableBuilder.createCustomerServicesTable(services);
    }

    /**
     * @return templates available to connect
     */
    public String showAllTemplates(String sortType) {
        List<Template> templates = getUnconnectedTemplates();

        templateSorter.sort(templates, parseTemplateSortType(sortType));

        return tableBuilder.createCustomerTemplatesTable(templates);
    }

    private TemplateSortType parseTemplateSortType(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        return TemplateSortType.valueOf(type);
    }

    public static CustomerWebOperations getInstance(){
        if(instance == null){
            instance = new CustomerWebOperations();
        }

        return instance;
    }
}

