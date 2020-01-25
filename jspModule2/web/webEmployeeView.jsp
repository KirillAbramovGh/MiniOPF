<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.*" %>
<%@ page import="jsp.EmployeeWebOperations" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>MiniOPF</title>
    <%!
        EmployeeWebOperations employeeWebOperations = EmployeeWebOperations.getInstance();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        employeeWebOperations.start(id);
    %>
</head>
<body>
<h1 align="right">
    <%
        cleanSession(request.getSession());
        for (String key : request.getParameterMap().keySet()) {
            if (request.getParameterMap().get(key) != null) {
                if (key.equals("out")) {
                    out(pageContext, request, response);
                } else if (key.equals("save")) {
                    String name = request.getParameter("fio");
                    String password = request.getParameter("password");

                    employeeWebOperations.changeNameAndPassword(name, password);
                } else if (key.contains("startOrder")) {
                    startOrder(key);
                } else if (key.contains("resumeOrder")) {
                    resumeOrder(key);
                } else if (key.contains("completeOrder")) {
                    completeOrder(key);
                } else if (key.contains("deleteTemplate")) {
                    deleteTemplate(key);
                } else if (key.contains("deleteCustomer")) {
                    deleteCustomer(key);
                } else if (key.contains("deleteEmployee")) {
                    deleteEmployee(key);
                } else if (key.contains("deleteArea")) {
                    deleteArea(key);
                } else if (key.toLowerCase().contains("startorder")) {
                    startOrder(key);
                } else if (key.toLowerCase().contains("completeorder")) {
                    completeOrder(key);
                } else if (key.toLowerCase().contains("resumeorder")) {
                    resumeOrder(key);
                } else if (key.startsWith("ServiceSort")) {
                    serviceSort(key, request);
                } else if (key.startsWith("TemplateSort")) {
                    templateSort(key, request);
                } else if (key.startsWith("AreaSort")) {
                    areaSort(key, request);
                } else if (key.startsWith("CustomerSort")) {
                    customerSort(key, request);
                } else if (key.startsWith("EmployeeSort")) {
                    employeeSort(key, request);
                } else if (key.startsWith("OrderSort")) {
                    orderSort(key, request);
                }
            }
        }
    %>
    <%!
        private void cleanSession(HttpSession session) {
            session.setAttribute("sortOrders", null);
            session.setAttribute("sortEmployees", null);
            session.setAttribute("sortCustomers", null);
            session.setAttribute("sortAreas", null);
            session.setAttribute("sortTemplates", null);
            session.setAttribute("sortServices", null);
        }

        private void orderSort(String key, HttpServletRequest request) {
            switch (key) {
                case "OrderSortUpById":
                    request.getSession().setAttribute("sortOrders", OrderSortType.UpById);
                    break;
                case "OrderSortDownById":
                    request.getSession().setAttribute("sortOrders", OrderSortType.DownById);
                    break;
            }
        }

        private void employeeSort(String key, HttpServletRequest request) {
            switch (key) {
                case "EmployeeSortUpById":
                    request.getSession().setAttribute("sortEmployees", EmployeeSortType.UpById);
                    break;
                case "EmployeeSortDownById":
                    request.getSession().setAttribute("sortEmployees", EmployeeSortType.DownById);
                    break;
                case "EmployeeSortUpByLogin":
                    request.getSession().setAttribute("sortEmployees", EmployeeSortType.UpByLogin);
                    break;
                case "EmployeeSortDownByLogin":
                    request.getSession().setAttribute("sortEmployees", EmployeeSortType.DownByLogin);
                    break;
                case "EmployeeSortUpByName":
                    request.getSession().setAttribute("sortEmployees", EmployeeSortType.UpByName);
                    break;
                case "EmployeeSortDownByName":
                    request.getSession().setAttribute("sortEmployees", EmployeeSortType.DownByName);
                    break;
            }
        }

        private void customerSort(String key, HttpServletRequest request) {
            switch (key) {
                case "CustomerSortUpByBalance":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.UpByBalance);
                    break;
                case "CustomerSortDownByBalance":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.DownByBalance);
                    break;
                case "CustomerSortUpById":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.UpById);
                    break;
                case "CustomerSortDownById":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.DownById);
                    break;
                case "CustomerSortUpByLogin":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.UpByLogin);
                    break;
                case "CustomerSortDownByLogin":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.DownByLogin);
                    break;
                case "CustomerSortUpByName":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.UpByName);
                    break;
                case "CustomerSortDownByName":
                    request.getSession().setAttribute("sortCustomers", CustomerSortType.DownByName);
                    break;
            }
        }

        private void areaSort(String key, HttpServletRequest request) {
            switch (key) {
                case "AreaSortUpByDescription":
                    request.getSession().setAttribute("sortAreas", AreaSortType.UpByDescription);
                    break;
                case "AreaSortDownByDescription":
                    request.getSession().setAttribute("sortAreas", AreaSortType.DownByDescription);
                    break;
                case "AreaSortUpById":
                    request.getSession().setAttribute("sortAreas", AreaSortType.UpById);
                    break;
                case "AreaSortDownById":
                    request.getSession().setAttribute("sortAreas", AreaSortType.DownById);
                    break;
                case "AreaSortUpByName":
                    request.getSession().setAttribute("sortAreas", AreaSortType.UpByName);
                    break;
                case "AreaSortDownByName":
                    request.getSession().setAttribute("sortAreas", AreaSortType.DownByName);
                    break;
            }
        }

        private void templateSort(String key, HttpServletRequest request) {
            switch (key) {
                case "TemplateSortUpByName":
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.UpByName);
                    break;
                case "TemplateSortDownByName":
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.DownByName);
                    break;
                case "TemplateSortUpByCost":
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.UpByCost);
                    break;
                case "TemplateSortDownByCost":
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.DownByCost);
                    break;
                case "TemplateSortUpById":
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.UpById);
                    break;
                case "TemplateSortDownById":
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.DownById);
                    break;
            }
        }

        private void serviceSort(String key, HttpServletRequest request) {
            switch (key) {
                case "ServiceSortUpByName":
                    request.getSession().setAttribute("sortServices", ServiceSortType.UpByName);
                    break;
                case "ServiceSortDownByName":
                    request.getSession().setAttribute("sortServices", ServiceSortType.DownByName);
                    break;
                case "ServiceSortUpByCost":
                    request.getSession().setAttribute("sortServices", ServiceSortType.UpByCost);
                    break;
                case "ServiceSortDownByCost":
                    request.getSession().setAttribute("sortServices", ServiceSortType.DownByCost);
                    break;
                case "ServiceSortUpById":
                    request.getSession().setAttribute("sortServices", ServiceSortType.UpById);
                    break;
                case "ServiceSortDownById":
                    request.getSession().setAttribute("sortServices", ServiceSortType.DownById);
                    break;
            }
        }

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
            BigInteger orderId = BigInteger.valueOf(Long.parseLong(key.substring(10)));
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
                        <option selected="selected">Id</option>
                        <option>TemplateId</option>
                        <option>ServiceId</option>
                        <option>EmployeeId</option>
                        <option>Status</option>
                        <option>Action</option>
                    </select>
                    <%=employeeWebOperations.showEmployeeOrders(
                            request.getParameter("searchFieldEmployeeOrders"),
                            request.getParameter("employeeOrderSelectField"),
                            (OrderSortType) request.getSession().getAttribute("sortOrders")
                    )%>
                </form>
            </div>
            <div class="tab tab-2">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllOrders" value="">
                    <input type="submit" name="searchButtonAllOrders" value="Search">
                    <select name="allOrderSelectField">
                        <option selected="selected">Id</option>
                        <option>TemplateId</option>
                        <option>ServiceId</option>
                        <option>EmployeeId</option>
                        <option>Status</option>
                        <option>Action</option>
                    </select>
                    <%=employeeWebOperations.showAllOrders(
                            request.getParameter("searchFieldAllOrders"),
                            request.getParameter("allOrderSelectField"),
                            (OrderSortType) request.getSession().getAttribute("sortOrders")
                    )%>
                </form>
            </div>
            <div class="tab tab-3">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllServices" value="">
                    <input type="submit" name="searchButtonAllServices" value="Search">
                    <select name="allServicesSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Cost</option>
                        <option>Status</option>
                        <option>TemplateId</option>
                        <option>UserId</option>
                        <option>ActivationDate</option>
                        <option>Areas</option>
                    </select>
                    <%=employeeWebOperations.showAllServices(
                            request.getParameter("searchFieldAllServices"),
                            request.getParameter("allServicesSelectField"),
                            (ServiceSortType) request.getSession().getAttribute("sortServices")
                    )%>
                </form>
            </div>
            <div class="tab tab-4">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllTemplates" value="">
                    <input type="submit" name="searchButtonAllTemplates" value="Search">
                    <select name="allTemplatesSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Cost</option>
                        <option>Description</option>
                        <option>Areas</option>
                    </select>
                    <%=employeeWebOperations.showAllTemplates(
                            request.getParameter("searchFieldAllTemplates"),
                            request.getParameter("allTemplatesSelectField"),
                            (TemplateSortType) request.getSession().getAttribute("sortTemplates")
                    )%>
                </form>
            </div>
            <div class="tab tab-5">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllCustomers" value="">
                    <input type="submit" name="searchButtonAllCustomers" value="Search">
                    <select name="allCustomersSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Login</option>
                        <option>Area</option>
                        <option>Balance</option>
                        <option>ConnectedServices</option>
                    </select>
                    <%=employeeWebOperations.showAllCustomers(
                            request.getParameter("searchFieldAllCustomers"),
                            request.getParameter("allCustomersSelectField"),
                            (CustomerSortType) request.getSession().getAttribute("sortCustomers")
                    )%>
                </form>
            </div>
            <div class="tab tab-6">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllEmployees" value="">
                    <input type="submit" name="searchButtonAllEmployees" value="Search">
                    <select name="allEmployeesSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Login</option>
                    </select>
                    <%=employeeWebOperations.showAllEmployees(
                            request.getParameter("searchFieldAllEmployees"),
                            request.getParameter("allEmployeesSelectField"),
                            (EmployeeSortType) request.getSession().getAttribute("sortEmployees")
                    )%>
                </form>
            </div>
            <div class="tab tab-7">
                <form action="${pageContext.request.contextPath}/webEmployeeView.jsp" method="post">
                    <input type="text" name="searchFieldAllAreas" value="">
                    <input type="submit" name="searchButtonAllAreas" value="Search">
                    <select name="allAreasSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Description</option>
                    </select>
                    <%=employeeWebOperations.showAllAreas(
                            request.getParameter("searchFieldAllAreas"),
                            request.getParameter("allAreasSelectField"),
                            (AreaSortType) request.getSession().getAttribute("sortAreas")
                    )%>
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