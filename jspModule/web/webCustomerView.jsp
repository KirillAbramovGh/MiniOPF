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
        #tab_3:checked ~ #txt_3 {
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


    private String selectArea(WebCustomerView webCustomerView)
    {
        String resultHtml = "";
        List<Area> availableAreas = webCustomerView.getAvailableAreas();

        for (Area area : availableAreas)
        {
            if (webCustomerView.getAreaName().equals(area.getName()))
            {
                resultHtml += "<option selected value='" + area.getName() + "'>" + area.getName() + "</option>";
            }
            else
            {
                resultHtml +=
                        "<option>" + area.getName() + "</option>"
                ;
            }
        }
        return resultHtml;
    }

    private void disconnect(String key)
    {
        String numb = key.substring(10);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            webCustomerView.disconnectService(value - 1);
        }
    }

    private void suspendOrResume(String key)
    {
        String numb = key.substring(14);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            webCustomerView.suspendOrResumeService(value - 1);
        }
    }

    private void connect(String key)
    {
        String numb = key.substring(7);
        if (!numb.equals(""))
        {
            int value = Integer.parseInt(numb);
            webCustomerView.connectService(value - 1);
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

    <div id="txt_1" class="prokrutka">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            <table border="1" width="auto" cellpadding="20">
                <%=webCustomerView.showEnteringServices()%>
            </table>
        </form>
    </div>

    <div id="txt_2" class="prokrutka">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            <%=webCustomerView.showAllTemplates()%>
        </form>
    </div>
    <div id="txt_3" class="settings">
        <form action="${pageContext.request.contextPath}/webCustomerView.jsp" method="post">
            Name: <input type="text" name="fio" value="<%=webCustomerView.getFIO()%>"><br/>
            Login: <input type="text" name="login" value="<%=webCustomerView.getLogin()%>"><br/>
            Password: <input type="password" name="password" value=""><br/>
            Area: <select name="area">
            <%=selectArea(webCustomerView)%>
        </select>
            <input type="submit" name="change">
        </form>
    </div>
</div>

<script src="main.js"></script>

<footer>
    <div align="center">
        © NetCracker ERC
    </div>
</footer>

</body>
</html>