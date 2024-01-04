<%--
  Created by IntelliJ IDEA.
  User: 王旭莲
  Date: 2020/12/28
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 后台主页</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
    <link href="${pageContext.request.contextPath}/libs/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/libs/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/libs/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/libs/css/style.css?v=4.1.0" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/libs/layer/layer.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="glyphicon glyphicon-home"></i>
                                        <strong class="font-bold">图书管理系统</strong>
                                    </span>
                                </span>
                        </a>
                    </div>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">管理员功能菜单</span>
                </li>
                <c:if test="${sessionScope.user.power==0}">
                    <li>
                        <a class="J_menuItem" href="#">
                            <i class="glyphicon glyphicon-home"></i>
                            <span class="nav-label">图书管理</span>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a class="J_menuItem" href="../welcome.jsp">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">返回首页</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/allBooks">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">图书管理</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/allTypes">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">种类管理</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/allReaders">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">读者信息管理</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/getAllBooks">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">图书借阅</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/toReturnBook">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">图书归还、续借</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/toUpdatePass">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">修改密码</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/manager/outlogin">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">退出系统</span>
                    </a>
                </li>



            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="glyphicon glyphicon-th-list"></i> </a>
                    <!-- 头部放置 -->
                </div>

            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="../welcome.jsp" frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/libs/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/libs/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/libs/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/libs/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/libs/js/hAdmin.js?v=4.1.0"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/index.js"></script>

<!-- 第三方插件 -->
<script src="${pageContext.request.contextPath}/libs/js/plugins/pace/pace.min.js"></script>

</body>

</html>

