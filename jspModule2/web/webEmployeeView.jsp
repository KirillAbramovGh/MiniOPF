<%@ page import="jsp.EmployeeWebOperations" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>MiniOPF</title>
    <%!
        EmployeeWebOperations employeeWebOperations = new EmployeeWebOperations();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        employeeWebOperations.start(id);
    %>
</head>
<body>
<h1 align="right">
    <%
        for (String key : request.getParameterMap().keySet()) {
            if (request.getParameterMap().get(key) != null) {
                if(key.equals("out")){
                    out(pageContext,request,response);
                }else if(key.equals("save")){
                    String name = request.getParameter("fio");
                    String password = request.getParameter("password");

                    employeeWebOperations.changeNameAndPassword(name,password);
                }else if(key.contains("startOrder")){
                    startOrder(key);
                }else if(key.contains("resumeOrder")){
                    resumeOrder(key);
                }else if(key.contains("completeOrder")){
                    completeOrder(key);
                }else if(key.contains("deleteTemplate")){
                    deleteTemplate(key);
                }else if(key.contains("deleteCustomer")){
                    deleteCustomer(key);
                }else if(key.contains("deleteEmployee")){
                    deleteEmployee(key);
                }else if(key.contains("deleteArea")){
                    deleteArea(key);
                }else if (key.startsWith("searchButton")){
                    String type = key.substring(11);
                    switch (type){
                        case "EmployeeOrders":;break;
                        case "AllOrders": break;
                        case "AllServices":break;
                        case "AllTemplates":break;
                        case "AllCustomers":break;
                        case "AllEmployees":break;
                        case "AllAreas":break;
                    }
                }
            }
        }
    %>
    <%!
        private void deleteArea(String key) {
            BigInteger areaId = BigInteger.valueOf(Long.parseLong(key.substring(9)));
            employeeWebOperations.deleteArea(areaId);
        }

        private void deleteEmployee(String key) {
            BigInteger employeeId = BigInteger.valueOf(Long.parseLong(key.substring(13)));
            employeeWebOperations.deleteEmployee(employeeId);
        }

        private void deleteCustomer(String key) {
            BigInteger customerId = BigInteger.valueOf(Long.parseLong(key.substring(13)));
            employeeWebOperations.deleteCustomer(customerId);
        }

        private void deleteTemplate(String key) {
            BigInteger templateId = BigInteger.valueOf(Long.parseLong(key.substring(13)));
            employeeWebOperations.deleteTemplate(templateId);
        }

        private void completeOrder(String key) {
            BigInteger orderId = BigInteger.valueOf(Long.parseLong(key.substring(12)));
            employeeWebOperations.completeOrder(orderId);
        }

        private void resumeOrder(String key) {
            BigInteger orderId = BigInteger.valueOf(Long.parseLong(key.substring(10)));
            employeeWebOperations.resumeOrder(orderId);
        }

        private void startOrder(String key) {
            BigInteger orderId = BigInteger.valueOf(Long.parseLong(key.substring(9)));
            employeeWebOperations.startOrder(orderId);
        }


        private void out(PageContext pageContext, ServletRequest request, ServletResponse response) throws ServletException, IOException {
            ServletContext servletContext = pageContext.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/startView.jsp");

            requestDispatcher.forward(request, response);
        }

    %>
    <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
        You are logged in as:<%=employeeWebOperations.getFIO()%>
        <input type="submit" name="out" value="Out">
    </form>
</h1>
<div class="wrapper">
    <div class="tabs">
        <div class="tabs__nav tabs-nav">
            <div class="tabs-nav__item is-active" data-tab-name="tab-1">My orders</div>
            <div class="tabs-nav__item" data-tab-name="tab-2">All orders</div>
            <div class="tabs-nav__item" data-tab-name="tab-3">All services</div>
            <div class="tabs-nav__item" data-tab-name="tab-4">All templates</div>
            <div class="tabs-nav__item" data-tab-name="tab-5">All customers</div>
            <div class="tabs-nav__item" data-tab-name="tab-6">All employees</div>
            <div class="tabs-nav__item" data-tab-name="tab-7">All areas</div>
            <div class="tabs-nav__item" data-tab-name="tab-8">Settings</div>
        </div>
        <div class="tabs__content">
            <div class="tab is-active tab-1">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldEmployeeOrders" value="">
                    <input type="submit" name="searchButtonEmployeeOrders" value="Search">
                    <select name="employeeOrderSelectField">
                        <option>id</option>
                        <option>TemplateId</option>
                        <option>ServiceId</option>
                        <option>EmployeeId</option>
                        <option>Status</option>
                        <option>Action</option>
                    </select>
                    <%=employeeWebOperations.showEmployeeOrders(
                            request.getParameterMap().get("searchFieldEmployeeOrders")[0],
                            request.getParameterMap().get("employeeOrderSelectField")[0]
                            )%>
                </form>
            </div>
            <div class="tab tab-2">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllOrders" value="">
                    <input type="submit" name="searchButtonAllOrders" value="Search">
                    <%=employeeWebOperations.showAllOrders()%>
                </form>
            </div>
            <div class="tab tab-3">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllServices" value="">
                    <input type="submit" name="searchButtonAllServices" value="Search">
                    <%=employeeWebOperations.showAllServices()%>
                </form>
            </div>
            <div class="tab tab-4">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllTemplates" value="">
                    <input type="submit" name="searchButtonAllTemplates" value="Search">
                    <%=employeeWebOperations.showAllTemplates()%>
                </form>
            </div>
            <div class="tab tab-5">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllCustomers" value="">
                    <input type="submit" name="searchButtonAllCustomers" value="Search">
                    <%=employeeWebOperations.showAllCustomers()%>
                </form>
            </div>
            <div class="tab tab-6">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllEmployees" value="">
                    <input type="submit" name="searchButtonAllEmployees" value="Search">
                    <%=employeeWebOperations.showAllEmployees()%>
                </form>
            </div>
            <div class="tab tab-7">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllAreas" value="">
                    <input type="submit" name="searchButtonAllAreas" value="Search">
                    <%=employeeWebOperations.showAllAreas()%>
                </form>
            </div>
            <div class="tab tab-8">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    Name: <input type="text" name="fio" value="<%=employeeWebOperations.getFIO()%>"><br/>
                    Login: <%=employeeWebOperations.getLogin()%><br/>
                    Password: <input type="text" name="password" value=<%=employeeWebOperations.getPassword()%>><br/>
                    <input type="submit" name="save" value="Save">
                </form>
            </div>
        </div>
    </div>
</div>
<footer>
    <div align="center">
        © NetCracker ERC
    </div>
</footer>
<script src="main.js"></script>
</body>
</html>