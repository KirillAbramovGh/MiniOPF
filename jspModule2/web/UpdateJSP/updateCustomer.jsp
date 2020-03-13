<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.users.Customer" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.netcracker.students.o3.controller.Controller" %>
<%@ page import="com.netcracker.students.o3.model.services.ServiceStatus" %>
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
    if(request.getParameter("save")!=null)
    {
        try
        {
            String name = request.getParameter("fio");
            String password = request.getParameter("password");
            String login = request.getParameter("login");
            String area = request.getParameter("area");
            String servicesValue = request.getParameter("connectedTemplatesId");
            String[] templates = servicesValue.split(",");

            customer.setName(name);
            customer.setPassword(password);
            customer.setLogin(login);
            customer.setAreaId(BigInteger.valueOf(Long.parseLong(area)));

            for (String s : templates)
            {
                if (s != null && !s.isEmpty()){
                    BigInteger templateId = BigInteger.valueOf(Long.parseLong(s));
                    boolean connected = false;
                    for(BigInteger serviceId : customer.getConnectedServicesIds()){
                        if(controller.getService(serviceId).getTemplateId().equals(templateId)){
                            connected = true;
                            break;
                        }
                    }
                    if(!connected){
                        controller.createService(customerId,templateId, ServiceStatus.Entering);
                    }
                }
            }
            ControllerImpl.getInstance().setCustomer(customer);

%>
<jsp:forward page="/webEmployeeView.jsp" />
<%
        }catch (Exception e){
            response.getWriter().println("Input error");
        }
    }
%>
</body>
</html>
