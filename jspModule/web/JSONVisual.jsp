<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON</title>
</head>
<body>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
<h2>JSON Entity</h2>
<pre>
    <%
        try
        { %>
          <%=ControllerImpl.getInstance().getEntity(
                  BigInteger.valueOf(Long.parseLong(request.getParameter("entityId"))))%>
    <%
        }
        catch (Exception e)
        {
            response.getWriter().println("Несуществующая сущность");
        }
    %>
</pre>
</body>
</html>
