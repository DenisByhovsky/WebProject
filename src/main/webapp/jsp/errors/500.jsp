<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 16.01.2018
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>505</title>
</head>
<body>
<div class="container">
    <div class="content">

        <div class="title">
            <h2>There is an 500 internal server error.</h2>
            Try later.<br><a href="index.jsp">Back to main page</a></div>
        <button type="button" name="back" onclick="history.back()" class="btn">Back</button>
    </div>
</div>
    <style>
        html, body {
            height: 100%;
        }
        body {
            margin: 0;
            padding: 0;
            width: 100%;
            color: #B0BEC5;
            background-color: #f3f3f3;
            display: table;
            font-weight: 100;
            font-family: 'Lato', sans-serif;
        }
        .container {
            text-align: center;
            display: table-cell;
            vertical-align: middle;
        }

        a{
            color: #B0BEC5;
            font-size: 0.3em;
            text-decoration: none;
        }
        .content {
            text-align: center;
            display: inline-block;
        }
        .title {
            font-size: 72px;
            margin-bottom: 40px;
        }
    </style>

</body>
</html>
