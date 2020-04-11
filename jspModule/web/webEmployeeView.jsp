<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.AreaSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.CustomerSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.EmployeeSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.OrderSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType" %>
<%@ page import="com.netcracker.students.o3.model.users.Employee" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="javax.inject.Inject" %>
<%@ page import="jsp.helpers.EmployeeJspHelper" %>
<%@ page import="jsp.sessionBeans.EmployeeSessionBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>MiniOPF</title>
    <%!
        @Inject
        EmployeeSessionBean employeeSessionBean;

        private EmployeeJspHelper employeeJspHelper = EmployeeJspHelper.getInstance();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        Employee employee;
        String name = "";
        String login = "";
        String password = "";
        if (id != null)
        {
            employee = employeeSessionBean.getEmployee(id);
            name = employee.getName();
            login = employee.getLogin();
            password = employee.getPassword();
        }
    %>
</head>
<body>
<h1 align="right">
    <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
        You are logged in as:<%=name%>
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
            <div class="tabs-nav__item" data-tab-name="tab-9">Search</div>
        </div>
        <div class="tabs__content">
            <div class="tab is-active tab-1">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
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
                    <input type="submit" name="createOrder" value="Create">
                    <%=employeeJspHelper.showFilteredOrdersByEmployeeId(
                            request.getParameter("searchFieldEmployeeOrders"),
                            request.getParameter("employeeOrderSelectField"),
                            (OrderSortType) request.getSession().getAttribute("sortOrders"),
                            request.getParameter("filterOrderTemplateId"),
                            request.getParameter("filterOrderServiceId"),
                            (BigInteger) request.getSession().getAttribute("id"),
                            employeeSessionBean
                    )%>
                </form>
                <form class="export" action="${pageContext.request.contextPath}/importType.jsp" method="post">
                    <input type="submit" name="importEntities" value="import JSON">
                </form>
            </div>
            <div class="tab tab-2">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
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
                    <input type="submit" name="createOrder" value="Create">
                    <%=employeeJspHelper.showFilteredOrders(
                            request.getParameter("searchFieldAllOrders"),
                            request.getParameter("allOrderSelectField"),
                            (OrderSortType) request.getSession().getAttribute("sortOrders"),
                            request.getParameter("filterOrderTemplateId"),
                            request.getParameter("filterOrderServiceId"),
                            request.getParameter("filterOrderEmployeeId"),
                            employeeSessionBean
                    )%>
                </form>
            </div>
            <div class="tab tab-3">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
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
                    <input type="submit" name="createService" value="Create">
                    <%=employeeJspHelper.showFilteredServices(
                            request.getParameter("searchFieldAllServices"),
                            request.getParameter("allServicesSelectField"),
                            (ServiceSortType) request.getSession().getAttribute("sortServices"),
                            request.getParameter("filterServiceName"),
                            request.getParameter("filterServiceCost"),
                            employeeSessionBean
                    )%>
                </form>
            </div>
            <div class="tab tab-4">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="text" name="searchFieldAllTemplates" value="">
                    <input type="submit" name="searchButtonAllTemplates" value="Search">
                    <select name="allTemplatesSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Cost</option>
                        <option>Description</option>
                        <option>Areas</option>
                    </select>
                    <input type="submit" name="createTemplate" value="Create">
                    <%=employeeJspHelper.showFilteredTemplates(
                            request.getParameter("searchFieldAllTemplates"),
                            request.getParameter("allTemplatesSelectField"),
                            (TemplateSortType) request.getSession().getAttribute("sortTemplates"),
                            request.getParameter("filterTemplateName"),
                            request.getParameter("filterTemplateCost"),
                            employeeSessionBean
                    )%>
                </form>
                <form class="export" action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="submit" name="exportTemplates" value="export JSON">
                </form>
            </div>
            <div class="tab tab-5">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
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
                    <input type="submit" name="createCustomer" value="Create">
                    <%=employeeJspHelper.showFilteredCustomers(
                            request.getParameter("searchFieldAllCustomers"),
                            request.getParameter("allCustomersSelectField"),
                            (CustomerSortType) request.getSession().getAttribute("sortCustomers"),
                            request.getParameter("filterCustomerName"),
                            request.getParameter("filterCustomerArea"),
                            employeeSessionBean
                    )%>
                </form>
                <form class="export" action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="submit" name="exportCustomers" value="export JSON">
                </form>
            </div>
            <div class="tab tab-6">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="text" name="searchFieldAllEmployees" value="">
                    <input type="submit" name="searchButtonAllEmployees" value="Search">
                    <select name="allEmployeesSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Login</option>
                    </select>
                    <input type="submit" name="createEmployee" value="Create">
                    <%=employeeJspHelper.showFilteredEmployees(
                            request.getParameter("searchFieldAllEmployees"),
                            request.getParameter("allEmployeesSelectField"),
                            (EmployeeSortType) request.getSession().getAttribute("sortEmployees"),
                            request.getParameter("filterEmployeeName"),
                            employeeSessionBean
                    )%>
                </form>
                <form class="export" action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="submit" name="exportEmployees" value="export JSON">
                </form>
            </div>
            <div class="tab tab-7">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="text" name="searchFieldAllAreas" value="">
                    <input type="submit" name="searchButtonAllAreas" value="Search">
                    <select name="allAreasSelectField">
                        <option selected="selected">Id</option>
                        <option>Name</option>
                        <option>Description</option>
                    </select>
                    <input type="submit" name="createArea" value="Create">
                    <input type="submit" name="filterArea" value="Filter">
                    <%=employeeJspHelper.showFilteredAreas(
                            request.getParameter("searchFieldAllAreas"),
                            request.getParameter("allAreasSelectField"),
                            (AreaSortType) request.getSession().getAttribute("sortAreas"),
                            request.getParameter("filterAreaName"),
                            employeeSessionBean
                    )%>
                </form>
                <form class="export" action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="submit" name="exportAreas" value="export JSON">
                </form>
            </div>
            <div class="tab tab-8">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    Name: <input type="text" name="fio" value="<%=name%>"><br/>
                    Login: <%=login%><br/>
                    Password: <input type="text" name="password" value=<%=password%>><br/>
                    <input type="submit" name="save" value="Save">
                </form>
            </div>
            <div class="tab tab-9">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    <input type="text" name="searchAllEntities" value="">
                    <input name="serviceRadio" type="radio" value="Services">Services
                    <input name="templateRadio" type="radio" value="Templates">Templates
                    <input name="orderRadio" type="radio" value="Orders">Orders
                    <input name="areaRadio" type="radio" value="Areas">Areas</br>
                    <input name="customerRadio" type="radio" value="Customers">Customers</br>
                    <input name="employeeRadio" type="radio" value="Employees">Employees
                    <input type="submit" name="searchAll" value="Search">
                    <%=employeeJspHelper.showAll(request.getParameter("searchAllEntities"),
                            employeeSessionBean,
                            request.getParameter("serviceRadio"),
                            request.getParameter("templateRadio"),
                            request.getParameter("orderRadio"),
                            request.getParameter("areaRadio"),
                            request.getParameter("customerRadio"),
                            request.getParameter("employeeRadio")
                    )%>
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

<script src="${pageContext.request.contextPath}/main1.js"></script>
</body>
</html>