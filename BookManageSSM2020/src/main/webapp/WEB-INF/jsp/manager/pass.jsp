<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">

<link href="libs/layui/css/layui.css" rel="stylesheet">
<link rel="stylesheet" href="libs/css/edit.css">
<script type="text/javascript" src="libs/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="libs/layui/layui.all.js"></script>
<script type="text/javascript" src="libs/js/util.js"></script>
<script type="text/javascript" src="libs/js/mylayer.js"></script>
<script type="text/javascript" src="libs/js/jquery.min.js"></script>
<link href="css/css.css" rel="stylesheet">
<style type="text/css">
.layui-table{
margin-left: 30px !important;
}
</style>
   </head>
<body>
	<div class="demoTable">
		<script type="text/javascript">
			<c:if test="${requestScope.state.equals('sucess')}">
			window.alert("修改密码成功！");
			</c:if>
			<c:if test="${requestScope.state.equals('f')}">
			window.alert("修改密码失败！两次输入的密码不一致");
			</c:if>
		</script>
<form action="manager/updatePass" method="post">
        <span class="inputspan">
		<label class="inputtext">密码:</label>
		<input class="inputinput" name="pass"  type="password" placeholder="请输入新密码" id="oldPwd"/>
		</span>

	    <span class="inputspan">
		<label class="inputtext">重复密码:</label>
			<input class="inputinput" name="repass" type="password" placeholder="请确认密码" id="password"/>
		</span>

<button type="submit"  class="layui-btn" id="idBtn">修改密码</button>
</form>
</div>
</body>
</html>