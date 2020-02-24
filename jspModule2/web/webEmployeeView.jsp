<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.*" %>
<%@ page import="jsp.EmployeeWebOperations" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="allStyles.css">
    <link rel="stylesheet" href="tab.css">
    <title>MiniOPF</title>
    <%!
        private EmployeeWebOperations employeeWebOperations = EmployeeWebOperations.getInstance();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        employeeWebOperations.start(id);
    %>
</head>
<body>
<h1 align="right">
    <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
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
                    <%=employeeWebOperations.showEmployeeOrders(
                            request.getParameter("searchFieldEmployeeOrders"),
                            request.getParameter("employeeOrderSelectField"),
                            (OrderSortType) request.getSession().getAttribute("sortOrders")
                    )%>
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
                    <%=employeeWebOperations.showAllOrders(
                            request.getParameter("searchFieldAllOrders"),
                            request.getParameter("allOrderSelectField"),
                            (OrderSortType) request.getSession().getAttribute("sortOrders")
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
                    <%=employeeWebOperations.showAllServices(
                            request.getParameter("searchFieldAllServices"),
                            request.getParameter("allServicesSelectField"),
                            (ServiceSortType) request.getSession().getAttribute("sortServices")
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
                    <%=employeeWebOperations.showAllTemplates(
                            request.getParameter("searchFieldAllTemplates"),
                            request.getParameter("allTemplatesSelectField"),
                            (TemplateSortType) request.getSession().getAttribute("sortTemplates")
                    )%>
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
                    <%=employeeWebOperations.showAllCustomers(
                            request.getParameter("searchFieldAllCustomers"),
                            request.getParameter("allCustomersSelectField"),
                            (CustomerSortType) request.getSession().getAttribute("sortCustomers")
                    )%>
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
                    <%=employeeWebOperations.showAllEmployees(
                            request.getParameter("searchFieldAllEmployees"),
                            request.getParameter("allEmployeesSelectField"),
                            (EmployeeSortType) request.getSession().getAttribute("sortEmployees")
                    )%>
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
                    <%=employeeWebOperations.showAllAreas(
                            request.getParameter("searchFieldAllAreas"),
                            request.getParameter("allAreasSelectField"),
                            (AreaSortType) request.getSession().getAttribute("sortAreas")
                    )%>
                </form>
            </div>
            <div class="tab tab-8">
                <form action="${pageContext.request.contextPath}/employeeServlet" method="post">
                    Name: <input type="text" name="fio" value="<%=employeeWebOperations.getFIO()%>"><br/>
                    Login: <%=employeeWebOperations.getLogin()%><br/>
                    Password: <input type="text" name="password" value=<%=employeeWebOperations.getPassword()%>><br/>
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
                    <%=employeeWebOperations.showAll(request.getParameter("searchAllEntities"),
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

<script src="main.js"></script>
</body>
</html>