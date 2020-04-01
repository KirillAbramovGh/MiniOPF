<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.orders.Order" %>
<%@ page import="com.netcracker.students.o3.model.orders.OrderStatus" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="com.netcracker.students.o3.model.services.ServiceStatus" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.netcracker.students.o3.controller.Controller" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>UpdateService</title>
    <%!
        BigInteger serviceId;
        Service service;
    %>
    <%
        serviceId = (BigInteger) request.getSession().getAttribute("updateServiceId");
        service = ControllerImpl.getInstance().getService(serviceId);
    %>
</head>
<body>
<form action="${pageContext.request.contextPath}/UpdateJSP/updateService.jsp" method="post">
    <div style="size: 200px">
        <div class="">
            TemplateId: <input type="text" name="templateId" value="<%=service.getTemplateId()%>">
        </div>

        <div class="">
            UserId: <input type="text" name="userId" value="<%=service.getUserId()%>">
        </div>
        <div class="">
            Status:
            <select name="status">
                <option>Planned</option>
                <option>Active</option>
                <option>Processing</option>
                <option>Disconnected</option>
                <option>Suspended</option>
            </select>
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%
    try
    {

            if (ControllerImpl.getInstance().getOrdersByServiceId(serviceId).size()>0)
            {
                response.getWriter().println("У этого сервиса есть order");
            }

        if (request.getParameter("save") != null)
        {
            String templateId = request.getParameter("templateId");
            String userId = request.getParameter("userId");
            String status = request.getParameter("status");

            service.setTemplateId(BigInteger.valueOf(Long.parseLong(templateId)));
            service.setUserId(BigInteger.valueOf(Long.parseLong(userId)));
            service.setStatus(ServiceStatus.valueOf(status));

            for (Order order : ControllerImpl.getInstance().getOrdersByServiceId(serviceId))
            {
                if (!order.getStatus().equals(OrderStatus.Completed))
                {
                    if (service.getStatus().equals(ServiceStatus.Disconnected))
                    {
                        order.setStatus(OrderStatus.Completed);
                        continue;
                    }

                    switch (order.getAction())
                    {
                        case New:
                            if (
                                    !service.getStatus().equals(ServiceStatus.Planned)
                            )
                            {
                                order.setStatus(OrderStatus.Completed);
                            } ; break;
                        case Resume:
                        case Suspend:
                            break;
                    }
                    ControllerImpl.getInstance().setOrder(order);
                }
            }


            ControllerImpl.getInstance().setService(service);

%>
<jsp:forward page="/webEmployeeView.jsp"/>
<%
        }
    }
    catch (Exception e)
    {
        response.getWriter().println("Wrong input!");
    }
%>
</body>
</html>
