<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title> - 登录</title>

    <link href="${pageContext.request.contextPath}/libs/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/libs/css/font-awesome.css?v=4.4.0" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/libs/css/animate.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/libs/css/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/libs/css/login.css" rel="stylesheet" type="text/css">

    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
        var a=0;
        function changerimg(o) {
            o.src="img?a="+(a++);
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-12">
            <form action="${pageContext.request.contextPath}/manager/login"  method="post" >
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录到后台框架</p>
                <input type="text"  name="name" class="form-control uname" placeholder="用户名" />
                <input type="password"  name="password" class="form-control pword m-b" placeholder="密码" />


                <button class="btn btn-success btn-block" type="submit">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; situ
        </div>
    </div>
</div>

</body>

</html>
