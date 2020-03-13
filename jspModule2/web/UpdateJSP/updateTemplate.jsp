<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="com.netcracker.students.o3.model.services.ServiceStatus" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="com.netcracker.students.o3.model.templates.Template" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>UpdateService</title>
    <%!
        BigInteger templateId;
        Template template;
    %>
    <%
        templateId = (BigInteger) request.getSession().getAttribute("updateTemplateId");
        template = ControllerImpl.getInstance().getTemplate(templateId);
    %>
</head>
<body>
<form action="${pageContext.request.contextPath}/UpdateJSP/updateTemplate.jsp" method="post">
    <div style="size: 200px">
        <div class="">
            Name: <input type="text" name="name" value="<%=template.getName()%>">
        </div>

        <div class="">
            Cost: <input type="text" name="cost" value="<%=template.getCost()%>">
        </div>
        <div class="">
            Description: <input type="text" name="description"
                           value=<%=template.getDescription()%>>
        </div>
        <div>
            PossibleAreasId: <input type="text" name="areas"
                                    value=<%=getPossibleAreasId()%>>
        </div>
        <%!
            private String getPossibleAreasId()
            {
                String res = "";
                for (BigInteger id : template.getPossibleAreasId())
                {
                    res += id + ",";
                }
                return res;
            }
        %>
        <input type="submit" name="save" class="button">
    </div>
</form>
<%
    try
    {
        if (request.getParameter("save") != null)
        {
            String name = request.getParameter("name");
            String cost = request.getParameter("cost");
            String description = request.getParameter("description");
            String[] areas = request.getParameter("areas").split(",");

            template.setDescription(description);
            template.setCost(BigDecimal.valueOf(Double.parseDouble(cost)));
            template.setName(name);

            List<BigInteger> set = new ArrayList<>();
            for(String id : areas){
                if(id!= null && !id.isEmpty()){
                    set.add(BigInteger.valueOf(Long.parseLong(id)));
                }
            }
            template.setPossibleAreasId(set);
            ControllerImpl.getInstance().setTemplate(template);

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
