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
		<base href="./">
<link href="libs/layui/css/layui.css" rel="stylesheet">
<script type="text/javascript" src="libs/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="libs/layui/layui.all.js"></script>
<script type="text/javascript" src="libs/js/util.js"></script>
<script type="text/javascript" src="libs/js/mylayer.js"></script>
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
function del(id){
	if(confirm("确认删除?")){
		location.href='manager/deleteReader?id='+id;
	}
}
</script>
<form action="manager/findReadersByTag" method="post">
<input name="name">
<button type="submit"  class="layui-btn" >查询</button>

	<button  class="layui-btn"  type="button" onclick="location.href='manager/toAddReader'">新增</button>

</form>
</div>

<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table ">
			<thead>
					<tr>
	<td style="width: 100px;">编号</td>
	<td style="width: 100px;">性名</td>
	<td style="width: 100px;">性别</td>
    <td style="width: 100px;">年龄</td>
	<td style="width: 100px;">电话</td>
	<td style="width: 100px;">邮箱</td>
	<td style="width: 100px;">密码</td>
	<td style="width: 100px;">开始时间</td>
	<td style="width: 100px;">结束时间</td>
<%--	<td style="width: 100px;">惩罚情况</td>--%>
	<td style="width: 100px;">借书次数</td>
	<td>操作</td></tr>
				</thead>
				<tbody>

	<c:forEach items="${readers}" var="row" varStatus="v">
	<tr>
	<td>${row.id}</td>
	<td>${row.name}</td>
	<td>${row.sex}</td>
    <td>${row.age}</td>
	<td>${row.telephone}</td>
	<td>${row.email}</td>
	<td>${row.password}</td>
		<td>${row.start}</td>
		<td>${row.end}</td>
<%--		<td>${row.punishcount}</td>--%>
		<td>${row.lendcount}</td>
	<td>
	<a href="javascript:;" onclick="del(${row.id});">删除</a>
	<a href="manager/toUpdateReader?id=${row.id}">修改</a>
	</td></tr>
	
	</c:forEach>
	<c:if test="${sessionScope.select.equals('all')}">
	<tr>
		<td>
			&nbsp;&nbsp;&nbsp;
			共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
			第${pageCur}页&nbsp;&nbsp;
			<c:url var="url_pre" value="manager/allReaders">
				<c:param name="pageCur" value="${pageCur - 1 }"/>
			</c:url>
			<c:url var="url_next" value="manager/allReaders">
				<c:param name="pageCur" value="${pageCur + 1 }"/>
			</c:url>
			<!-- 第一页没有上一页 -->
			<c:if test="${pageCur != 1 }">
				<a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<!-- 最后一页，没有下一页 -->
			<c:if test="${pageCur != totalPage && totalPage != 0}">
				<a href="${url_next}">下一页</a>
			</c:if>
		</td>
	</tr>
	</c:if>
	</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>