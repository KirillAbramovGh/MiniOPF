<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.orders.Order" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderAction" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderStatus" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="com.netcracker.students.o3.model.services.ServiceStatus" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>UpdateOrder</title>
    <%!
        BigInteger orderId;
        Order order;
    %>
    <%
        orderId = (BigInteger) request.getSession().getAttribute("updateOrderId");
        order = ControllerImpl.getInstance().getOrder(orderId);
    %>
</head>
<body>
<form action="${pageContext.request.contextPath}/UpdateJSP/updateOrder.jsp" method="post">
    <div style="size: 200px">
        <div class="name">
            EmployeeId: <input type="text" name="employeeId" value="<%=order.getEmployeeId()%>">
        </div>

        <div class="">
            ServiceId: <input type="text" name="serviceId" value="<%=order.getServiceId()%>">
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
        List<Service> services = ControllerImpl.getInstance().getServices();
        for (Service service : services)
        {
            if (service.getId().equals(order.getServiceId()) &&
                    !service.getStatus().equals(ServiceStatus.Disconnected))
            {
                response.getWriter().println("У этого order есть service");
                break;
            }
        }
        if (request.getParameter("save") != null)
        {
            String employeeId = request.getParameter("employeeId");
            String serviceId = request.getParameter("serviceId");
            String status = request.getParameter("status");
            String action = request.getParameter("action");

            BigInteger serviceIdValue = BigInteger.valueOf(Long.parseLong(serviceId));
            Service service = ControllerImpl.getInstance().getService(serviceIdValue);

            order.setEmployeeId(BigInteger.valueOf(Long.parseLong(employeeId)));
            order.setServiceId(serviceIdValue);
            order.setTemplateId(service.getTemplateId());
            order.setStatus(OrderStatus.valueOf(status));
            order.setAction(OrderAction.valueOf(action));

            ControllerImpl.getInstance().setOrder(order);

%>
<jsp:forward page="/webEmployeeView.jsp"/>
<%
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
        response.getWriter().println("Input Error");
    }
%>
</body>
</html>
