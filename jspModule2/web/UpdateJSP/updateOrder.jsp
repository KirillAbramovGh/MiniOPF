<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.users.Customer" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.netcracker.students.o3.model.orders.Order" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderStatus" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderAction" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tab.css">
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
            Status: <input type="text" name="status"
                             value=<%=order.getStatus()%>>
        </div>

        <div class="selectArea">
            Action: <input type="text" name="action"
                         value=<%=order.getAction()%>>
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%
    try
    {
    if(request.getParameter("save")!=null)
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
<jsp:forward page="/webEmployeeView.jsp" />
<%
    }
    }catch (Exception e){
        response.getWriter().println("Input Error");
    }
%>
</body>
</html>
