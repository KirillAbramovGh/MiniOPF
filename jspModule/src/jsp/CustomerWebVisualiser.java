package jsp;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigInteger;
import java.util.List;

import jsp.builders.CardBuilder;
import jsp.ejb.CustomerEJB;

public class CustomerWebVisualiser
{
    private static CustomerWebVisualiser instance;


    private CustomerWebVisualiser()
    {
    }

    /**
     * @return search result by req
     */
    public String search(String req, BigInteger customerId, CustomerEJB customerEJB)
    {
        List<Service> services = customerEJB.searchServices(req, customerId);
        List<Template> templates = customerEJB.searchTemplates(req, customerId);

        String result = CardBuilder.getInstance().makeCardsFromServices(services);
        result += CardBuilder.getInstance().makeCardsFromTemplates(templates);

        return result;
    }

    /**
     * @return table of services
     */
    public String showConnectedServices(BigInteger customerId, CustomerEJB customerEJB)
    {
        List<Service> services = customerEJB.getConnectedServices(customerId);
        if (services.isEmpty())
        {
            return "There are no connected service";
        }
        return CardBuilder.getInstance().makeCardsFromServices(services);
    }

    /**
     * @return templates available to connect
     */
    public String showAllTemplates(BigInteger customerId, CustomerEJB customerEJB)
    {
        List<Template> templates = customerEJB.getUnconnectedTemplates(customerId);

        if (templates.isEmpty())
        {
            return "There are no services";
        }

        return CardBuilder.getInstance().makeCardsFromTemplates(templates);
    }


    public static CustomerWebVisualiser getInstance()
    {
        if (instance == null)
        {
            instance = new CustomerWebVisualiser();
        }

        return instance;
    }

    public String selectArea(BigInteger customerId)
    {
        StringBuilder resultHtml = new StringBuilder();
        List<Area> availableAreas = ControllerImpl.getInstance().getAreas();

        for (Area area : availableAreas)
        {
            if (ControllerImpl.getInstance().getAreaName(customerId).equals(area.getName()))
            {
                resultHtml.append("<option selected value='").append(area.getId()).append("'>").append(area.getName())
                        .append("</option>");
            }
            else
            {
                resultHtml.append("<option value='").append(area.getId()).append("'>").append(area.getName())
                        .append("</option>");
            }
        }
        return resultHtml.toString();
    }

}

