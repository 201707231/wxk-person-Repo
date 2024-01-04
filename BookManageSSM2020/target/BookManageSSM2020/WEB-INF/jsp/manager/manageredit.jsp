<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<link rel="stylesheet" href="libs/css/edit.css">
<script type="text/javascript" src="libs/js/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
	<c:if test="${requestScope.state.equals('s')}">
	window.alert("修改个人信息成功！");
	</c:if>
</script>
	<form action="manager/updateManager" method="post">
		<input type="hidden" name="id" value="${manager.id}">
		<span class="inputspan">
		<label class="inputtext">性名:</label>
		<input class="inputinput" name="name" value="${manager.name}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">性别:</label>

		男<input class="inputinput" name="sex" value="男" type="radio"
				<c:if test="${manager.sex.equals('男')}">checked="checked"</c:if>
		/>
		女<input class="inputinput" name="sex" value="女" type="radio"
				<c:if test="${manager.sex.equals('女')}">checked="checked"</c:if>
		/>
		</span>

		<span class="inputspan">
		<label class="inputtext">年龄:</label>
		<input class="inputinput" name="age" value="${manager.age}" type="text"/>
		</span>

			<span class="inputspan">
		<label class="inputtext">电话</label>
		<input class="inputinput" name="telephone" value="${manager.telephone}" type="text"/>
		</span>

		</div>
		<div class="inputbtpanel ">
		<button class="seachbt1" type="submit" >保存</button>
		</div>
	</form>
	
</body>
</html>