<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.users.Customer" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tab.css">
    <title>UpdateCustomer</title>
    <%!
        BigInteger customerId;
        Customer customer;
    %>
    <%
        customerId = (BigInteger) request.getSession().getAttribute("updateCustomerId");
        customer = ControllerImpl.getInstance().getCustomer(customerId);
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
            ConnectedServicesId: <input type="text" name="connectedServicesId" value="<%=getConnectedServicesId()%>">
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%!
    private String getConnectedServicesId()
    {
        String res = "";
        for (BigInteger id : customer.getConnectedServicesIds())
        {
            res += id + ",";
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
            String servicesValue = request.getParameter("connectedServicesId");
            String[] services = servicesValue.split(",");

            customer.setName(name);
            customer.setPassword(password);
            customer.setLogin(login);
            customer.setAreaId(BigInteger.valueOf(Long.parseLong(area)));

            Set<BigInteger> set = new HashSet<>();
            for (String s : services)
            {
                if (s != null && !s.isEmpty())
                    set.add(BigInteger.valueOf(Long.parseLong(s)));
            }
            customer.setConnectedServicesIds(set);
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
