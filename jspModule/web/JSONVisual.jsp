<%@ page import="java.math.BigInteger" %>
<%@ page import="javax.inject.Inject" %>
<%@ page import="jsp.ejb.JSONVisualiseEJB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON</title>
</head>
<body>
<script>
    function getJSON() {
        let object = <%=jsonVisualiseEJB.getJsonFromEntity(BigInteger.valueOf(Long.parseLong(request.getParameter("entityId"))))%>;
        return JSON.stringify(object);
    }
</script>
<%!
    @Inject
    JSONVisualiseEJB jsonVisualiseEJB;
%>
<pre>
<code>
    <script>document.write(getJSON());</script>
</code>
</pre>
</body>
</html>
