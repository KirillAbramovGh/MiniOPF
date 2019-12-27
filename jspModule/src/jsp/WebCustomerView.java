package jsp;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class WebCustomerView
{
    private BigInteger customerId;
    private Controller controller;

    public BigInteger getCustomerId()
    {
        return customerId;
    }

    public void start(String login,String password) throws IncorrectCredentialsException
    {
        controller = ControllerImpl.getInstance();
        this.customerId = controller.getUserIdByCredentials(login,password);
    }

    public void start(BigInteger id){
        controller = ControllerImpl.getInstance();
        customerId = id;
    }

    public String getServiceName(BigInteger serviceId)
    {
        Service service = controller.getService(serviceId);
        System.out.println(service);
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

    public String getAreaName()
    {
        return controller.getAreaName(customerId);
    }

    public List<Service> getActiveServices()
    {
        return controller.getActiveServices(customerId);

    }

    public List<Service> getEnteringAndActiveServices()
    {
        return controller.getEnteringAndActiveServices(customerId);
    }

    public List<Template> getAllTemplates()
    {
        List<Template> templates = controller.getCustomerAvailableTemplates(customerId);
        for(Service service : getEnteringAndActiveServices()){
            templates.remove(controller.getTemplate(service.getTemplateId()));
        }
        return templates;
    }

    public void changeName(String newName) throws WrongInputException
    {
        if(newName!=null && !newName.isEmpty())
        controller.setCustomerName(customerId, newName);
    }

    public void changeLogin(String newLogin) throws WrongInputException, LoginOccupiedException
    {
        if(newLogin!=null && !newLogin.isEmpty())
        controller.setUserLogin(customerId, newLogin);
    }

    public void changePassword(String newPassword) throws WrongInputException
    {
        if(newPassword!=null && !newPassword.isEmpty())
        controller.setUserPassword(customerId, newPassword);
    }

    public void changeArea(Area area) throws UnpossibleChangeAreaException
    {
        System.out.println(area);
        if(area!=null)
        controller.setCustomerArea(customerId, area.getId());
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


    public void disconnectService(int index)
    {
        BigInteger serviceId = getEnteringAndActiveServices().get(index).getId();
        controller.disconnectService(customerId, serviceId);
    }

    public void suspendOrResumeService(int index){
        BigInteger serviceId = getEnteringAndActiveServices().get(index).getId();
        controller.suspendOrResumeService(customerId,serviceId);
    }

    public void connectService(int index){
        BigInteger templateId = getAllTemplates().get(index).getId();
        controller.connectService(customerId,templateId);
    }

    public String getLogin(){
        return controller.getCustomer(customerId).getLogin();
    }

    public List<Area> getAvailableAreas(){
        return controller.getAvailableAreas(customerId);
    }

    public String showEnteringServices()
    {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();

        tableBuilder.addColumns("id","Name","Cost","Status","disconnect","suspend/resume");
        int i = 1;
        addServices(tableBuilder);
        String result = tableBuilder.buitlt();

        return result;
    }

    private void addServices(HtmlTableBuilder tableBuilder){
        int i = 1;
        for (Service service : getEnteringAndActiveServices())
        {
            String id = i+"";
            String name = getServiceName(service.getId());
            String cost = service.getCost()+"";
            String status = service.getStatus()+"";

            tableBuilder.addRow().addCell(id).addCell(name).addCell(cost).addCell(status);

            if(service.getStatus()== ServiceStatus.Entering){
                tableBuilder.addButton("cancel","disconnect"+i);
                tableBuilder.addCell("");
            }else if(service.getStatus()==ServiceStatus.Active){
                tableBuilder.addButton("disconnect","disconnect"+i);
                tableBuilder.addButton("suspend","suspend"+i);
            }else if(service.getStatus()==ServiceStatus.Suspended){
                tableBuilder.addButton("disconnect","disconnect"+i);
                tableBuilder.addButton("resume","suspend"+i);
            }
            tableBuilder.builtRow();
            i++;
        }

    }

    public String showAllTemplates()
    {
        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();
        tableBuilder.addColumns("id","name","cost","description","connect");

        int i = 1;
        for (Template t : getAllTemplates())
        {
            tableBuilder.addRow().addCell(i+"").addCell(t.getName()).addCell(t.getCost()+"")
                    .addCell(t.getDescription())
                    .addButton("connect","connect"+i).builtRow();
            i++;
        }
        String result = tableBuilder.buitlt();
        return result;
    }

}

