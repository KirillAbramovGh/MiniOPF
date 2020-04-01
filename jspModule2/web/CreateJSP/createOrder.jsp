<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.orders.Order" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderAction" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderStatus" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tab.css">
    <title>CreateOrder</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/CreateJSP/createOrder.jsp" method="post">
    <div style="size: 200px">
        <div class="name">
            EmployeeId: <input type="text" name="employeeId">
        </div>

        <div class="">
            ServiceId: <input type="text" name="serviceId">
        </div>

        <div class="password">
            Status: <select name="status">
            <option>Entering</option>
            <option>Active</option>
            <option>Processing</option>
            <option>Disconnected</option>
        </select>
        </div>

        <div class="selectArea">
            Action: <select name="action">
            <option>New</option>
            <option>Disconnect</option>
            <option>Resume</option>
            <option>Suspend</option>
        </select>
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%
    try
    {
        if (request.getParameter("save") != null)
        {
            String employeeId = request.getParameter("employeeId");
            String serviceId = request.getParameter("serviceId");
            String status = request.getParameter("status");
            String action = request.getParameter("action");

            BigInteger serviceIdValue = BigInteger.valueOf(Long.parseLong(serviceId));
            Service service = ControllerImpl.getInstance().getService(serviceIdValue);

            Order order = ControllerImpl.getInstance().createOrder(
                    service.getTemplateId(), serviceIdValue, OrderStatus.valueOf(status),
                    OrderAction.valueOf(action)
            );

            order.setEmployeeId(BigInteger.valueOf(Long.parseLong(employeeId)));

            ControllerImpl.getInstance().setOrder(order);

%>
<jsp:forward page="/webEmployeeView.jsp"/>
<%
        }
    }
    catch (Exception e)
    {
        response.getWriter().println("Input Error");
    }
%>
</body>
</html>
