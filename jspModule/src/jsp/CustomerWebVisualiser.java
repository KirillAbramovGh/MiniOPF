package jsp;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigInteger;
import java.util.List;

import jsp.builders.CardBuilder;
import jsp.ejb.CustomerSessionBean;

public class CustomerWebVisualiser
{
    private static CustomerWebVisualiser instance;


    private CustomerWebVisualiser()
    {
    }

    /**
     * @return search result by req
     */
    public String search(String req, BigInteger customerId, CustomerSessionBean customerSessionBean)
    {
        List<Service> services = customerSessionBean.searchServices(req, customerId);
        List<Template> templates = customerSessionBean.searchTemplates(req, customerId);

        String result = CardBuilder.getInstance().makeCardsFromServices(services);
        result += CardBuilder.getInstance().makeCardsFromTemplates(templates);

        return result;
    }

    /**
     * @return table of services
     */
    public String showConnectedServices(BigInteger customerId, CustomerSessionBean customerSessionBean)
    {
        List<Service> services = customerSessionBean.getConnectedServices(customerId);
        if (services.isEmpty())
        {
            return "There are no connected service";
        }
        return CardBuilder.getInstance().makeCardsFromServices(services);
    }

    /**
     * @return templates available to connect
     */
    public String showAllTemplates(BigInteger customerId, CustomerSessionBean customerSessionBean)
    {
        List<Template> templates = customerSessionBean.getUnconnectedTemplates(customerId);

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

