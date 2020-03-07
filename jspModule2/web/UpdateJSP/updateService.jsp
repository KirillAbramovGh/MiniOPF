<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="com.netcracker.students.o3.model.services.ServiceStatus" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tab.css">
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
            Status: <input type="text" name="status"
                           value=<%=service.getStatus()%>>
        </div>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%
    try
    {
        if (request.getParameter("save") != null)
        {
            String templateId = request.getParameter("templateId");
            String userId = request.getParameter("userId");
            String status = request.getParameter("status");

            service.setTemplateId(BigInteger.valueOf(Long.parseLong(templateId)));
            service.setUserId(BigInteger.valueOf(Long.parseLong(userId)));
            service.setStatus(ServiceStatus.valueOf(status));

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
