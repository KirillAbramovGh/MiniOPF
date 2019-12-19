<%@ page import="com.netcracker.students.o3.model.services.Service" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="jsp.WebCustomerView" %>
<%--
  Created by IntelliJ IDEA.
  User: Kirill
  Date: 13.12.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <% WebCustomerView webCustomerView = new WebCustomerView();
        webCustomerView.start(BigInteger.valueOf(18));
    %>
</head>

<body>

<%!
    public String showEnteringServices(WebCustomerView webCustomerView)
    {
        String resultHtml = "";

        int i = 1;
        for (Service service : webCustomerView.getEnteringServices())
        {

            resultHtml += "<tr>" +
                    "<th>" + i + "</th>" +
                    "<th>" + webCustomerView.getServiceName(service.getId()) + "</th>" +
                    "<th>" + service.getCost() + "</th>" +
                    "<th>" +
                    "<input type='button' value='disconnect'>" +
                    "</th>" +
                    "</tr>";
            i++;
        }
        System.out.println(resultHtml);
        return resultHtml;
    }
%>
<h1 align="right">
    <form action="webCustomerView.jsp" method="get">
        Balance: <%=webCustomerView.getBalance()%>
        <input type="button" name="putOnBalance" value="put">
        Fio:<%=webCustomerView.getFIO()%>
    </form>
</h1>


<div class="tabs">
    <input type="radio" name="inset" value="" id="tab_1" checked>
    <label for="tab_1">Мои услуги</label>

    <input type="radio" name="inset" value="" id="tab_2">
    <label for="tab_2">Все услуги</label>

    <input type="radio" name="inset" value="" id="tab_3">
    <label for="tab_3">Настройки</label>

    <div id="txt_1">
        <table border="1" width="auto" cellpadding="20">
            <%=showEnteringServices(webCustomerView)%>
        </table>
    </div>
    <div id="txt_2">

    </div>
    <div id="txt_3">

    </div>
</div>

<script src="main.js"></script>

<footer>
    <div align="center">
        © NetCracker ERC

        <span>
            <script type="text/javascript">
                function corc() {
                    document.write("ahaha");
                }
                corc();
            </script>
        </span>
    </div>
</footer>

</body>
</html>