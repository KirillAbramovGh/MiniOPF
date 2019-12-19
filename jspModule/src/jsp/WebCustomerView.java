package jsp;

import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class WebCustomerView
{
    private BigInteger customerId;
    private Controller controller;

    public void start(BigInteger customerId)
    {
        this.customerId = customerId;
        controller = ControllerImpl.getInstance();
    }

    public String getServiceName(BigInteger serviceId){
        Service service = controller.getService(serviceId);
        BigInteger templateId = service.getTemplateId();

        Template template = controller.getTemplate(templateId);
       return template.getName();
    }

    public BigDecimal getBalance()
    {
        return controller.getBalance(customerId);
    }

    public String getFIO()
    {
        return controller.getCustomerFio(customerId);
    }

    public String getArea()
    {
        return controller.getAreaName(customerId);
    }

    public List<Service> getActiveServices()
    {
        return controller.getActiveServices(customerId);

    }

    public List<Service> getEnteringServices()
    {

        return controller.getEnteringServices(customerId);

    }

    public List<Service> getAllService()
    {
        return controller.getCustomerAvailableServices(customerId);
    }

    public void changeName(String newName) throws WrongInputException
    {
        controller.setCustomerName(customerId, newName);
    }

    public void changeLogin(String newLogin) throws WrongInputException, LoginOccupiedException
    {
        controller.setUserLogin(customerId, newLogin);
    }

    public void changePassword(String newPassword) throws WrongInputException
    {
        controller.setUserPassword(customerId, newPassword);
    }

    public void changeArea(Area area) throws UnpossibleChangeAreaException
    {
        controller.setCustomerArea(customerId, area.getId());
    }
}
