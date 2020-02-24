<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType" %>
<%@ page import="com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType" %>
<%@ page import="jsp.CustomerWebOperations" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="com.netcracker.students.o3.model.serializer.SerializerImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="allStyles.css">
    <link rel="stylesheet" href="tab.css">
    <title>MiniOPF</title>
    <%
        CustomerWebOperations customerWO = CustomerWebOperations.getInstance();
        customerWO.start((BigInteger) request.getSession().getAttribute("id"));

        customerWO.addBalance(request.getParameter("sum"));

        String services = new SerializerImpl().serializeToString(customerWO.getConnectedServices()).replaceAll("\"","'");
        String possibleAreasByServiceId = new SerializerImpl().serializeToString(customerWO.getConnectedTemplates()).replaceAll("\"","'");
    %>

    <form action="${pageContext.request.contextPath}/customerServlet" method="post" class="header">
        <div class="balance">
            Balance: <%=customerWO.getBalance()+"$"%>
            <input type="submit" name="putOnBalance" value="+" onclick="showBalanceForm()" class="button">
        </div>
        <div class="login">
            You are logged in as:
            <m style="color: #8c4667">
            <%=customerWO.getFIO()%>
            </m>
            <input type="submit" name="out" value="Out" class="button">
        </div>
    </form>
</head>
<body>
<div class="wrapperGrid">
    <div class="main">
        <div class="wrapper">
            <div class="tabs">
                <div class="tabs__nav tabs-nav">
                    <div class="tabs-nav__item is-active" data-tab-name="tab-1">My services</div>
                    <div class="tabs-nav__item" data-tab-name="tab-2">All services</div>
                    <div class="tabs-nav__item" data-tab-name="tab-3">Settings</div>
                    <div class="tabs-nav__item" data-tab-name="tab-4">Search</div>
                </div>
                <form action="${pageContext.request.contextPath}/customerServlet" method="post">
                    <div class="tabs__content">
                        <div class="tab is-active tab-1">
                            <%=customerWO.showConnectedServices((ServiceSortType) session.getAttribute("sortServices"))%>
                        </div>
                        <div class="tab tab-2">
                            <%=customerWO.showAllTemplates((TemplateSortType) session.getAttribute("sortTemplates"))%>
                        </div>
                        <div class="tab tab-3">
                            <div class="customerSettings">
                                <div class="name">
                                    Name: <input type="text" name="fio" value="<%=customerWO.getFIO()%>">
                                </div>

                                <div class="login">
                                    Login: <%=customerWO.getLogin()%>
                                </div>

                                <div class="password">
                                    Password: <input type="text" name="password"
                                                     value=<%=customerWO.getPassword()%>>
                                </div>

                                <div class="selectArea">
                                    Area: <select id="areaChange" name="area"
                                                  onchange="
                                                  changeArea(<%=services+","+possibleAreasByServiceId%>)">
                                    <%=customerWO.selectArea()%>
                                </select>
                                </div>
                                <input type="submit" name="change" class="button">
                            </div>
                        </div>
                        <div class="tab tab-4">
                            <div class="searchWrapper">
                                <input type="text" name="searchField" value="">
                                <input type="submit" name="searchButton" value="Search">
                            </div>
                            <%=customerWO.search((String) request.getSession().getAttribute("searchField"))%>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <footer>
        <div align="center">
            © NetCracker ERC
        </div>
    </footer>
</div>
<script src="main.js">

</script>
</body>
</html>