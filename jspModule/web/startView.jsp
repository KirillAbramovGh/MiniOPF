<%@ page import="com.netcracker.students.o3.controller.Controller" %>
<%@ page import="com.netcracker.students.o3.controller.ControllerImpl" %>
<%@ page import="com.netcracker.students.o3.model.area.Area" %>
<%@ page import="java.util.List" %>
<%@ page import="jsp.HtmlSelectBuilder" %><%--
  Created by IntelliJ IDEA.
  User: Kirill
  Date: 26.12.2019
  Time: 21:31
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
            min-height: 40%;
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
            width: 50%;
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
    <%!
        public String showArea()
        {
            Controller controller = ControllerImpl.getInstance();
            List<Area> areas = controller.getAreas();
            HtmlSelectBuilder selectBuilder = new HtmlSelectBuilder();
            selectBuilder.setNameAttribute("area");

            for(Area area : areas){
                selectBuilder.addElement(area.getName());
            }

            return selectBuilder.built();
        }
    %>
</head>
<body>
<div class="tabs" align="center">
    <input type="radio" name="inset" value="" id="tab_1" checked>
    <label for="tab_1">Login</label>

    <input type="radio" name="inset" value="" id="tab_2">
    <label for="tab_2">RegCustomer</label>

    <input type="radio" name="inset" value="" id="tab_3">
    <label for="tab_3">RegAdmin</label>
    <div id="txt_1">
        <form action="http://localhost:8080/jspModule_war_exploded/start" method="post">
            <input type="text" name="login"><br/>
            <input type="password" name="password" value="password"><br/>
            <input type="submit" name="loginUser" align="center">
        </form>
    </div>

    <div id="txt_2">
        <form action="http://localhost:8080/jspModule_war_exploded/start" method="post">
            <input type="text" name="fio" value="FIO"><br/>
            <input type="text" name="login" value="login"><br/>
            <input type="password" name="password" value="password"><br/>
                <%=showArea()%><br/>
            <input type="submit" name="regCustomer" value="Register">
        </form>
    </div>
    <div id="txt_3">
        <form action="http://localhost:8080/jspModule_war_exploded/start" method="post">
            <input type="text" name="fio" value="FIO"><br/>
            <input type="text" name="login" value="login"><br/>
            <input type="password" name="password" value="password"><br/>
            <input type="submit" name="regAdmin" value="Register">
        </form>
    </div>
</div>
</div>

<script src="main.js"></script>
</body>
<footer>
    <div align="center">
        © NetCracker ERC
    </div>
</footer>
</html>
