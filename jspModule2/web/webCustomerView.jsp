<%@ page import="com.netcracker.students.o3.controller.sorters.SortType" %>
<%@ page import="com.netcracker.students.o3.model.area.Area" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.List" %>
<%@ page import="jsp.CustomerWebOperations" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>MiniOPF</title>
    <%!
        CustomerWebOperations customerWebOperations = new CustomerWebOperations();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        customerWebOperations.start(id);
    %>
</head>
<body>
<%!
    String resultSearch = "";

    private void showSearchResult(String req)
    {
        resultSearch = customerWebOperations.search(req);
    }

    private String selectArea()
    {
        StringBuilder resultHtml = new StringBuilder();
        List<Area> availableAreas = customerWebOperations.getAreas();

        for (Area area : availableAreas)
        {
            if (customerWebOperations.getAreaName().equals(area.getName()))
            {
                resultHtml.append("<option selected value='").append(area.getName()).append("'>").append(area.getName())
                        .append("</option>");
            }
            else
            {
                resultHtml.append("<option>").append(area.getName()).append("</option>");
            }
        }
        return resultHtml.toString();
    }

    private void disconnect(String key)
    {
        String numb = key.substring(10);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            customerWebOperations.disconnectService(BigInteger.valueOf(value));
        }
    }

    private void suspendOrResume(String key)
    {
        String numb = key.substring(14);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            customerWebOperations.suspendOrResumeService(BigInteger.valueOf(value));
        }
    }

    private void connect(String key)
    {
        String numb = key.substring(7);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            customerWebOperations.connectService(BigInteger.valueOf(value));
        }
    }
%>
<h1 align="right">
    <%
        for (String key : request.getParameterMap().keySet())
        {
            if (request.getParameterMap().get(key) != null)
            {
                if (key.startsWith("disconnect"))
                {
                    disconnect(key);
                }
                else if (key.startsWith("suspend/resume"))
                {
                    suspendOrResume(key);
                }
                else if (key.startsWith("connect"))
                {
                    connect(key);
                }
                else if (key.equals("change"))
                {
                    String name = request.getParameter("fio");
                    String password = request.getParameter("password");
                    String area = request.getParameter("area");

                    Area newArea = null;

                    for (Area a : customerWebOperations.getAreas())
                    {
                        if (a.getName().equals(area))
                        {
                            newArea = a;
                            break;
                        }
                    }


                    customerWebOperations.changeName(name);
                    customerWebOperations.changePassword(password);
                    customerWebOperations.changeArea(newArea);

                }
                else if (key.equals("searchButton"))
                {
                    showSearchResult(request.getParameter("searchField"));
                }
                else if (key.equals("ServiceSortUpByName"))
                {
                    customerWebOperations.defineServiceSortType(SortType.UpByName);
                }
                else if (key.equals("ServiceSortDownByName"))
                {
                    customerWebOperations.defineServiceSortType(SortType.DownByName);
                }
                else if (key.equals("ServiceSortUpByCost"))
                {
                    customerWebOperations.defineServiceSortType(SortType.UpByCost);
                }
                else if (key.equals("ServiceSortDownByCost"))
                {
                    customerWebOperations.defineServiceSortType(SortType.DownByCost);
                }
                else if (key.equals("TemplateSortUpByName"))
                {
                    customerWebOperations.defineTemplateSortType(SortType.UpByName);
                }
                else if (key.equals("TemplateSortDownByName"))
                {
                    customerWebOperations.defineTemplateSortType(SortType.DownByName);
                }
                else if (key.equals("TemplateSortUpByCost"))
                {
                    customerWebOperations.defineTemplateSortType(SortType.UpByCost);
                }
                else if (key.equals("TemplateSortDownByCost"))
                {
                    customerWebOperations.defineTemplateSortType(SortType.DownByCost);
                }else if (key.toLowerCase().equals("out")){
                    ServletContext servletContext = pageContext.getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/startView.jsp");

                    requestDispatcher.forward(request, response);
                }
            }
        }

        String textValue = request.getParameter("sum");
        if (textValue != null && !textValue.isEmpty())
        {
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
                    <%=customerWebOperations.showEnteringActiveServices()%>
                </form>
            </div>
            <div class="tab tab-2">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    <%=customerWebOperations.showAllTemplates()%>
                </form>
            </div>
            <div class="tab tab-3">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    Name: <input type="text" name="fio" value="<%=customerWebOperations.getFIO()%>"><br/>
                    Login: <%=customerWebOperations.getLogin()%><br/>
                    Password: <input type="text" name="password" value=<%=customerWebOperations.getPassword()%>><br/>
                    Area: <select name="area">
                    <%=selectArea()%>
                </select>
                    <input type="submit" name="change">
                </form>
            </div>
            <div class="tab tab-4">
                <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
                    <input type="text" name="searchField" value="">
                    <input type="submit" name="searchButton" value="Search">
                    <%=resultSearch%>
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