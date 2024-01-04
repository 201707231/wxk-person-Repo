<%--
  Created by IntelliJ IDEA.
  User: 王旭莲
  Date: 2020/12/28
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="libs/layui/css/layui.css" rel="stylesheet">
<head>
    <title>Title</title>
    <style type="text/css">
        div
        {
            list-style-type:none;
            padding:0;
            margin:0;
            text-align:center;
        }
    </style>
</head>
<body>
<h1 align="center">欢迎进入图书管理系统</h1>
<div style="margin: 0 auto;font-size:15px">
    你的个人信息是：<br/>
    性名：${sessionScope.manager.name}<br/>
    年龄：${sessionScope.manager.age}<br/>
    性别：${sessionScope.manager.sex}<br/>
    电话：${sessionScope.manager.telephone}
</div>
<form action="manager/toUpdateManager" method="post">
    <button  class="layui-btn"  type="submit">修改个人信息</button>
</form>
</body>
</html>
