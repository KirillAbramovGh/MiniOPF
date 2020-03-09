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

import jsp.builders.CardBuilder;
import jsp.builders.HtmlTableBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerWebOperations {
    private BigInteger customerId;
    private Controller controller;
    private ServiceSorter serviceSorter;
    private TemplateSorter templateSorter;
    private HtmlTableBuilder tableBuilder;
    private CardBuilder cardBuilder;

    private static CustomerWebOperations instance;


    private CustomerWebOperations(){
        controller = ControllerImpl.getInstance();
        serviceSorter = ServiceSorter.getInstance();
        templateSorter = TemplateSorter.getInstance();
        tableBuilder = HtmlTableBuilder.getInstance();
        cardBuilder = CardBuilder.getInstance();
    }

    /**
     * @return search result by req
     */
    public String search(String req) {
        String result = "";
        SearcherTemplates searcher = SearcherTemplates.getInstance();
        SearcherService searcherService = SearcherService.getInstance();
        if(req!=null) {
            List<Service> services =searcherService.searchServicesByAllEntities(
                    controller.getEnteringActiveSuspendedProcessingService(customerId), req);
            List<Template> templates = searcher.searchTemplatesByAllFields(
                    controller.getCustomerAvailableTemplates(customerId), req);

            result += cardBuilder.makeCardsFromServices(services);
            result += cardBuilder.makeCardsFromTemplates(templates);
        }
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
    public List<Service> getConnectedServices() {
        List<Service> result = controller.getEnteringActiveSuspendedProcessingService(customerId);
        return result;
    }

    public Map<BigInteger,Template> getConnectedTemplates(){
        List<Service> connectedServices = getConnectedServices();
        Map<BigInteger,Template> templates = new HashMap<>();

        for(Service service : connectedServices){
            templates.put(service.getId(),controller.getTemplate(service.getTemplateId()));
        }

        return templates;
    }

    /**
     * @return unconnected templates
     */
    public List<Template> getUnconnectedTemplates() {
        List<Template> templates = controller.getCustomerAvailableTemplates(customerId);
        for (Service service : getConnectedServices()) {
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
        controller.disconnectService(serviceId);
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
     *
     */
    public String showConnectedServices(ServiceSortType sortServices) {
        List<Service> services = getConnectedServices();
        if(services.isEmpty()){
            return "There are no connected service";
        }

        serviceSorter.sort(services,sortServices);
        return cardBuilder.makeCardsFromServices(services);
    }

    /**
     * @return templates available to connect
     */
    public String showAllTemplates(TemplateSortType sortType) {
        List<Template> templates = getUnconnectedTemplates();

        if(templates.isEmpty()){
            return "There are no services";
        }
        templateSorter.sort(templates, sortType);

        return cardBuilder.makeCardsFromTemplates(templates);
    }


    public static CustomerWebOperations getInstance(){
        if(instance == null){
            instance = new CustomerWebOperations();
        }

        return instance;
    }

    public String selectArea() {
        StringBuilder resultHtml = new StringBuilder();
        List<Area> availableAreas = getAreas();

        for (Area area : availableAreas) {
            if (getAreaName().equals(area.getName())) {
                resultHtml.append("<option selected value='").append(area.getId()).append("'>").append(area.getName())
                        .append("</option>");
            } else {
                resultHtml.append("<option value='").append(area.getId()).append("'>").append(area.getName()).append("</option>");
            }
        }
        return resultHtml.toString();
    }

    public void suspendService(final BigInteger serviceId)
    {
        controller.suspendService(serviceId);
    }

    public void resumeService(final BigInteger serviceId){
        controller.resumeService(serviceId);
    }
}

