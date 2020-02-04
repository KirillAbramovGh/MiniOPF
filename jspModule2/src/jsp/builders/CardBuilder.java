package jsp.builders;

import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.util.Collection;

public class CardBuilder
{

    private static CardBuilder instance;

    private CardBuilder()
    {
    }

    public static CardBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new CardBuilder();
        }

        return instance;
    }


    private String makeCardFromService(Service service)
    {
        String result = "";

        result += "<div class='card'>";
        result += "<h3>" + service.getName() + "</h3>";
        result += "<p>" + service.getDescription() + "</p>";
        result += "<p>" + service.getCost() + "$" + "</p>";

        switch (service.getStatus())
        {
            case Suspended:
                result += createButton("resume", "resume", service.getId().toString());
                result += createButton("disconnect", "disconnect", service.getId().toString());
                break;
            case Entering:
                result += createButton("cancel", "disconnect", service.getId().toString());
                break;
            case Active:
                result += createButton("disconnect", "disconnect", service.getId().toString());
                result += createButton("suspend", "suspend", service.getId().toString());
                break;
        }
        result += "</div>";
        return result;
    }

    private String makeCardFromTemplate(Template template)
    {
        String result = "";

        result += "<div class='card'>";
        result += "<h3>" + template.getName() + "</h3>";
        result += "<p>" + template.getDescription() + "</p>";
        result += "<p>" + template.getCost() + "$" + "</p>";
        result += createButton("connect", "connect", template.getId().toString());
        result += "</div>";

        return result;
    }


    private String createButton(String value, String name)
    {
        String buttonStart = "<input ";
        String buttonEnd = " />";
        return buttonStart + "type='" + "submit" + "' value='" + value + "' name='" + name + "' class='button' " +
                buttonEnd;
    }

    private String createButton(String buttonName, String command, String id)
    {
        return createButton(buttonName, command + " " + id);
    }

    public String makeCardsFromServices(Collection<Service> services)
    {
        StringBuilder result = new StringBuilder();

        for (Service service : services)
        {
            result.append(makeCardFromService(service));
        }

        return result.toString();
    }

    public String makeCardsFromTemplates(Collection<Template> templates)
    {
        StringBuilder result = new StringBuilder();

        for (Template template : templates)
        {
            result.append(makeCardFromTemplate(template));
        }

        return result.toString();
    }

}
