<%@ page import="com.netcracker.students.o3.Exceptions.WrongInputException" %>
<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="com.netcracker.students.o3.model.templates.Template" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="javax.inject.Inject" %>
<%@ page import="jsp.ejb.CustomerEJB" %>
<%@ page import="javax.ejb.EJB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>UpdateCustomer</title>
    <%!
        @Inject
        CustomerEJB customerEJB;
    %>
</head>
<body>
<form action="${pageContext.request.contextPath}/UpdateJSP/updateCustomer.jsp" method="post">
    <div style="size: 200px">
        <div class="name">
            Name: <input type="text" name="fio"
                         value="<%=customerEJB.getFIO((BigInteger) request.getSession().getAttribute("id"))%>">
        </div>

        <div class="">
            Login: <input type="text" name="login"
                          value="<%=customerEJB.getLogin((BigInteger) request.getSession().getAttribute("id"))%>">
        </div>

        <div class="password">
            Password: <input type="text" name="password"
                             value=<%=customerEJB.getPassword((BigInteger) request.getSession().getAttribute("id"))%>>
        </div>
        <div class="">
            Balance: <input type="text" name="balance"
                            value="<%=customerEJB.getBalance((BigInteger) request.getSession().getAttribute("id"))%>">
        </div>
        <div class="selectArea">
            Area: <input type="text" name="area"
                         value=<%=customerEJB.getAreaId((BigInteger) request.getSession().getAttribute("id"))%>>
        </div>
        <div>
            ConnectedTemplatesId: <input type="text" name="connectedTemplatesId" value="<%=getConnectedTemplatesId(
                    (BigInteger)request.getSession().getAttribute("id")
            )%>">
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%!
    private String getConnectedTemplatesId(BigInteger customerId)
    {
        String res = "";
        for (Template template : customerEJB.getConnectedTemplates(customerId).values())
        {
            res += template.getId() + ",";
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

            customerEJB.setCustomersFields((BigInteger) request.getSession().getAttribute("id"),
                    name, password, login, BigInteger.valueOf(Long.parseLong(area)),
                    BigDecimal.valueOf(Double.parseDouble(moneyBalance)));

            for (String s : templates)
            {
                if (s != null && !s.isEmpty())
                {
                    BigInteger templateId = BigInteger.valueOf(Long.parseLong(s));
                    boolean connected = false;
                    for (BigInteger serviceId : customerEJB
                            .getConnectedServicesIds((BigInteger) request.getSession().getAttribute("id")))
                    {
                        Service service = ControllerImpl.getInstance().getService(serviceId);
                        Template template = service.getTemplate();
                        if (!template.getPossibleAreas()
                                .contains(customerEJB.getAreaId((BigInteger) request.getSession().getAttribute("id"))))
                        {
                            customerEJB.disconnectService(serviceId);
                            break;
                        }
                        if (service.getTemplate().equals(templateId))
                        {
                            connected = true;
                            break;
                        }
                    }
                    if (!connected && ControllerImpl.getInstance().getTemplate(templateId).getPossibleAreas()
                            .contains(customerEJB.getAreaId((BigInteger) request.getSession().getAttribute("id"))))
                    {
                        customerEJB.connectService((BigInteger) request.getSession().getAttribute("id"), templateId);
                    }
                    else
                    {
                        throw new WrongInputException("Service уже подключен или Area пользователя не соответствует " +
                                "данному template");
                    }
                }
            }

%>
<jsp:forward page="/webEmployeeView.jsp"/>
<%
        }
        catch (WrongInputException e1)
        {
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
