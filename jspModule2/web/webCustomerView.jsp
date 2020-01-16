<%@ page import="com.netcracker.students.o3.controller.sorters.SortType" %>
<%@ page import="com.netcracker.students.o3.model.area.Area" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.util.List" %>
<%@ page import="jsp.CustomerWebOperations" %>
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
                request.open("POST", "${pageContext.request.contextPath}/webCustomerView.jsp?" + body);
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
        List<Area> availableAreas = customerWebOperations.getAvailableAreas();

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
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    String area = request.getParameter("area");

                    Area newArea = null;

                    for (Area a : customerWebOperations.getAvailableAreas())
                    {
                        if (a.getName().equals(area))
                        {
                            newArea = a;
                            break;
                        }
                    }


                    customerWebOperations.changeName(name);
                    customerWebOperations.changeLogin(login);
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
                <%=customerWebOperations.showEnteringActiveServices()%>
        </form>
    </div>

    <div id="txt_2" class="prokrutka">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            <%=customerWebOperations.showAllTemplates()%>
        </form>
    </div>
    <div id="txt_3" class="settings">
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