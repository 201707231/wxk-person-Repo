<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<base href="./">
<link href="${pageContext.request.contextPath}/libs/layui/css/layui.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/layui/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/mylayer.js"></script>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<style type="/text/css">
.layui-table{
margin-left: 30px !important;
}
</style>
   </head>
<body>
	<div class="demoTable">
<script type="text/javascript">
function del(tid){
	if(confirm("确认删除?")){
		location.href='${pageContext.request.contextPath}/manager/deleteType?tid='+tid;
	}
}
</script>
<form action="${pageContext.request.contextPath}/manager/addType" method="post">
<input type="text" name="tname">
	<button  class="layui-btn"  type="submit">新增</button>
</form>
</div>

<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table center1" >
			<thead>
					<tr>
	<td style="width: 100px;">编号</td>
	<td style="width: 200px;">名称</td>
	<td>操作</td></tr>
				</thead>
				<tbody>

	<c:forEach items="${allTypes}" var="row" varStatus="v">
	<tr>
	<td>${row.tid}</td>
	<td>${row.tname}</td>
	<td>
	<a href="javascript:del(${row.tid});">删除</a>
	</td></tr>
	
	</c:forEach>
	</tbody>
			</table>
		</div>
	</div>
	
	
	
</div>
</body>
</html>