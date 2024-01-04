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
<script type="text/javascript" src="libs/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="libs/layui/layui.all.js"></script>
<script type="text/javascript" src="libs/js/util.js"></script>
<script type="text/javascript" src="libs/js/mylayer.js"></script>
<link href="css/css.css" rel="stylesheet">
<style type="text/css">
.layui-table{
margin-left: 30px !important;
}
.wrap{
	width: 150px; //设置需要固定的宽度
white-space: nowrap; //不换行
text-overflow: ellipsis; //超出部分用....代替
overflow: hidden; //超出隐藏
}

</style>
   </head>
<body>
	<div class="demoTable">
<script type="text/javascript">
function lend(id){
	var code = prompt("请输入读者编号：");
	if(code){
		location.href='manager/lendBook?bid='+id+'&rid='+code;
	}else {
		alert("请输入读者编号！")
	}
}
</script>
<form action="User" method="post">
<input name="name">
<button type="submit"  class="layui-btn" >查询</button>

<%--	<button  class="layui-btn"  type="button" onclick="location.href='manager/toAddBook'">新增</button>--%>

</form>
</div>

<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table ">
			<thead>
					<tr>
	<td style="width: 100px;">编号</td>
	<td style="width: 100px;">书名</td>
	<td style="width: 100px;">出版社</td>
    <td style="width: 100px;">类别</td>
	<td style="width: 100px;">作者</td>
	<td style="width: 100px;">数量</td>
	<td style="width: 150px;">介绍</td>
	<td style="width: 100px;">出版时间</td>
	<td>操作</td></tr>
				</thead>
				<tbody>

	<c:forEach items="${allBooks}" var="row" varStatus="v">
	<tr>
	<td>${row.isbn}</td>
	<td>${row.name}</td>
	<td>${row.press}</td>
    <td>${row.type.tname}</td>
	<td>${row.author}</td>
	<td>${row.num}</td>
	<td><div class="wrap" title="${row.introduction}">${row.introduction}</div></td>
	<td>${row.ctime}</td>
	<td>
	<a href="javascript:;" onclick="lend(${row.id});">借阅</a>
<%--	<a href="manager/toUpdateBook?isbn=${row.isbn}"></a>--%>
	</td></tr>
	
	</c:forEach>
	<tr>
		<td>
			&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
			第${pageCur}页&nbsp;&nbsp;
			<c:url var="url_pre" value="manager/getAllBooks">
				<c:param name="pageCur" value="${pageCur - 1 }"/>
			</c:url>
			<c:url var="url_next" value="manager/getAllBooks">
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
	</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>