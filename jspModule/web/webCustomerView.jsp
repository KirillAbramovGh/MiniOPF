<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.serializer.SerializerImpl" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="javax.inject.Inject" %>
<%@ page import="jsp.CustomerWebVisualiser" %>
<%@ page import="jsp.ejb.CustomerEJB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/allStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/tabs.css">
    <title>MiniOPF</title>
    <%!
        @Inject
        CustomerEJB customerEJB;
    %>
    <%
        CustomerWebVisualiser customerWV = CustomerWebVisualiser.getInstance();

        customerEJB.addBalance(request.getParameter("sum"), (BigInteger) request.getSession().getAttribute("id"));

        String services = new SerializerImpl().serializeToString(
                ControllerImpl.getInstance().getPlannedActiveSuspendedProvisioningService(
                        (BigInteger) request.getSession().getAttribute("id"))).replaceAll("\"", "'");
        String possibleAreasByServiceId = new SerializerImpl().serializeToString(
                customerEJB.getConnectedTemplates((BigInteger) request.getSession().getAttribute("id")))
                .replaceAll("\"", "'");
    %>

    <form action="${pageContext.request.contextPath}/customerServlet" method="post" class="header">
        <div class="balance">
            Balance: <%=customerEJB.getBalance((BigInteger) request.getSession().getAttribute("id")) + "$"%>
            <input type="submit" name="putOnBalance" value="+" onclick="showBalanceForm()" class="button">
        </div>
        <div class="login">
            You are logged in as:
            <m style="color: #8c4667">
                <%=customerEJB.getFIO((BigInteger) request.getSession().getAttribute("id"))%>
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
                            <%=customerWV.showConnectedServices((BigInteger) request.getSession().getAttribute("id"),
                                    customerEJB)%>
                        </div>
                        <div class="tab tab-2">
                            <%=customerWV.showAllTemplates((BigInteger) request.getSession().getAttribute("id"),
                                    customerEJB)%>
                        </div>
                        <div class="tab tab-3">
                            <div class="customerSettings">
                                <div class="name">
                                    Name: <input type="text" name="fio"
                                                 value="<%=customerEJB.getFIO((BigInteger) request.getSession().getAttribute("id"))%>">
                                </div>

                                <div class="login">
                                    Login: <%=customerEJB
                                        .getLogin((BigInteger) request.getSession().getAttribute("id"))%>
                                </div>

                                <div class="password">
                                    Password: <input type="text" name="password"
                                                     value=<%=customerEJB.getPassword((BigInteger) request.getSession().getAttribute("id"))%>>
                                </div>

                                <div class="selectArea">
                                    Area: <select id="areaChange" name="area"
                                                  onchange="
                                                          changeArea(<%=services+","+possibleAreasByServiceId%>)">
                                    <%=customerWV.selectArea((BigInteger) request.getSession().getAttribute("id"))%>
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
                            <%=customerWV.search((String) request.getSession().getAttribute("searchField"),
                                    (BigInteger) request.getSession().getAttribute("id"), customerEJB)%>
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
<script src="${pageContext.request.contextPath}/main1.js">

</script>
</body>
</html>