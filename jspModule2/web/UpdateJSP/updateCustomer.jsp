<%@ page import="com.netcracker.students.o3.controller.Controller" %>
<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.services.ServiceStatus" %>
<%@ page import="com.netcracker.students.o3.model.users.Customer" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.Set" %>
<%@ page import="jsp.CustomerWebOperations" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="com.netcracker.students.o3.model.templates.Template" %>
<%@ page import="jsp.EmployeeWebOperations" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.netcracker.students.o3.Exceptions.WrongInputException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>UpdateCustomer</title>
    <%!
        BigInteger customerId;
        Customer customer;
        Controller controller = ControllerImpl.getInstance();
    %>
    <%
        customerId = (BigInteger) request.getSession().getAttribute("updateCustomerId");
        customer = controller.getCustomer(customerId);
    %>
</head>
<body>
<form action="${pageContext.request.contextPath}/UpdateJSP/updateCustomer.jsp" method="post">
    <div style="size: 200px">
        <div class="name">
            Name: <input type="text" name="fio" value="<%=customer.getName()%>">
        </div>

        <div class="">
            Login: <input type="text" name="login" value="<%=customer.getLogin()%>">
        </div>

        <div class="password">
            Password: <input type="text" name="password"
                             value=<%=customer.getPassword()%>>
        </div>
        <div class="">
            Balance: <input type="text" name="balance" value="<%=customer.getMoneyBalance()%>">
        </div>
        <div class="selectArea">
            Area: <input type="text" name="area"
                         value=<%=customer.getAreaId()%>>
        </div>
        <div>
            ConnectedTemplatesId: <input type="text" name="connectedTemplatesId" value="<%=getConnectedTemplatesId()%>">
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%!
    private String getConnectedTemplatesId()
    {
        String res = "";
        for (BigInteger id : customer.getConnectedServicesIds())
        {
            res += controller.getService(id).getTemplateId() + ",";
        }
        return res;
    }
%>
<%
    if (request.getParameter("save") != null)
    {
        try
        {
            String name = request.getParameter("fio");
            String password = request.getParameter("password");
            String login = request.getParameter("login");
            String area = request.getParameter("area");
            String servicesValue = request.getParameter("connectedTemplatesId");
            String[] templates = servicesValue.split(",");
            String moneyBalance = request.getParameter("balance");

            customer.setName(name);
            customer.setPassword(password);
            customer.setLogin(login);
            customer.setAreaId(BigInteger.valueOf(Long.parseLong(area)));
            customer.setMoneyBalance(BigDecimal.valueOf(Double.parseDouble(moneyBalance)));

            for (String s : templates)
            {
                if (s != null && !s.isEmpty())
                {
                    BigInteger templateId = BigInteger.valueOf(Long.parseLong(s));
                    boolean connected = false;
                    for (BigInteger serviceId : customer.getConnectedServicesIds())
                    {
                        Service service = controller.getService(serviceId);
                        Template template = controller.getTemplate(service.getTemplateId());
                        if(!template.getPossibleAreasId().contains(customer.getAreaId())){
                            CustomerWebOperations.getInstance().disconnectService(serviceId);
                            break;
                        }
                        if (service.getTemplateId().equals(templateId))
                        {
                            connected = true;
                            break;
                        }
                    }
                    if (!connected && controller.getTemplate(templateId).getPossibleAreasId().contains(customer.getAreaId()))
                    {
                        controller.connectService(customerId,templateId);
                    }else{
                        throw new WrongInputException("Service уже подключен или Area пользователя не соответствует " +
                                "данному template");
                    }
                }
            }
            ControllerImpl.getInstance().setCustomer(customer);

%>
<jsp:forward page="/webEmployeeView.jsp"/>
<%
        }
        catch (WrongInputException e1){
            response.getWriter().println(e1.getMessage());
        }
        catch (Exception e)
        {
            response.getWriter().println("Input error");
        }
    }
%>
</body>
</html>
