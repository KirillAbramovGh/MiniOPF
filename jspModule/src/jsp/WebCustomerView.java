package jsp;

import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.Searcher;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class WebCustomerView
{
    private BigInteger customerId;
    private Controller controller;


    public String getSearchResult(String req)
    {
        Searcher searcher = new Searcher();

        Collection<Service> services = searcher.searchServices(
                controller.getCustomerServices(customerId), req);
        Collection<Template> templates = searcher.searchTemplates(
                controller.getCustomerAvailableTemplates(customerId), req);

        String result = "";

        if (services.size() > 0)
        {
            result += servicesToTable(services);
        }
        if (templates.size() > 0)
        {
            result += templatesToTable(templates);
        }

        return result;
    }

    private String servicesToTable(Collection<Service> services)
    {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        tableBuilder.createServicesTable(services);

        return tableBuilder.built();
    }

    private String templatesToTable(Collection<Template> templates)
    {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        tableBuilder.createTemplatesTable(templates);

        return tableBuilder.built();
    }


    public void start(BigInteger id)
    {
        controller = ControllerImpl.getInstance();
        customerId = id;
    }

    public BigDecimal getBalance()
    {
        return controller.getBalance(customerId);
    }

    public String getFIO()
    {
        return controller.getCustomerFio(customerId);
    }

    public String getAreaName()
    {
        return controller.getAreaName(customerId);
    }


    public List<Service> getEnteringAndActiveServices()
    {
        return controller.getEnteringAndActiveServices(customerId);
    }

    public List<Template> getAllTemplates()
    {
        List<Template> templates = controller.getCustomerAvailableTemplates(customerId);
        for (Service service : getEnteringAndActiveServices())
        {
            templates.remove(controller.getTemplate(service.getTemplateId()));
        }
        return templates;
    }

    public void changeName(String newName) throws WrongInputException
    {
        if (newName != null && !newName.isEmpty())
        {
            controller.setCustomerName(customerId, newName);
        }
    }

    public void changeLogin(String newLogin) throws WrongInputException, LoginOccupiedException
    {
        if (newLogin != null && !newLogin.isEmpty())
        {
            controller.setUserLogin(customerId, newLogin);
        }
    }

    public void changePassword(String newPassword) throws WrongInputException
    {
        if (newPassword != null && !newPassword.isEmpty())
        {
            controller.setUserPassword(customerId, newPassword);
        }
    }

    public void changeArea(Area area) throws UnpossibleChangeAreaException
    {
        System.out.println(area);
        if (area != null)
        {
            controller.setCustomerArea(customerId, area.getId());
        }
    }

    public void addBalance(String value)
    {
        if (value != null && !value.isEmpty())
        {
            controller.putOnBalance(customerId, parseBigDec(value));
        }
    }

    public BigDecimal parseBigDec(String value)
    {
        double d = Double.parseDouble(value);
        return BigDecimal.valueOf(d);
    }


    public void disconnectService(BigInteger serviceId)
    {
        controller.disconnectService(customerId, serviceId);
    }

    public void suspendOrResumeService(BigInteger serviceId)
    {
        controller.suspendOrResumeService(customerId, serviceId);
    }

    public void connectService(BigInteger templateId)
    {
        controller.connectService(customerId, templateId);
    }

    public String getLogin()
    {
        return controller.getCustomer(customerId).getLogin();
    }

    public List<Area> getAvailableAreas()
    {
        return controller.getAvailableAreas(customerId);
    }

    public String showEnteringActiveServices()
    {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        tableBuilder.createServicesTable(getEnteringAndActiveServices());

        return tableBuilder.built();
    }


    public String showAllTemplates()
    {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        tableBuilder.createTemplatesTable(getAllTemplates());

        return tableBuilder.built();
    }

}

