<%@ page import="com.netcracker.students.o3.controller.sorters.SortType" %>
<%@ page import="com.netcracker.students.o3.model.area.Area" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.List" %>
<%@ page import="jsp.WebCustomerView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MiniOPF</title>
    <style>
        html {
            height: 100%;
        }

        body {
            position: relative;
            min-height: 95%;
            background-color: #1d1c1d;
            color: aliceblue;
        }

        h1 {
            background-color: #343434;
            color: white;
        }

        p {
            background-color: #FFFFFF;
        }

        footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 30px;
            background: #710215;
            text-align: center;
            margin: auto;
        }
    </style>
    <style type="text/css">
        .tabs {
            padding: 0px;
            margin: 0 auto;
        }

        .tabs > input {
            display: none;
        }

        .tabs > div {
            display: none;
            padding: 12px;
            border: 1px solid #343434;
            background: #343434;
        }

        .tabs > label {
            display: inline-block;
            padding: 7px;
            margin: 0 -5px -1px 0;
            text-align: center;
            color: #FFFFFF;
            border: 1px solid #1d1c1d;
            background: #1d1c1d;
            cursor: pointer;

        }

        .tabs > input:checked + label {
            color: #FFFFFF;
            border: 1px solid #343434;
            background: #343434;
        }

        #tab_1:checked ~ #txt_1,
        #tab_2:checked ~ #txt_2,
        #tab_3:checked ~ #txt_3,
        #tab_4:checked ~ #txt_4 {
            display: block;
        }
    </style>
    <style>
        .prokrutka {
            height: 70%;
            overflow-y: scroll; /* прокрутка по вертикали */
        }

        .settings {
            height: 70%;
            padding: 5px;
            margin: 5px;
        }
    </style>
    <script>
        let request = new XMLHttpRequest();

        function showBalanceForm() {
            let sum = prompt("Введите сумму пополнения", "0");
            if (parseFloat(sum)) {
                var body = "sum=" + sum;
                request.open("POST", "http://localhost:8080/jspModule_war_exploded/webCustomerView.jsp?" + body);
                request.onreadystatechange = reqReadyStateChange;
                request.send();
            } else {
                alert("Введите число");
            }
        }

        function reqReadyStateChange() {
            if (request.readyState == 4) {
                let status = request.status;
                if (status == 200) {
                    document.getElementById("output").innerHTML = request.responseText;
                }
            }
        }
    </script>
    <%!
        WebCustomerView webCustomerView = new WebCustomerView();
    %>
    <%
        BigInteger id = (BigInteger) request.getSession().getAttribute("id");
        webCustomerView.start(id);
    %>
</head>

<body>
<%!
    String resultSearch = "";

    private void showSearchResult(String req)
    {
        resultSearch = webCustomerView.getSearchResult(req);
    }

    private String selectArea()
    {
        StringBuilder resultHtml = new StringBuilder();
        List<Area> availableAreas = webCustomerView.getAvailableAreas();

        for (Area area : availableAreas)
        {
            if (webCustomerView.getAreaName().equals(area.getName()))
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
            webCustomerView.disconnectService(BigInteger.valueOf(value));
        }
    }

    private void suspendOrResume(String key)
    {
        String numb = key.substring(14);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            webCustomerView.suspendOrResumeService(BigInteger.valueOf(value));
        }
    }

    private void connect(String key)
    {
        String numb = key.substring(7);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            webCustomerView.connectService(BigInteger.valueOf(value));
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
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    String area = request.getParameter("area");

                    Area newArea = null;

                    for (Area a : webCustomerView.getAvailableAreas())
                    {
                        if (a.getName().equals(area))
                        {
                            newArea = a;
                            break;
                        }
                    }


                    webCustomerView.changeName(name);
                    webCustomerView.changeLogin(login);
                    webCustomerView.changePassword(password);
                    webCustomerView.changeArea(newArea);

                }
                else if (key.equals("searchButton"))
                {
                    showSearchResult(request.getParameter("searchField"));
                }
                else if (key.equals("ServiceSortUpByName"))
                {
                    webCustomerView.defineServiceSortType(SortType.UpByName);
                }
                else if (key.equals("ServiceSortDownByName"))
                {
                    webCustomerView.defineServiceSortType(SortType.DownByName);
                }
                else if (key.equals("ServiceSortUpByCost"))
                {
                    webCustomerView.defineServiceSortType(SortType.UpByCost);
                }
                else if (key.equals("ServiceSortDownByCost"))
                {
                    webCustomerView.defineServiceSortType(SortType.DownByCost);
                }
                else if (key.equals("TemplateSortUpByName"))
                {
                    webCustomerView.defineTemplateSortType(SortType.UpByName);
                }
                else if (key.equals("TemplateSortDownByName"))
                {
                    webCustomerView.defineTemplateSortType(SortType.DownByName);
                }
                else if (key.equals("TemplateSortUpByCost"))
                {
                    webCustomerView.defineTemplateSortType(SortType.UpByCost);
                }
                else if (key.equals("TemplateSortDownByCost"))
                {
                    webCustomerView.defineTemplateSortType(SortType.DownByCost);
                }
            }
        }

        String textValue = request.getParameter("sum");
        if (textValue != null && !textValue.isEmpty())
        {
            webCustomerView.addBalance(textValue);
        }
    %>
    Balance: <%=webCustomerView.getBalance()%>
    <input type="button" name="putOnBalance" value="add" onclick="showBalanceForm()">
    Fio:<%=webCustomerView.getFIO()%>
</h1>


<div class="tabs">
    <input type="radio" name="inset" value="" id="tab_1" checked>
    <label for="tab_1">Мои услуги</label>

    <input type="radio" name="inset" value="" id="tab_2">
    <label for="tab_2">Все услуги</label>

    <input type="radio" name="inset" value="" id="tab_3">
    <label for="tab_3">Настройки</label>

    <input type="radio" name="inset" value="" id="tab_4">
    <label for="tab_4">Поиск</label>

    <div id="txt_1" class="prokrutka">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            <input type="submit" name="ServiceSortUpByName" value="ServiceSortUpByName">
            <input type="submit" name="ServiceSortDownByName" value="ServiceSortDownByName">
            <input type="submit" name="ServiceSortUpByCost" value="ServiceSortUpByCost">
            <input type="submit" name="ServiceSortDownByCost" value="ServiceSortDownByCost">
            <table border="1" width="auto" cellpadding="20">
                <%=webCustomerView.showEnteringActiveServices()%>
            </table>
        </form>
    </div>

    <div id="txt_2" class="prokrutka">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            <input type="submit" name="TemplateSortUpByName" value="TemplateSortUpByName">
            <input type="submit" name="TemplateSortDownByName" value="TemplateSortDownByName">
            <input type="submit" name="TemplateSortUpByCost" value="TemplateSortUpByCost">
            <input type="submit" name="TemplateSortDownByCost" value="TemplateSortDownByCost">
            <%=webCustomerView.showAllTemplates()%>
        </form>
    </div>
    <div id="txt_3" class="settings">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            Name: <input type="text" name="fio" value="<%=webCustomerView.getFIO()%>"><br/>
            Login: <input type="text" name="login" value="<%=webCustomerView.getLogin()%>"><br/>
            Password: <input type="password" name="password" value=""><br/>
            Area: <select name="area">
            <%=selectArea()%>
        </select>
            <input type="submit" name="change">
        </form>
    </div>
    <div id="txt_4" class="prokrutka">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            <input type="text" name="searchField" value="">
            <input type="submit" name="searchButton" value="Search">
            <%=resultSearch%>
        </form>
    </div>
</div>

<footer>
    <div align="center">
        © NetCracker ERC
    </div>
</footer>

</body>
</html>