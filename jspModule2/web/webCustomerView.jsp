<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType" %>
<%@ page import="com.netcracker.students.o3.model.area.Area" %>
<%@ page import="jsp.CustomerWebOperations" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>MiniOPF</title>
    <%!
        CustomerWebOperations customerWebOperations = CustomerWebOperations.getInstance();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        customerWebOperations.start(id);
    %>
</head>
<body>
<%!
    private void disconnect(String key) {
        String[] res = key.split(" ");
        String numb = res[1];
        if (!numb.equals("")) {
            int value = Integer.parseInt(numb);
            customerWebOperations.disconnectService(BigInteger.valueOf(value));
        }
    }

    private void suspendOrResume(String key) {
        String[] res = key.split(" ");
        String numb = res[1];
        if (!numb.equals("")) {
            int value = Integer.parseInt(numb);
            customerWebOperations.suspendOrResumeService(BigInteger.valueOf(value));
        }
    }

    private void connect(String key) {
        String[] res = key.split(" ");
        String numb = res[1];
        if (!numb.equals("")) {
            int value = Integer.parseInt(numb);
            customerWebOperations.connectService(BigInteger.valueOf(value));
        }
    }
%>
<h1 align="right">
    <%
        for (String key : request.getParameterMap().keySet()) {
            if (request.getParameterMap().get(key) != null) {
                if (key.startsWith("disconnect")) {
                    disconnect(key);
                } else if (key.contains("suspend") || key.contains("resume")) {
                    suspendOrResume(key);
                } else if (key.startsWith("connect")) {
                    connect(key);
                } else if (key.equals("change")) {
                    String name = request.getParameter("fio");
                    String password = request.getParameter("password");
                    String area = request.getParameter("area");

                    Area newArea = null;

                    for (Area a : customerWebOperations.getAreas()) {
                        if (a.getName().equals(area)) {
                            newArea = a;
                            break;
                        }
                    }


                    customerWebOperations.changeName(name);
                    customerWebOperations.changePassword(password);
                    customerWebOperations.changeArea(newArea);

                } else if (key.equals("searchButton")) {
                    request.getSession().setAttribute("searchField", request.getParameter("searchField"));
                } else if (key.equals("ServiceSortUpByName")) {
                    request.getSession().setAttribute("sortServices", ServiceSortType.UpByName);
                } else if (key.equals("ServiceSortDownByName")) {
                    request.getSession().setAttribute("sortServices", ServiceSortType.DownByName);
                } else if (key.equals("ServiceSortUpByCost")) {
                    request.getSession().setAttribute("sortServices", ServiceSortType.UpByCost);
                } else if (key.equals("ServiceSortDownByCost")) {
                    request.getSession().setAttribute("sortServices", ServiceSortType.DownByCost);
                } else if (key.equals("TemplateSortUpByName")) {
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.UpByName);
                } else if (key.equals("TemplateSortDownByName")) {
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.DownByName);
                } else if (key.equals("TemplateSortUpByCost")) {
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.UpByCost);
                } else if (key.equals("TemplateSortDownByCost")) {
                    request.getSession().setAttribute("sortTemplates", TemplateSortType.DownByCost);
                } else if (key.toLowerCase().equals("out")) {
                    ServletContext servletContext = pageContext.getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/startView.jsp");

                    requestDispatcher.forward(request, response);
                }
            }
        }

        String textValue = request.getParameter("sum");
        if (textValue != null && !textValue.isEmpty()) {
            customerWebOperations.addBalance(textValue);
        }
    %>
    <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
        Balance: <%=customerWebOperations.getBalance()%>
        <input type="button" name="putOnBalance" value="+" onclick="showBalanceForm()">
        You are logged in as:<%=customerWebOperations.getFIO()%>
        <input type="submit" name="Out" value="Out">
    </form>
</h1>
<div class="wrapper">
    <div class="tabs">
        <div class="tabs__nav tabs-nav">
            <div class="tabs-nav__item is-active" data-tab-name="tab-1">My services</div>
            <div class="tabs-nav__item" data-tab-name="tab-2">All services</div>
            <div class="tabs-nav__item" data-tab-name="tab-3">Settings</div>
            <div class="tabs-nav__item" data-tab-name="tab-4">Search</div>
        </div>
        <div class="tabs__content">
            <div class="tab is-active tab-1">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    <%=customerWebOperations.showConnectedServices((ServiceSortType) session.getAttribute("sortServices"))%>
                </form>
            </div>
            <div class="tab tab-2">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    <%=customerWebOperations.showAllTemplates((TemplateSortType) session.getAttribute("sortTemplates"))%>
                </form>
            </div>
            <div class="tab tab-3">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    Name: <input type="text" name="fio" value="<%=customerWebOperations.getFIO()%>"><br/>
                    Login: <%=customerWebOperations.getLogin()%><br/>
                    Password: <input type="text" name="password" value=<%=customerWebOperations.getPassword()%>><br/>
                    Area: <select name="area">
                    <%=customerWebOperations.selectArea()%>
                </select>
                    <input type="submit" name="change">
                </form>
            </div>
            <div class="tab tab-4">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    <input type="text" name="searchField" value="">
                    <input type="submit" name="searchButton" value="Search">
                    <%=customerWebOperations.search((String) request.getSession().getAttribute("searchField"))%>
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